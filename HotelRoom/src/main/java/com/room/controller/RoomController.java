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
	
	@PostMapping("/room/add")
	public Room addRoom(@RequestBody Room room){ 
		return iRoomService.addRoom(room);
	}
	
	@PutMapping("/room/update")
	public Room updateRoom(@RequestBody Room room) throws RoomNotFoundException{
		return iRoomService.updateRoom(room);
	}
	
	@PutMapping("/room/{id}/{roomStatus}")
	public Room updateRoomById(@PathVariable int id, @PathVariable String roomStatus) throws RoomNotFoundException{
		return iRoomService.updateRoomById(id, roomStatus);
	}
	
	@GetMapping("/room/getAll")
	public List<Room> getAllRoom(){
		return iRoomService.getAllRoom();
	}
	
	@GetMapping("/room/getByRoomNo/{id}")
	public Room getRoomById(@PathVariable int id) throws RoomNotFoundException {
		return iRoomService.getRoomById(id);
	}
	
	@GetMapping("/room/avilableRooms")
	public List<Room> getAllAvailableRooms() {
        return iRoomService.getAllAvailableRooms();
    }
	
	@DeleteMapping("/room/delete/{id}")
	public void deleteRoom(@PathVariable int id) throws RoomNotFoundException{
		iRoomService.deleteRoom(id);
	}

}
