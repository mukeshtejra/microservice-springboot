package com.springboot.userservice.userservice;

import java.util.List;

import com.springboot.userservice.entities.User;

public interface UserService {
	
	User save(User user);
	
	User getUser(String userId);
	
	List<User> getAllUsers();
	
	User delete(User user);
	

}
