package com.springboot.hotel.service.services;

import java.util.List;

import com.springboot.hotel.service.entities.Hotel;

public interface HotelServices {
	
	//create
	public Hotel createHotel(Hotel hotel);
	
	//getAll
	
	public List<Hotel> getAllHotels();
	
	//get
	public Hotel getHotel(String id);
}
