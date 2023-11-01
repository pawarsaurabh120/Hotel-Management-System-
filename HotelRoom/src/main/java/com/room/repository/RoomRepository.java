package com.room.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.room.entity.Room;

@Repository
public interface RoomRepository extends MongoRepository<Room, Integer>{
	
	List<Room> findByRoomStatus(String roomStatus);


}
