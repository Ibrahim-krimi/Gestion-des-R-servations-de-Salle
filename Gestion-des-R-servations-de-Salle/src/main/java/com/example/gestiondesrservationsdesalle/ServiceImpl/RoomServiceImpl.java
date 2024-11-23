package com.example.gestiondesrservationsdesalle.ServiceImpl;

import com.example.gestiondesrservationsdesalle.Entity.Room;
import com.example.gestiondesrservationsdesalle.Repository.RoomRepository;
import com.example.gestiondesrservationsdesalle.Service.RoomService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;


    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Room findRoomById(int id) {
        return null;
    }

    @Override
    public List<Room> findAllRooms() {
        return List.of();
    }

    @Override
    public Room save(Room room) {
        return null;
    }

    @Override
    public void delete(Room room) {

    }

    @Override
    public Room update(Integer id, Room room) {
        return null;
    }
}
