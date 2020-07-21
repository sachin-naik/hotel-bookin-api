package com.hotelbookingapi.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.hotelbookingapi.demo.entity.User;

public interface UserRepository extends CrudRepository<User, Long>{

}
