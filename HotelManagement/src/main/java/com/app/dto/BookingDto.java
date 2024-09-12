package com.app.dto;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.app.entity.Room;
import com.app.entity.User;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingDto {
	
    private Long id;
	
	
	private LocalDate checkInDate;
	
	private LocalDate checkOutDate;
	
	
	private int numberOfAdults;
	private int numberOfChildren;
	private int numberOfGuest;
	private String bookingConformationCode;
	
	private UserDto userDto;
	private RoomDto roomDto;

}
