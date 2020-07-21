package com.hotelbookingapi.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.hotelbookingapi.demo.entity.Booking;

public interface HotelBookingRepository extends CrudRepository<Booking, Long> {

}
