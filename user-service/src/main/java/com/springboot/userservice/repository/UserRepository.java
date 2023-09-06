package com.springboot.userservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.userservice.entities.User;

public interface UserRepository extends JpaRepository<User, String>{
	
	//public List<User> getAllusers();
	
	//public User getSingleUser();
	
	 

}
