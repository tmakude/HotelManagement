package com.app.dto;

import java.util.ArrayList;
import java.util.List;

import com.app.entity.Booking;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
	
	private Long id;
	private String email;
	private String name;
	private String phoneNumber;
	private String role;
	
	private List<BookingDto> booking = new ArrayList<>();

}
