package com.hotelbookingapi.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.hotelbookingapi.demo.entity.Room;
import com.hotelbookingapi.demo.entity.User;
import com.hotelbookingapi.demo.repository.RoomRepository;
import com.hotelbookingapi.demo.repository.UserRepository;

@Component
public class H2Initialize implements CommandLineRunner {
	@Autowired
	RoomRepository roomRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Bootstrapping data: ");
		
		roomRepository.save(new Room(405, 200));
		roomRepository.save(new Room(406, 220));
		roomRepository.save(new Room(407, 250));
		
		userRepository.save(new User("Sam", 300));
		userRepository.save(new User("John", 240));
		userRepository.save(new User("Sam", 190));
		
		Iterable<Room> itrRoom = roomRepository.findAll();
		
		System.out.println("Printing out Room Data ");
		for(Room room : itrRoom) {
			System.out.println("id: " + room.getId() + ", room no: " +room.getRoomNumber() + ", price: " + room.getPrice());
		}
		
		Iterable<User> itrUsr = userRepository.findAll();
		
		System.out.println("Printing out User Data ");
		for(User usr : itrUsr) {
			System.out.println("id: " + usr.getId() + ", user name: " +usr.getName() + ", bonus: " + usr.getBonus());
		}
	}
}
