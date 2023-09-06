package com.springboot.userservice.userservice.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.springboot.userservice.entities.Hotel;
import com.springboot.userservice.entities.Rating;
import com.springboot.userservice.entities.User;
import com.springboot.userservice.exception.ResourceNotFoundException;
import com.springboot.userservice.external.services.HotelService;
import com.springboot.userservice.repository.UserRepository;
import com.springboot.userservice.userservice.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;
	
	private Logger  logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public User save(User user) {
		String randonUserId = UUID.randomUUID().toString();
		user.setUserId(randonUserId);
		return userRepository.save(user);
	}

	@Override
	public User getUser(String userId) {
		 User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user with given id is not found "+userId));
		
		 //get rating based on user id from Rating service 
		 
		 Rating[] ratingObject = restTemplate.getForObject("http://RATING-SERVICE/rating/user/"+user.getUserId(), Rating[].class);
		 logger.info("{}", ratingObject);
		 
		 List<Rating> ratings = List.of(ratingObject);
		 
		 //we need to set hotel in Rating
		 List<Rating> ratingList = ratings.stream().map(rating ->{
			 //going through each rating and get hotel and set in rating
			 //ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://localhost:8082/hotel/"+rating.getHotelId(), Hotel.class);
			//Localhost and port can be changed if we deploy it on other server so we use naming of service which is registered on service discovery
			//using name of service is not going to work, restTemplate calling our Hotel service so we put @LoadBalance on it then it will work
			 
			 //ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotel/"+rating.getHotelId(), Hotel.class);

			// Hotel hotel = forEntity.getBody();
			 
			 /*below we use Feign Client*/
			 Hotel hotel = hotelService.getHotel(rating.getHotelId());  
			 
			 rating.setHotel(hotel);
			 return rating;
		 }).collect(Collectors.toList());
		 
		 
		 
		 user.setRatings(ratingList);
		 
		 return user;
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User delete(User user) {
		return null;
	}

}
