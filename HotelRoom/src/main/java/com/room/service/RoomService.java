package com.room.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.room.entity.Room;
import com.room.exception.RoomNotFoundException;
import com.room.repository.RoomRepository;

@Service
public class RoomService implements IRoomService {

	@Autowired
	private RoomRepository roomRepository;

	@Override
	public Room addRoom(Room room) {
		return roomRepository.save(room);
	}

	@Override
	public Room updateRoom(Room room) throws RoomNotFoundException {
		roomRepository.findById(room.getRoomNo()).orElseThrow(() -> new RoomNotFoundException("Room not found"));
		return roomRepository.save(room);
	}

	@Override
	public Room updateRoomById(int roomNo, String roomStatus) throws RoomNotFoundException {
		Room r1 = roomRepository.findById(roomNo).orElseThrow(() -> new RoomNotFoundException("Room not found"));
		r1.setRoomStatus(roomStatus);
		return roomRepository.save(r1);
	}

	@Override
	public List<Room> getAllRoom() {
		return roomRepository.findAll();
	}

	@Override
	public Room getRoomById(int roomNo) throws RoomNotFoundException {
		try {
			return roomRepository.findById(roomNo).get();
		} catch (Exception e) {
			throw new RoomNotFoundException("Room not found");
		}
	}

	@Override
	public void deleteRoom(int roomNo) throws RoomNotFoundException {
		roomRepository.findById(roomNo).orElseThrow(() -> new RoomNotFoundException("Room not found"));
		roomRepository.deleteById(roomNo);
	}

}
