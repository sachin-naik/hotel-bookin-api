package com.hotelbookingapi.demo.converter;

import org.springframework.core.convert.converter.Converter;

import com.hotelbookingapi.demo.entity.Booking;
import com.hotelbookingapi.demo.model.request.BookingRequest;

public class BookingRequestToBookingEntityConverter implements Converter<BookingRequest, Booking> {

	@Override
	public Booking convert(BookingRequest source) {
		Booking booking = new Booking();
		booking.setCheckin(source.getCheckin());
		booking.setCheckout(source.getCheckout());
		booking.setUserId(source.getUserId());
		booking.setRoomId(source.getRoomId());

        return booking;
	}

}
