package com.hotelbookingapi.demo.model.request;

import lombok.Data;

@Data
public class RoomRequest {

	private Integer roomNumber;
	private Integer price;
	
	public Integer getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
}
