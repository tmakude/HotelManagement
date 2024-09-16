package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.app.dto.BookingDto;
import com.app.dto.Response;
import com.app.entity.Booking;
import com.app.entity.Room;
import com.app.entity.User;
import com.app.exception.Exceptions;
import com.app.repository.BookingRepository;
import com.app.repository.RoomRepository;
import com.app.repository.UserRepository;
import com.app.utils.Utils;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	private RoomService roomService;
	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public Response saveBooking(Long roomId, Long userId, Booking bookingRequest) {
		
		Response response = new Response();
		 try {
			 if(bookingRequest.getCheckOutDate().isBefore(bookingRequest.getCheckInDate())){
				 throw new IllegalArgumentException("Check in date must be brfore Check out date");
			 }
			 Room room = roomRepository.findById(roomId).orElseThrow(()->new Exceptions("Room Not Found"));
			 User user = userRepository.findById(userId).orElseThrow(()->new Exceptions("User Not Found"));
			
			List<Booking> existingBookings = room.getBooking();
			
			if(!roomIsAvailable(bookingRequest , existingBookings)) {
				throw new Exceptions("Room Not Available for selected date range .");
			}
			bookingRequest.setRoom(room);
			bookingRequest.setUser(user);
			String bookingConfirmationCode = Utils.generateRandomALPHANUMERIC(10);
			bookingRepository.save(bookingRequest);
			response.setStatusCode(200);
			response.setMessage("Successful");
			response.setBookingConformationTime(bookingConfirmationCode);
			
				
			}catch(Exceptions e)
			{
				response.setStatusCode(404);
				response.setMessage(e.getMessage());
				
			}
			catch(Exception e) {
				response.setStatusCode(500);
				response.setMessage("Error getting At deleting the Room !!"+e.getMessage());
				
			}
			return response;
	}

	

	@Override
	public Response findBookingByConfirmationCode(String confirmationCode) {
		Response response = new Response();
		try {
			Booking booking = bookingRepository.findByBookingConfirmationCode(confirmationCode).orElseThrow(()->new Exceptions("Booking Not Found"));
			BookingDto bookingDto = Utils.mapBookingEntityToBookingDTO(booking);
			
			response.setStatusCode(200);
			response.setMessage("Successful");
			response.setBooking(bookingDto);
				
			}catch(Exceptions e)
			{
				response.setStatusCode(404);
				response.setMessage(e.getMessage());
				
			}
			catch(Exception e) {
				response.setStatusCode(500);
				response.setMessage("Error getting At getting the Room !!"+e.getMessage());
				
			}
		return response;
		
	}

	@Override
	public Response getAllBooking() {
		
		Response response = new Response();
		try {
			List<Booking> listBooking = bookingRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
			List<BookingDto> listdtoBooking=Utils.mapBookingListEntitytoBookingListDto(listBooking);
			response.setStatusCode(200);
			response.setMessage("Successful");
			response.setBookingList(listdtoBooking);
				
			}catch(Exceptions e)
			{
				response.setStatusCode(404);
				response.setMessage(e.getMessage());
				
			}
			catch(Exception e) {
				response.setStatusCode(500);
				response.setMessage("Error getting At deleting the Room !!"+e.getMessage());
				
			}
		return response;
	}

	@Override
	public Response cancelBooking(Long bookingId) {
		
		Response response = new Response();
		 try {
			 roomRepository.findById(bookingId).orElseThrow(()->new Exceptions("Room Not Found"));
			
			 roomRepository.deleteById(bookingId);
			 response.setStatusCode(200);
			 response.setMessage("Successful");
				
				
			}catch(Exceptions e)
			{
				response.setStatusCode(404);
				response.setMessage(e.getMessage());
				
			}
			catch(Exception e) {
				response.setStatusCode(500);
				response.setMessage("Error getting At Canceling the Booking !!"+e.getMessage());
				
			}
			return response;
	}
	
	//Room Available or Not Check method
	
	private boolean roomIsAvailable(Booking bookingRequest, List<Booking> existingBookings) {
		
		return existingBookings.stream().noneMatch(existingBooking->
		bookingRequest.getCheckInDate().equals(existingBooking.getCheckInDate())
		||bookingRequest.getCheckOutDate().isBefore(existingBooking.getCheckOutDate())
		||(bookingRequest.getCheckInDate().isAfter(existingBooking.getCheckInDate())
		&&bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckOutDate()))
		||(bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckInDate())
		
		&&bookingRequest.getCheckOutDate().equals(existingBooking.getCheckOutDate()))
		||(bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckInDate())
				
		&&bookingRequest.getCheckOutDate().isAfter(existingBooking.getCheckOutDate()))
		
		||(bookingRequest.getCheckInDate().equals(existingBooking.getCheckOutDate())
		&&bookingRequest.getCheckOutDate().equals(existingBooking.getCheckInDate()))
		
		||(bookingRequest.getCheckInDate().equals(existingBooking.getCheckOutDate())
		&&bookingRequest.getCheckOutDate().equals(bookingRequest.getCheckInDate()))
				
		);
		
	}

}
