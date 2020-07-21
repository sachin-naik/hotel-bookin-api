package com.hotelbookingapi.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.hotelbookingapi.demo.entity.Room;

public interface RoomRepository extends CrudRepository<Room, Long> {

}
