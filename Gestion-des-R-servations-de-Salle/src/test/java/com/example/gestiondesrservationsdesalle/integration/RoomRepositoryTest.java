package com.example.gestiondesrservationsdesalle.integration;

import com.example.gestiondesrservationsdesalle.Entity.Employee;
import com.example.gestiondesrservationsdesalle.Entity.Room;
import com.example.gestiondesrservationsdesalle.Enum.RoleEmployee;
import com.example.gestiondesrservationsdesalle.Repository.RoomRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class RoomRepositoryTest {

    @Autowired
    private RoomRepository roomRepository;

    Room room;

    @BeforeEach
    public void setup(){

        //Given
        this.room = Room.builder()
                    .name("ROOM 43")
                    .description("Room 43  est en face de l'amphi 55")
                    .capacity(50).build();

    }

    @DisplayName("Junit Repo Test for creation of Room")
    @Test
    public void ShouldCreateRoom(){
        //when
        Room savedRoom = roomRepository.save(room);
        //then
        Assertions.assertNotNull(room);
        Assertions.assertNotEquals(0, savedRoom.getId());
    }

    @DisplayName("Junit Repo Test For Update of Room")
    @Test
    public void ShouldUpdateRoom(){
        // Given
        Room savedRoom = roomRepository.save(room);

        //when
        savedRoom.setCapacity(100);
        Room savedRoomSecondTime = roomRepository.save(savedRoom);
        //then

        Assertions.assertEquals(savedRoomSecondTime.getCapacity(), savedRoom.getCapacity());

    }

    @DisplayName("Junit Repo Test Should return List of Room Filtred By Capacity")
    @Test
    public void ShouldReturnListOfRoomFiltratedByCapacity()
    {
        //Given
        roomRepository.save(room);
        //When
        List<Room> rooms =roomRepository.findByCapacity(room.getCapacity());
        //Then
        rooms.forEach( roomFromTheList-> {
            Assertions.assertEquals(roomFromTheList.getCapacity(), room.getCapacity());
        });
    }

}
