package com.hotelbookingapi.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hotelbookingapi.demo.entity.Booking;
import com.hotelbookingapi.demo.entity.Room;
import com.hotelbookingapi.demo.entity.User;
import com.hotelbookingapi.demo.model.request.BookingRequest;
import com.hotelbookingapi.demo.model.response.BookingResponse;
import com.hotelbookingapi.demo.repository.HotelBookingRepository;
import com.hotelbookingapi.demo.repository.RoomRepository;
import com.hotelbookingapi.demo.repository.UserRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/bookings")
@Api(value="hotelbooking", description="Operations pertaining to hotel room booking")
@CrossOrigin
public class HotelBookingController {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    ConversionService conversionService;

    @Autowired
    HotelBookingRepository hotelBookingRepository;
    
    @Autowired
    UserRepository userRepository;

    /**
     * Method to return list of bookings
     */
    @ApiOperation(value = "View a list of available bookings")
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Booking>> getAllBookings(){
    	List<Booking> bookingsList = new ArrayList<>();
    	for (Booking booking: hotelBookingRepository.findAll()) {
    		bookingsList.add(booking);
    	}
    	
    	return new ResponseEntity<>(bookingsList, HttpStatus.OK);
    }
    
    /**
     * Method to return booking by booking id.
     * @param bookingId
     * @return
     */
    @ApiOperation(value = "View a booking by booking id")
    @RequestMapping(path = "/{bookingId}", method = RequestMethod.GET)
    public ResponseEntity<Booking> getBookingById( @PathVariable Long bookingId ){
    	Booking booking = hotelBookingRepository.findById(bookingId).get();
    	
    	return new ResponseEntity<>(booking, HttpStatus.OK);
    }
    
    /**
     * Method to create new booking
     * @param bookingRequest
     * @return
     */
    @ApiOperation(value = "Create new booking")
	@RequestMapping(path = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<BookingResponse> createBooking(@RequestBody BookingRequest bookingRequest) {

		Booking booking = conversionService.convert(bookingRequest, Booking.class);
		hotelBookingRepository.save(booking);

		Optional<Room> RoomOptional = roomRepository.findById(bookingRequest.getRoomId());

		Room room = RoomOptional.get();
		User user = userRepository.findById(bookingRequest.getUserId()).get();
		if (user.getBonus() >= room.getPrice()) {
			room.setStatus("BOOKED");
			user.setBonus(user.getBonus() - room.getPrice());
			userRepository.save(user);
		} else {
			room.setStatus("PENDING APPROVAL");
		}
		room.addBooking(booking);
		roomRepository.save(room);
		booking.setRoom(room);

		BookingResponse BookingResponse = conversionService.convert(booking, BookingResponse.class);

		return new ResponseEntity<>(BookingResponse, HttpStatus.CREATED);
	}

}
