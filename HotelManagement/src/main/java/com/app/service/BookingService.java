package com.app.service;

import java.util.Optional;

import com.app.dto.Response;
import com.app.entity.Booking;

public interface BookingService {
	
	Response saveBooking(Long roomId , Long userId , Booking bookingRequest);
	
	Response findBookingByConfirmationCode(String confirmationCode);
	
	Response getAllBooking();
	
	Response cancelBooking(Long bookingId);
	

}
