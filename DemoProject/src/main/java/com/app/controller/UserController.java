package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.User;
import com.app.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/addUser")
	public ResponseEntity<String> addData(@RequestBody User user){
		
		Long id = userService.addUser(user);
		
		System.out.println("Id :"+id);
		
		return new  ResponseEntity<>("The user id Is : "+ id , HttpStatus.OK);
		
	}
	
	@GetMapping("/id")
    public ResponseEntity<String> getData(@PathVariable("id") Long id){
		
		User user = userService.getUser(id);
		
		return ResponseEntity.ok("User data is"+ user);
		
	}
	
	//http://localhost:8080/user/1
	
	
	

}
