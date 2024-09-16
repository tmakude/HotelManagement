package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.LoginDto;
import com.app.dto.Response;
import com.app.entity.User;
import com.app.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthenticateController {
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<Response> register(@RequestBody User user){
		
		Response response = userService.register(user);
		return ResponseEntity.status(response.getStatusCode()).body(response);
		
	}
	@PostMapping("/login")
	public ResponseEntity<Response> login(@RequestBody LoginDto loginrequest){
		
		Response response = userService.login(loginrequest);
		return ResponseEntity.status(response.getStatusCode()).body(response);
		
	}

}
