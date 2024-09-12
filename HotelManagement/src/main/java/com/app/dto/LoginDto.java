package com.app.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginDto {
	
	@NotBlank(message = "Email is required")
	private String email;
	@NotBlank(message = "Email is required")
	private String password;

}
