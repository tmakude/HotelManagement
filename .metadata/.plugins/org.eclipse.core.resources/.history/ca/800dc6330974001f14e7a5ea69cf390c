package com.app.service;

import com.app.dto.LoginDto;
import com.app.dto.Response;
import com.app.entity.User;

public interface UserService {
	
	Response register(User user);
	Response login(LoginDto loginRequest);
	Response getAllUsers();
	Response getUsersBookingHistory(String UserId);
	Response deleterUser(Long UserId);
	Response getUserById(Long UserId);
	Response getMyInfo(String UserId);

}
