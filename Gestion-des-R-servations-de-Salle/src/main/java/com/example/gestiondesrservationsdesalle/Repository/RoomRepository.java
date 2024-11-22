package com.example.gestiondesrservationsdesalle.Repository;

import com.example.gestiondesrservationsdesalle.Entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {

    List<Room> findByCapacity(int capacity);

}