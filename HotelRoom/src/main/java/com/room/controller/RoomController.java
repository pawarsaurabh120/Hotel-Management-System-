package com.room.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.room.entity.Room;
import com.room.exception.RoomNotFoundException;
import com.room.service.IRoomService;

@RestController
@RequestMapping("/hotel")
@CrossOrigin("*")
public class RoomController{
	
	@Autowired
	private IRoomService iRoomService;
	
	@PostMapping("/room")
	public Room addRoom(@RequestBody Room room){ 
		return iRoomService.addRoom(room);
	}
	
	@PutMapping("/room")
	public Room updateRoom(@RequestBody Room room) throws RoomNotFoundException{
		return iRoomService.updateRoom(room);
	}
	
	@PutMapping("/room/{roomNo}/{roomStatus}")
	public Room updateRoomById(@PathVariable int roomNo, @PathVariable String roomStatus) throws RoomNotFoundException{
		return iRoomService.updateRoomById(roomNo, roomStatus);
	}
	
	@GetMapping("/room")
	public List<Room> getAllRoom(){
		return iRoomService.getAllRoom();
	}
	
	@GetMapping("/room/{roomNo}")
	public Room getRoomById(@PathVariable int roomNo) throws RoomNotFoundException {
		return iRoomService.getRoomById(roomNo);
	}
	
	@DeleteMapping("/room/{roomNo}")
	public void deleteRoom(@PathVariable int roomNo) throws RoomNotFoundException{
		iRoomService.deleteRoom(roomNo);
	}

}
