package com.app.service;

import com.app.dto.Response;
import com.app.entity.Booking;

@Service
public class BookingServiceImpl implements BookingService {

	@Override
	public Response saveBooking(Long roomId, Long userId, Booking bookingRequest) {
		
		return null;
	}

	@Override
	public Response findBookingByConfirmationCode(String confirmationCode) {
		
		return null;
	}

	@Override
	public Response getAllBooking() {
		
		return null;
	}

	@Override
	public Response cancelBooking(Long bookingId) {
		
		return null;
	}

}
