package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.exception.Exceptions;
import com.app.repository.UserRepository;

@Service
public class CustomerUserDetailsService implements UserDetailsService {
	@Autowired
	public UserRepository  userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return userRepo.findByEmail(username).orElseThrow(()-> new Exceptions("User/Email not Found"));
	}

}
