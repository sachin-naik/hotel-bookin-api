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

import com.hotelbookingapi.demo.entity.User;
import com.hotelbookingapi.demo.model.request.UserRequest;
import com.hotelbookingapi.demo.repository.UserRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/users")
@Api(value="users", description="Operations pertaining to users")
@CrossOrigin
public class UserController {

	@Autowired
    UserRepository userRepository;
	
	/**
	 * Method to get list of users
	 * @return
	 */
	@ApiOperation(value = "View a list of available users")
	@RequestMapping(path = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<User>> getUsers() {

		List<User> userList = new ArrayList<>();
		for (User user : userRepository.findAll()) {
			userList.add(user);
		}

		return new ResponseEntity<>(userList, HttpStatus.OK);
	}
	
	/**
	 * Method to get user by user id
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "View a user by user id")
	@RequestMapping(path = "/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<User> getUserById(@PathVariable Long userId) {

		User user = userRepository.findById(userId).get();

		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	/**
	 * Method to create new user
	 * @param userReq
	 * @return
	 */
	@ApiOperation(value = "Create new user")
	@RequestMapping(path = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<User> createUser(@RequestBody UserRequest userReq) {
		User user = new User();
		user.setBonus(userReq.getBonus());
		user.setName(userReq.getName());
		userRepository.save(user);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}
	
	/**
	 * Method to delete user by user id
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "Delete a user")
	@RequestMapping(path = "/delete/{userId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> deleteUserById(@PathVariable Long userId) {
		userRepository.deleteById(userId);
		return new ResponseEntity<>("Deleted", HttpStatus.OK);
	}
}
