package com.springboot.userservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.userservice.entities.User;
import com.springboot.userservice.userservice.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController {
	
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		 User user2 = userService.save(user);
		 
		 return new ResponseEntity<User>(user2,HttpStatus.CREATED);
		
	}
	
	int ratingCount;
	
	@GetMapping("/{userId}")
	//@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
	//@Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
	@RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallback")
	public ResponseEntity<User> getUser(@PathVariable String userId) {
		
		logger.info("{}",ratingCount);
		ratingCount++;
		User user = userService.getUser(userId);
		return new ResponseEntity<User>(user, HttpStatus.OK);
		
	}
	
	//create fallback method if rating service gets failed from above controller then fallback method would be called
	//have the same return type and param as per above controller
	public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex){
		logger.info("fallback is executed due to service down",ex.getMessage());
		User user = new User();
		user.setAbout("User is created dummy due to service down");
		user.setEmail("testdummy@gmail.com");
		user.setName("dummy user");
		user.setUserId("1234");
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers(){
		
		List<User> allUsers = userService.getAllUsers();
		return new ResponseEntity<List<User>>(allUsers,HttpStatus.OK);
		
	}

}
