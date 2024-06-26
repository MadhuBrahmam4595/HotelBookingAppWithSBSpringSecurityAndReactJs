package com.app.model;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
@Entity
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String roomType;
	private BigDecimal roomPrice;
	private boolean isBooked = false;
	
	@OneToMany(mappedBy = "room" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<BookedRoom> bookings;
	
	@Lob
	private Blob photo;
	
	public Room() {
		this.bookings = new ArrayList<BookedRoom>();
	}

	 

	public Room(Long id, String roomType, BigDecimal roomPrice, boolean isBooked, List<BookedRoom> bookings,
			Blob photo) {
		super();
		this.id = id;
		this.roomType = roomType;
		this.roomPrice = roomPrice;
		this.isBooked = isBooked;
		this.bookings = bookings;
		this.photo = photo;
	}



	public Blob getPhoto() {
		return photo;
	}



	public void setPhoto(Blob photo) {
		this.photo = photo;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public BigDecimal getRoomPrice() {
		return roomPrice;
	}

	public void setRoomPrice(BigDecimal roomPrice) {
		this.roomPrice = roomPrice;
	}

	public boolean isBooked() {
		return isBooked;
	}

	public void setBooked(boolean isBooked) {
		this.isBooked = isBooked;
	}

	public List<BookedRoom> getBookings() {
		return bookings;
	}

	public void setBookings(List<BookedRoom> bookings) {
		this.bookings = bookings;
	}

	 
	
	public void addBooking(BookedRoom bookedRoom) {
		if(bookings == null) {
			bookings = new ArrayList<BookedRoom>();
		}
		bookings.add(bookedRoom);
		bookedRoom.setRoom(this);
		isBooked = true;
		String bookingcode = RandomStringUtils.randomNumeric(10);
		bookedRoom.setBookingConfirmCode(bookingcode);
		
	}



	@Override
	public String toString() {
		return "Room [id=" + id + ", roomType=" + roomType + ", roomPrice=" + roomPrice + ", isBooked=" + isBooked
				+ ", bookings=" + bookings + ", photo=" + photo + "]";
	}
	
	

}
