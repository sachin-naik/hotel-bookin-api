package com.hotelbookingapi.demo.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.Data;

@Entity
@Data
@Table(name="BOOKINGS")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
    private LocalDate checkin;

    @NotNull
    private LocalDate checkout;
    
    @NotNull
    private Long userId;
    
    @NotNull
    private Long roomIdentifier;

    @ManyToOne
    private Room room;

    public Booking() {
    }

    public Booking(LocalDate checkin, LocalDate checkout, Room room) {
        this.checkin = checkin;
        this.checkout = checkout;
        this.room = room;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getCheckin() {
		return checkin;
	}

	public void setCheckin(LocalDate checkin) {
		this.checkin = checkin;
	}

	public LocalDate getCheckout() {
		return checkout;
	}

	public void setCheckout(LocalDate checkout) {
		this.checkout = checkout;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoomId() {
		return roomIdentifier;
	}

	public void setRoomId(Long roomId) {
		this.roomIdentifier = roomId;
	}
}
