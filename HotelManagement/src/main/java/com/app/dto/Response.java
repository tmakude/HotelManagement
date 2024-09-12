package com.app.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
	
	private int statusCode;
	private String message;
	
	private String token;
	private String role;
	private String expirationTime;
	private String bookingConformationTime;
	
	private UserDto user;
	private RoomDto room;
	private BookingDto booking;
	private List<UserDto> userList;
	private List<RoomDto> roomList;
	private List<BookingDto> bookingList;

}
