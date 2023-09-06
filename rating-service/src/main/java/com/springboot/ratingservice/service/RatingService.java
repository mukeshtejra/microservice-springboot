package com.springboot.ratingservice.service;

import java.util.List;

import com.springboot.ratingservice.entities.Rating;

public interface RatingService {
	
	public Rating save(Rating rating);

	public List<Rating> getAllRatings();

	public List<Rating> getAllRatingsByUserId(String userId);

	public List<Rating> getRatingByHotelId(String hotelId);
}
