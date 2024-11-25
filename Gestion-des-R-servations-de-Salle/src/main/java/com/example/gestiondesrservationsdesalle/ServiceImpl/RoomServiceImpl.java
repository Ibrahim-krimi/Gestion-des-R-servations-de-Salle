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
        return this.roomRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Room> findAllRooms() {
        return this.roomRepository.findAll();
    }

    @Override
    public Room save(Room room) {
        return this.roomRepository.save(room);
    }

    @Override
    public void delete(Room room) {

        this.roomRepository.delete(room);
    }

    @Override
    public Room update(Integer id, Room room) {
       Room roomToUpdate =this.roomRepository.findById(id).orElseThrow();
       roomToUpdate.setName(room.getName());
       roomToUpdate.setDescription(room.getDescription());
       roomToUpdate.setCapacity(room.getCapacity());
       return this.roomRepository.save(roomToUpdate);
    }
}
