package com.springboot.ratingservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.ratingservice.entities.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, String> {
	
	//Custom Methods
	
	List<Rating> findByUserId(String userId);
	
	List<Rating> findByHotelId(String hotelId);

}
