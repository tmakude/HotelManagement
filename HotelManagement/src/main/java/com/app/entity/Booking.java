package com.app.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Booking {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "CheckIn Date is required")
	private LocalDate checkInDate;
	@Future(message = "CheckOut Date Must be in Future")
	private LocalDate checkOutDate;
	
	@Min(value = 1 , message = "Minimum 1 Adult is required")
	private int numberOfAdults;
	private int numberOfChildren;
	private int numberOfGuest;
	private String bookingConfirmationCode;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name ="User_id")
	private User user;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="Room_id")
	private Room room;
	
	public void calculateNumberOfGuest() {
		
		this.numberOfGuest=this.numberOfAdults+this.numberOfChildren;
		
	}
	
	public void setNumberOfAdults(int setAdults) {
		this.numberOfAdults=setAdults;
		calculateNumberOfGuest();
	}
	
	public void setNumberOfChildren(int setChildren) {
		this.numberOfAdults=setChildren;
		calculateNumberOfGuest();
	}
	
	

}
