package com.springboot.hotel.service.controller;

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

import com.springboot.hotel.service.entities.Hotel;
import com.springboot.hotel.service.services.HotelServices;

@RestController
@RequestMapping("/hotel")
public class HotelController {
	@Autowired
	private HotelServices hotelServices;

	@PostMapping
	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
		Hotel createHotel = hotelServices.createHotel(hotel);
		
		return new ResponseEntity<Hotel>(createHotel, HttpStatus.CREATED);

	}
	
	@GetMapping
	public ResponseEntity<List<Hotel>> getAllHotel(){
		List<Hotel> allHotels = hotelServices.getAllHotels();
		return new ResponseEntity<List<Hotel>>(allHotels,HttpStatus.OK);
		
	}
	@GetMapping("/{id}")
	public ResponseEntity<Hotel> getHotelDetail(@PathVariable String id){
		Hotel hotel = hotelServices.getHotel(id);
		return new ResponseEntity<Hotel>(hotel,HttpStatus.OK);
	}

}
