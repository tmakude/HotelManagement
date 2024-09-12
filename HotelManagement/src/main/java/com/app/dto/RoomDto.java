package com.app.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.app.entity.Booking;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoomDto {
	

	
    private Long id;
	private String roomType;
	private String roomPrice;
	private String roomPhotoUrl;
	private String roomDescription;
	private List<BookingDto> booking ;

}
