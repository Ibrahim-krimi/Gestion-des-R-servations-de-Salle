package com.example.gestiondesrservationsdesalle.service;

import com.example.gestiondesrservationsdesalle.Entity.Room;
import com.example.gestiondesrservationsdesalle.Repository.RoomRepository;
import com.example.gestiondesrservationsdesalle.ServiceImpl.RoomServiceImpl;
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
    private RoomServiceImpl roomService;

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
    public void WhenSaveRoomShouldReturnRoom() {
        //Given
        when(roomRepository.save(any(Room.class))).thenReturn(room);
        //When
        Room roomSaved = this.roomService.save(room);
        //then
        Assertions.assertNotNull(roomSaved);
        Assertions.assertEquals(room.getName(), roomSaved.getName());
    }

    @DisplayName("RoomService Test for Modifying room")
    @Test
    public void WhenUpdateRoomShouldReturnRoomUpdated() {
        // Given
        when(this.roomRepository.findById(any())).thenReturn(Optional.of(room));
        when(this.roomRepository.save(any(Room.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // When
        Room roomToUpdate = this.roomRepository.findById(1).orElseThrow(() -> new RuntimeException("Room not found"));
        roomToUpdate.setName("Bene Bene");

        Room roomUpdated = this.roomService.update(roomToUpdate.getId(), roomToUpdate);

        // Then
        Assertions.assertNotNull(roomUpdated, "Updated room should not be null");
        Assertions.assertEquals("Bene Bene", roomUpdated.getName(), "Room name should be updated");
    }



}
