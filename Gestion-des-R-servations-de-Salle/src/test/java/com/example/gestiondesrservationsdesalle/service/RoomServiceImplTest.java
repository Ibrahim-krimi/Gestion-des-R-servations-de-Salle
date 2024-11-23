package com.example.gestiondesrservationsdesalle.service;

import com.example.gestiondesrservationsdesalle.Entity.Room;
import com.example.gestiondesrservationsdesalle.Repository.RoomRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RoomServiceImplTest {

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private RoomService roomService;

    Room room;

    @BeforeEach
    public void setUp() {
        room = Room.builder()
                .name("Onizuka")
                .id(1)
                .capacity(50)
                .description("Onizuka Room devant Naha")
                .build();
    }

    @DisplayName("RoomSerice Test for saving room")
    @Test
    public void WhenSaveRoomShouldReturnRoom(){
        //Given
        when(this.roomRepository.save(room)).thenReturn(room);
        //When
        Room roomSaved = this.roomRepository.save(room);
        //then
        Assertions.assertNotNull(roomSaved);
        Assertions.assertEquals(room, roomSaved);
    }

    @DisplayName("RoomSerice Test for Modifying room")
    @Test
    public void WhenUpdateRoomShouldReturnRoomUpdated(){
        //Given
        when(this.roomRepository.save(room)).thenReturn(room);
        when(this.roomRepository.findById(any())).thenReturn(Optional.of(room));
        //When
        Room roomToUpdate = this.roomRepository.findById(1).orElse(null);

        roomToUpdate.setName("Bene Bene");

        Room roomUpdated = this.roomRepository.save(roomToUpdate);
        //then
        Assertions.assertNotNull(roomUpdated);
        Assertions.assertEquals(roomToUpdate.getName(), roomUpdated.getName());
    }



}
