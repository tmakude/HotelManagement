package com.app.utils;

import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;

import com.app.dto.BookingDto;
import com.app.dto.RoomDto;
import com.app.dto.UserDto;
import com.app.entity.Booking;
import com.app.entity.Room;
import com.app.entity.User;

public class Utils {
	
	private static final String ALPHANUMERIC_STRING = "ABCDEFGHIZKLMNOPQRSTUVWXYZ0123456789";
	
	private static final SecureRandom secureRandom = new SecureRandom();
	
	public static String generateRandomALPHANUMERIC(int length)
	{
		StringBuilder stringBuilder = new StringBuilder();
		for(int i = 0; i < length; i++) {
			
			int randomIndex =  secureRandom.nextInt(ALPHANUMERIC_STRING.length());
			char randomChar = ALPHANUMERIC_STRING.charAt(randomIndex);
			stringBuilder.append(randomChar);
		}
		
		return stringBuilder.toString();
	}
	
	public static RoomDto mapRoomEntityToRoomDto(Room room)
	{
		RoomDto roomDto = new RoomDto();
		
		roomDto.setId(room.getId());
		roomDto.setRoomType(room.getRoomType());
		roomDto.setRoomPrice(room.getRoomPrice());
		roomDto.setRoomPhotoUrl(room.getRoomPhotoUrl());
		roomDto.setRoomDescription(room.getRoomDescription());
		
		return roomDto;
		
	}
	
	public static BookingDto mapBookingEntityToBookingDTO(Booking booking)
	{
		BookingDto bookingDto = new BookingDto();
		
		bookingDto.setId(booking.getId());
		bookingDto.setCheckInDate(booking.getCheckInDate());
		bookingDto.setCheckOutDate(booking.getCheckOutDate());
		bookingDto.setNumberOfAdults(booking.getNumberOfAdults());
		bookingDto.setNumberOfChildren(booking.getNumberOfChildren());
		bookingDto.setNumberOfGuest(booking.getNumberOfGuest());
		bookingDto.setBookingConformationCode(booking.getBookingConfirmationCode());
		
		return bookingDto;
		
	}
	
	public static RoomDto mapRoomEntityToRoomDtoPlusBookings(Room room)
	{
		RoomDto roomDto = new RoomDto();
		
		roomDto.setId(room.getId());
		roomDto.setRoomType(room.getRoomType());
		roomDto.setRoomPrice(room.getRoomPrice());
		roomDto.setRoomPhotoUrl(room.getRoomPhotoUrl());
		roomDto.setRoomDescription(room.getRoomDescription());
		
		if(room.getBooking() != null) {
			
			roomDto.setBooking(room.getBooking().stream().map(Utils ::mapBookingEntityToBookingDTO).collect(Collectors.toList()));
		}
		return roomDto;
		
	}
	
	
	
	public static UserDto mapUserEntityToUserDTO(User user)
	{
		UserDto userDTO = new UserDto();
		
		userDTO.setId(user.getId());
		userDTO.setName(user.getName());
		userDTO.setEmail(user.getEmail());
		userDTO.setPhoneNumber(user.getPhoneNumber());
		userDTO.setRole(user.getRole());
		return userDTO;
	}
	
	public static UserDto mapUserEntityToUserDTOPlusUserBookingAndRooms(User user)
	{
		UserDto userDTO = new UserDto();
		
		userDTO.setId(user.getId());
		userDTO.setName(user.getName());
		userDTO.setEmail(user.getEmail());
		userDTO.setPhoneNumber(user.getPhoneNumber());
		userDTO.setRole(user.getRole());
		
		if(!user.getBookings().isEmpty())
		{
			userDTO.setBooking(user.getBookings().stream().map(booking ->mapBookingEntityToBookingDtoPlusBookingRoom(booking,false)).collect(Collectors.toList()));
		}
		return userDTO;
	}

	private static BookingDto mapBookingEntityToBookingDtoPlusBookingRoom(Booking booking, boolean mapUser) {
		
        BookingDto bookingDto = new BookingDto();
		
		bookingDto.setId(booking.getId());
		bookingDto.setCheckInDate(booking.getCheckInDate());
		bookingDto.setCheckOutDate(booking.getCheckOutDate());
		bookingDto.setNumberOfAdults(booking.getNumberOfAdults());
		bookingDto.setNumberOfChildren(booking.getNumberOfChildren());
		bookingDto.setNumberOfGuest(booking.getNumberOfGuest());
		bookingDto.setBookingConformationCode(booking.getBookingConfirmationCode());
		
		if(mapUser) {
			bookingDto.setUserDto(Utils.mapUserEntityToUserDTO(booking.getUser()));
		}
		
		if(booking.getRoom() != null) {
			
			RoomDto roomDto = new RoomDto();
			
			roomDto.setId(booking.getRoom().getId());
			roomDto.setRoomType(booking.getRoom().getRoomType());
			roomDto.setRoomPrice(booking.getRoom().getRoomPrice());
			roomDto.setRoomPhotoUrl(booking.getRoom().getRoomPhotoUrl());
			roomDto.setRoomDescription(booking.getRoom().getRoomDescription());
			bookingDto.setRoomDto(roomDto);
			
		}
		
		return bookingDto;
		
	}
	
	public static List<UserDto> mapUserListEntityTouserListDTO(List<User> userList){
		
		return userList.stream().map(Utils :: mapUserEntityToUserDTO).collect(Collectors.toList());
	}
	public static List<RoomDto> mapRoomListEntitytoRoomListDto(List<Room> roomList){
		
		return roomList.stream().map(Utils :: mapRoomEntityToRoomDto).collect(Collectors.toList());
	}
    public static List<BookingDto> mapBookingListEntitytoBookingListDto(List<Booking> roomList){
		
		return roomList.stream().map(Utils :: mapBookingEntityToBookingDTO).collect(Collectors.toList());
	}
	

}
