package com.app.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.Response;
import com.app.service.BookingService;
import com.app.service.RoomService;

@RestController
@RequestMapping("/rooms")
public class RoomController {
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private BookingService bookingService;
	
	@PostMapping("/add")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Response> addNewRoom(
			@RequestParam(value = "photo", required = false)MultipartFile photo,
			@RequestParam(value = "roomType", required = false)String roomType,
			@RequestParam(value = "roomPrice", required = false)BigDecimal roomPrice,
			@RequestParam(value = "roomDescription", required = false)String roomDescription		
   		){
		
		if(photo == null || photo.isEmpty() || roomType.isEmpty() || roomPrice == null ||roomType.isBlank()) {
			
			Response response = new Response();
			response.setStatusCode(400);
			response.setMessage("Plese provide values for all fields(photo, roomType ,roomPrice)");
			
		}
		Response response = roomService.addNewRoom(photo, roomType, roomPrice, roomDescription);
		return ResponseEntity.status(response.getStatusCode()).body(response);

	}
	
	@GetMapping("/all")
	public ResponseEntity<Response> getAllusers(){
		
		Response response = roomService.getAllRooms();
		return ResponseEntity.status(response.getStatusCode()).body(response);
	}
	
	@GetMapping("/types")
	public List<String> getRoomtypes(){
		
		return roomService.getAllRoomTypes();
	}
	
	@GetMapping("/room-by-id/{roomId}")
	public ResponseEntity<Response> getRoomById(@PathVariable("roomId") Long roomId){
		
		Response response = roomService.getRoomById(roomId);
		return ResponseEntity.status(response.getStatusCode()).body(response);
	}
	
	@GetMapping("/all-available-rooms")
	public ResponseEntity<Response> getAvailableRooms(){
		
		Response response = roomService.getAllAvailableRooms();
		return ResponseEntity.status(response.getStatusCode()).body(response);
	}
	
	@GetMapping("/available-rooms-by-date-and-type")
	public ResponseEntity<Response> getAvailableRoomsByDateAndTime(
			@RequestParam( required = false)@DateTimeFormat(iso =ISO.DATE) LocalDate checkInDate,
			@RequestParam(required = false)@DateTimeFormat(iso =ISO.DATE) LocalDate checkOutDate,			
			@RequestParam(required = false)String roomType		
   		){
		
		if(checkInDate == null || roomType == null || roomType.isBlank() || checkOutDate == null ){
			
			Response response = new Response();
			response.setStatusCode(400);
			response.setMessage("Plese provide values for all fields(checkInDate, roomType ,checkOutDate)");
			return ResponseEntity.status(response.getStatusCode()).body(response);
			
		}
		Response response = roomService.getAvailableRoomsByDataAndType(checkInDate, checkOutDate, roomType);
		return ResponseEntity.status(response.getStatusCode()).body(response);

	}
	
	@PostMapping("/update/{roomId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Response> addNewRoom(@PathVariable Long id ,
			@RequestParam(value = "photo", required = false)MultipartFile photo,
			@RequestParam(value = "roomType", required = false)String roomType,
			@RequestParam(value = "roomPrice", required = false)BigDecimal roomPrice,
			@RequestParam(value = "roomDescription", required = false)String roomDescription		
   		){
		
				Response response = roomService.updateRoom(id, roomDescription, roomType, roomPrice, photo);
		return ResponseEntity.status(response.getStatusCode()).body(response);

	}
	
	@DeleteMapping("/deleteId/{roomId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Response> deleteRoomById(@PathVariable("roomId") Long roomId){
		
		Response response = roomService.deleteRoom(roomId);
		return ResponseEntity.status(response.getStatusCode()).body(response);
	}
	
	
	

}
