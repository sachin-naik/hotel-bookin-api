package com.hotelbookingapi.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hotelbookingapi.demo.entity.Room;
import com.hotelbookingapi.demo.model.request.RoomRequest;
import com.hotelbookingapi.demo.repository.RoomRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="room", description="Operations pertaining to hotel rooms")
@RequestMapping("/api/rooms")
@CrossOrigin
public class RoomController {
	
	@Autowired
    RoomRepository roomRepository;
	
	/**
	 * Method to get list of rooms
	 * @return
	 */
	@ApiOperation(value = "View a list of rooms")
	@RequestMapping(path = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Room>> getRooms() {

        List<Room> roomList = new ArrayList<>();
        for(Room room: roomRepository.findAll()) {
        	roomList.add(room);
        }

        return new ResponseEntity<>(roomList, HttpStatus.OK);
    }
	
	/**
	 * Method to get room by room id
	 * @param roomId
	 * @return
	 */
	@ApiOperation(value = "View a room by room id")
	@RequestMapping(path = "/{roomId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Room> getRoomById(@PathVariable Long roomId) {

		Room room = roomRepository.findById(roomId).get();

		return new ResponseEntity<>(room, HttpStatus.OK);
	}
	
	/**
	 * Method to create room
	 * @param roomReq
	 * @return
	 */
	@ApiOperation(value = "Create new room")
	@RequestMapping(path = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Room> createRoom(@RequestBody RoomRequest roomReq) {
		Room room = new Room();
		room.setPrice(roomReq.getPrice());
		room.setRoomNumber(roomReq.getRoomNumber());
		roomRepository.save(room);
		return new ResponseEntity<>(room, HttpStatus.CREATED);
	}
	
	/**
	 * Method to delete room by room id
	 * @param roomId
	 * @return
	 */
	@ApiOperation(value = "Delete a room")
	@RequestMapping(path = "/delete/{roomId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> deleteRoomById(@PathVariable Long roomId) {
		roomRepository.deleteById(roomId);
		return new ResponseEntity<>("Deleted", HttpStatus.OK);
	}

}
