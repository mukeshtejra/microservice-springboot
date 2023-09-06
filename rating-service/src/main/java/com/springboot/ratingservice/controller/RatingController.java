package com.springboot.ratingservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.ratingservice.entities.Rating;
import com.springboot.ratingservice.service.RatingService;

@RestController
@RequestMapping("/rating")
public class RatingController {

	@Autowired
	private RatingService ratingService;

	@PostMapping
	public ResponseEntity<Rating> CreateRating(@RequestBody Rating rating) {

		Rating ratingObj = ratingService.save(rating);
		return new ResponseEntity<Rating>(ratingObj, HttpStatus.CREATED);

	}

	@GetMapping
	public ResponseEntity<List<Rating>> getRatings() {

		List<Rating> allRatings = ratingService.getAllRatings();
		return new ResponseEntity<List<Rating>>(allRatings, HttpStatus.OK);

	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String userId) {

		List<Rating> allRatings = ratingService.getAllRatingsByUserId(userId);
		return new ResponseEntity<List<Rating>>(allRatings, HttpStatus.OK);

	}

	@GetMapping("/hotel/{hotelId}")
	public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable String hotelId) {

		List<Rating> allRatings = ratingService.getRatingByHotelId(hotelId);
		return new ResponseEntity<List<Rating>>(allRatings, HttpStatus.OK);

	}

}
