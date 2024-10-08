package com.app.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.internal.AWSS3V4Signer;
import com.app.dto.Response;
import com.app.dto.RoomDto;
import com.app.dto.UserDto;
import com.app.entity.Room;
import com.app.entity.User;
import com.app.exception.Exceptions;
import com.app.repository.BookingRepository;
import com.app.repository.RoomRepository;
import com.app.utils.Utils;

@Service
public class RoomServiceImpl implements RoomService{

	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	
	@Override
	public Response addNewRoom(MultipartFile photo, String roomType, BigDecimal roomPrice, String description) {
		
		Response response = new Response();
		 try {
			//convert the MultipartFile to byte Array
				byte[] imageBytes = photo.getBytes();
				// Encode the byte array as a base64 string
		        String base64Image = Base64.getEncoder().encodeToString(imageBytes);
		        
		        Room room = new Room();
		        room.setRoomPhotoUrl(base64Image);
		        room.setRoomType(roomType);
		        room.setRoomDescription(description);
		        room.setRoomPrice(roomPrice);
		        
		        Room rooms =roomRepository.save(room);
		        RoomDto roomDto =Utils.mapRoomEntityToRoomDto(rooms);
		        
		        response.setStatusCode(200);
		        response.setMessage("Successfull");
		        response.setRoom(roomDto);
			 
			
				
		 }catch(Exception e) {
				response.setStatusCode(500);
				response.setMessage("Error saving Room "+e.getMessage());
				
			}
			return response;
	}

	@Override
	public List<String> getAllRoomTypes() {
		
		return roomRepository.findDistinctRoomTypes();
	}

	@Override
	public Response getAllRooms() {
		
		Response response = new Response();
		 try {
			
			List<Room> roomList = roomRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
			
			List<RoomDto> roomDtoList = Utils.mapRoomListEntitytoRoomListDto(roomList);
			response.setStatusCode(200);
	        response.setMessage("Successfull");
	        response.setRoomList(roomDtoList);
			
				
		 }catch(Exception e) {
				response.setStatusCode(500);
				response.setMessage("Error saving Room "+e.getMessage());				
		}
			return response;
	}

	@Override
	public Response deleteRoom(Long roomId) {
		
		Response response = new Response();
		 try {
			 roomRepository.findById(roomId).orElseThrow(()->new Exceptions("Room Not Found"));
			
			 roomRepository.deleteById(roomId);
			 response.setStatusCode(200);
			 response.setMessage("Successful");
				
				
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
	public Response updateRoom(Long roomId,String description, String roomType, BigDecimal roomPrice, MultipartFile photo) {
		Response response = new Response();
		 try {
			 
			 
				    //convert the MultipartFile to byte Array
					byte[] imageBytes = photo.getBytes();
					// Encode the byte array as a base64 string
			        String base64Image = Base64.getEncoder().encodeToString(imageBytes);
			
			 Room room = roomRepository.findById(roomId).orElseThrow(()->new Exceptions("Room Not Found"));
			
			 if(roomType != null)room.setRoomType(roomType);
			 if(roomPrice != null)room.setRoomPrice(roomPrice);
			 if(description != null)room.setRoomDescription(description);
			 if(photo !=null)room.setRoomPhotoUrl(base64Image);
			 
			 Room roomUpdate = roomRepository.save(room);
			 RoomDto roomDto = Utils.mapRoomEntityToRoomDto(roomUpdate);
			 response.setStatusCode(200);
		     response.setMessage("Successfull");
		     response.setRoom(roomDto);
			 
				
			}
		 catch(Exceptions e)
			{
				response.setStatusCode(404);
				response.setMessage(e.getMessage());
				
			}
			catch(Exception e) {
				response.setStatusCode(500);
				response.setMessage("Error getting At saving the Room !!"+e.getMessage());
				
			}
			return response;
		 
			
		
	}

	@Override
	public Response getRoomById(Long roomId) {
		

		Response response = new Response();
		 try {
			 Room room = roomRepository.findById(roomId).orElseThrow(()->new Exceptions("Room Not Found"));
			 RoomDto roomDto = Utils.mapRoomEntityToRoomDto(room);
			 response.setStatusCode(200);
			 response.setMessage("Successful");
			 response.setRoom(roomDto);
			
				
				
			}catch(Exceptions e)
			{
				response.setStatusCode(404);
				response.setMessage(e.getMessage());
				
			}
			catch(Exception e) {
				response.setStatusCode(500);
				response.setMessage("Error getting At Saving Room!!"+e.getMessage());
				
			}
			return response;
	}

	@Override
	public Response getAvailableRoomsByDataAndType(LocalDate checkInDate, LocalDate checkOutDate, String roomType) {
		
		Response response = new Response();
		 try {
			 List<Room> list = roomRepository.findAvailableRoomsByDatesAndTypes(checkInDate, checkOutDate, roomType);
			 List<RoomDto> listDto = Utils.mapRoomListEntitytoRoomListDto(list);
			 response.setStatusCode(200);
			 response.setMessage("Successful");
			 response.setRoomList(listDto);
			
				
				
			}catch(Exceptions e)
			{
				response.setStatusCode(404);
				response.setMessage(e.getMessage());
				
			}
			catch(Exception e) {
				response.setStatusCode(500);
				response.setMessage("Error getting At getting Room!!"+e.getMessage());
				
			}
			return response;
	}

	@Override
	public Response getAllAvailableRooms() {
		
		Response response = new Response();
		 try {
			 List<Room> list = roomRepository.findAll();
			 List<RoomDto> listDto = Utils.mapRoomListEntitytoRoomListDto(list);
			 response.setStatusCode(200);
			 response.setMessage("Successful");
			 response.setRoomList(listDto);
			
				
				
			}catch(Exceptions e)
			{
				response.setStatusCode(404);
				response.setMessage(e.getMessage());
				
			}
			catch(Exception e) {
				response.setStatusCode(500);
				response.setMessage("Error getting At getting Room!!"+e.getMessage());
				
			}
			return response;
	}

}
