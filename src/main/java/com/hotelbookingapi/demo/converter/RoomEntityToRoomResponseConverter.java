package com.hotelbookingapi.demo.converter;

import org.springframework.core.convert.converter.Converter;

import com.hotelbookingapi.demo.entity.Room;
import com.hotelbookingapi.demo.model.response.RoomResponse;

public class RoomEntityToRoomResponseConverter implements Converter<Room, RoomResponse> {
	public RoomResponse convert(Room source) {
		RoomResponse roomResponse = new RoomResponse();

		roomResponse.setId(source.getId());
		roomResponse.setRoomNumber(source.getRoomNumber());
		roomResponse.setPrice(source.getPrice());
		roomResponse.setStatus(source.getStatus());

		return roomResponse;
	}
}
