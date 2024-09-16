package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.Response;
import com.app.service.UserService;

@RestController
@RequestMapping("/users")
public class userController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/all")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Response> getAllusers(){
		
		Response response = userService.getAllUsers();
		return ResponseEntity.status(response.getStatusCode()).body(response);
	}

}
