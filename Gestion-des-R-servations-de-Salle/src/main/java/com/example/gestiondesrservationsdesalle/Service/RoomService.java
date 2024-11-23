package com.example.gestiondesrservationsdesalle.Service;

import com.example.gestiondesrservationsdesalle.Entity.Room;

import java.util.List;

public interface RoomService {
    public Room findRoomById(int id);
    public List<Room> findAllRooms();
    public Room save(Room room);
    public void delete(Room room);
    public Room update(Integer id,Room room);
}
