package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.User;
import com.app.repository.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	public Long addUser(User user) {
		
		User newuser= userRepo.save(user);
		
		System.out.println("User :"+newuser);
		return  newuser.getId();
		
		
	}
	
	public User getUser(Long id) {
		
		return userRepo.findById(id).orElseThrow();
	}

}
