package com.hotelbookingapi.demo.converter;

import org.springframework.core.convert.converter.Converter;

import com.hotelbookingapi.demo.entity.Booking;
import com.hotelbookingapi.demo.model.response.BookingResponse;

public class BookingEntityToBookingResponseConvertor implements Converter<Booking, BookingResponse> {
	@Override
	public BookingResponse convert(Booking source) {

		BookingResponse bookingResponse = new BookingResponse();
		bookingResponse.setCheckin(source.getCheckin());
		bookingResponse.setCheckout(source.getCheckout());
		bookingResponse.setUserId(source.getUserId());
		bookingResponse.setRoomId(source.getRoomId());

		if (null != source.getRoom())
			bookingResponse.setId(source.getRoom().getId());

		return bookingResponse;
	}
}
