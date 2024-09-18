package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.LoginDto;
import com.app.dto.Response;
import com.app.dto.UserDto;
import com.app.entity.User;
import com.app.exception.Exceptions;
import com.app.repository.UserRepository;
import com.app.utils.JWTUtils;
import com.app.utils.Utils;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JWTUtils jwtUtils;
	
	@Autowired
	private AuthenticationManager  authenticationManager;

	@Override
	public Response register(User user) {
		
		Response response = new Response();
		try {
			
			if(user.getRole() == null || user.getRole().isEmpty()) {
				user.setRole("USER");
			}
			if(userRepository.existsByEmail(user.getEmail())) {
				throw new Exceptions(user.getEmail()+ "Already Exists");
			}
			
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			User saveUser= userRepository.save(user);
			UserDto userDto = Utils.mapUserEntityToUserDTO(saveUser);
			response.setStatusCode(200);
			response.setUser(userDto);
			
		}catch(Exceptions e)
		{
			response.setStatusCode(400);
			response.setMessage(e.getMessage());
			
		}
		catch(Exception e) {
			response.setStatusCode(500);
			response.setMessage("Error Occuring During the USER Resistration"+e.getMessage());
			
		}
		return response;
	}

	@Override
	public Response login(LoginDto loginRequest) {
		Response response = new Response();
        try {
			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
			
			User user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(()->new Exceptions("User Not Found"));
			
			String token = jwtUtils.generateJwtToken(user);
			response.setStatusCode(200);
			response.setToken(token);
			response.setRole(user.getRole());
			response.setExpirationTime("7 days");
			response.setMessage("Successful");
			
		}catch(Exceptions e)
		{
			response.setStatusCode(404);
			response.setMessage(e.getMessage());
			
		}
		catch(Exception e) {
			response.setStatusCode(500);
			response.setMessage("Error Occuring During the USER Login"+e.getMessage());
			
		}
		return response;
	}

	@Override
	public Response getAllUsers() {
		
		Response response = new Response();
		 try {
			 
			 List<User> userList = userRepository.findAll();
			 List<UserDto> userDtoList=Utils.mapUserListEntityTouserListDTO(userList);
			 response.setStatusCode(200);
			 response.setMessage("Successful");
			 response.setUserList(userDtoList);
				
				
			}catch(Exceptions e)
			{
				response.setStatusCode(500);
				response.setMessage("Error getting all Users"+e.getMessage());
				
			}
			catch(Exception e) {
				response.setStatusCode(500);
				response.setMessage("Error getting all Users"+e.getMessage());
				
			}
			return response;
	}

	@Override
	public Response getUsersBookingHistory(Long UserId) {
		
		Response response = new Response();
		 try {
			 
			 User user = userRepository.findById(UserId).orElseThrow(()->new Exceptions("User Not Found"));
			 UserDto userDto = Utils.mapUserEntityToUserDTOPlusUserBookingAndRooms(user);
			 response.setStatusCode(200);
			 response.setMessage("Successful");
			 response.setUser(userDto);
				
				
			}catch(Exceptions e)
			{
				response.setStatusCode(404);
				response.setMessage(e.getMessage());
				
			}
			catch(Exception e) {
				response.setStatusCode(500);
				response.setMessage("Error getting all User Booking"+e.getMessage());
				
			}
			return response;
	}

	@Override
	public Response deleterUser(Long UserId) {
		
		Response response = new Response();
		 try {
			 userRepository.findById(UserId).orElseThrow(()->new Exceptions("User Not Found"));
			
			 userRepository.deleteById(Long.valueOf(UserId));
			 response.setStatusCode(200);
			 response.setMessage("Successful");
				
				
			}catch(Exceptions e)
			{
				response.setStatusCode(404);
				response.setMessage(e.getMessage());
				
			}
			catch(Exception e) {
				response.setStatusCode(500);
				response.setMessage("Error getting At deleting the error !!"+e.getMessage());
				
			}
			return response;
	}
	

	@Override
	public Response getUserById(Long UserId) {
	
		Response response = new Response();
		 try {
			 User user = userRepository.findById(UserId).orElseThrow(()->new Exceptions("User Not Found"));
			 UserDto userDto = Utils.mapUserEntityToUserDTO(user);
			 response.setStatusCode(200);
			 response.setMessage("Successful");
			 response.setUser(userDto);
			
				
				
			}catch(Exceptions e)
			{
				response.setStatusCode(404);
				response.setMessage(e.getMessage());
				
			}
			catch(Exception e) {
				response.setStatusCode(500);
				response.setMessage("Error getting "+e.getMessage());
				
			}
			return response;
	}
	

	@Override
	public Response getMyInfo(String email) {
		
		Response response = new Response();
		 try {
			 
			 User user = userRepository.findByEmail(email).orElseThrow(()->new Exceptions("User Not Found"));
			 UserDto userDto = Utils.mapUserEntityToUserDTO(user);
			 response.setStatusCode(200);
			 response.setMessage("Successful");
			 response.setUser(userDto);
				
			}catch(Exceptions e)
			{
				response.setStatusCode(404);
				response.setMessage(e.getMessage());
				
			}
			catch(Exception e) {
				response.setStatusCode(500);
				response.setMessage("Error getting User "+e.getMessage());
				
			}
			return response;
	}
	}


