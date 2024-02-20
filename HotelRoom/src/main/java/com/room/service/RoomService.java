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
	public Room addRoom(Room room) throws RoomNotFoundException {
		List<Room> list = roomRepository.findAll();
		for (Room r : list) {
			if (r.getId() == room.getId()) {
				throw new RoomNotFoundException("Room already exists...");
			}
		}
		return roomRepository.save(room);
	}

	@Override
	public Room updateRoom(Room room) throws RoomNotFoundException {
		roomRepository.findById(room.getId()).orElseThrow(() -> new RoomNotFoundException("Room not found"));
		return roomRepository.save(room);
	}

	@Override
	public Room updateRoomById(int id, String roomStatus) throws RoomNotFoundException {
		Room r1 = roomRepository.findById(id).orElseThrow(() -> new RoomNotFoundException("Room not found"));
		r1.setRoomStatus(roomStatus);
		return roomRepository.save(r1);
	}

	@Override
	public List<Room> getAllRoom() {
		return roomRepository.findAll();
	}

	@Override
	public Room getRoomById(int id) throws RoomNotFoundException {
		try {
			return roomRepository.findById(id).get();
		} catch (Exception e) {
			throw new RoomNotFoundException("Room not found");
		}
	}
	
	public List<Room> getAllAvailableRooms() {
        return roomRepository.findByRoomStatus("Available");
    }

	@Override
	public void deleteRoom(int id) throws RoomNotFoundException {
		roomRepository.findById(id).orElseThrow(() -> new RoomNotFoundException("Room not found"));
		roomRepository.deleteById(id);
	}

}
