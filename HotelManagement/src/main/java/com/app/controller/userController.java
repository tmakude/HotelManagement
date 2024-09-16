package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Response> getAllusers(){
		
		Response response = userService.getAllUsers();
		return ResponseEntity.status(response.getStatusCode()).body(response);
	}
	
	@GetMapping("/get-by-id/{userId}")
	public ResponseEntity<Response> getUserById(@PathVariable("userId") Long userId){
		
		Response response = userService.getUserById(userId);
		return ResponseEntity.status(response.getStatusCode()).body(response);
	}
	
	@DeleteMapping("/deleteId/{userId}")
	public ResponseEntity<Response> deleteUserById(@PathVariable("userId") Long userId){
		
		Response response = userService.deleterUser(userId);
		return ResponseEntity.status(response.getStatusCode()).body(response);
	}
	
	@GetMapping("/get-logged-in-profile-info")
	public ResponseEntity<Response> getLoginInUserProfile(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		Response response = userService.getMyInfo(email);
		return ResponseEntity.status(response.getStatusCode()).body(response);
	}
	
	@GetMapping("/get-user-bookings/{userId}")
	public ResponseEntity<Response> getUserBookingHistory(@PathVariable("userId") Long userId ){
		
		Response response = userService.getUsersBookingHistory(userId);
		return ResponseEntity.status(response.getStatusCode()).body(response);
	}
	
	

}
