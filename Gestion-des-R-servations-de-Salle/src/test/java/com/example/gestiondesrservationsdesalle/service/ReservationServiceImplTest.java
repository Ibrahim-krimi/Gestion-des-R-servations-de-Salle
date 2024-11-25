package com.example.gestiondesrservationsdesalle.service;

import com.example.gestiondesrservationsdesalle.Entity.Employee;
import com.example.gestiondesrservationsdesalle.Entity.Reservation;
import com.example.gestiondesrservationsdesalle.Entity.Room;
import com.example.gestiondesrservationsdesalle.Enum.RoleEmployee;
import com.example.gestiondesrservationsdesalle.Repository.EmployeeRepository;
import com.example.gestiondesrservationsdesalle.Repository.ReservationRepository;
import com.example.gestiondesrservationsdesalle.Repository.RoomRepository;
import com.example.gestiondesrservationsdesalle.ServiceImpl.EmployeeServiceImpl;
import com.example.gestiondesrservationsdesalle.ServiceImpl.ReservationServiceImpl;
import com.example.gestiondesrservationsdesalle.ServiceImpl.RoomServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceImplTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    private Reservation reservation;
    private Room room;
    private Employee employee;
    private final String description = "Reservation pour le cour de Cloud";
    private Date dateDebut;
    private Date dateFin;

    @BeforeEach
    public void setUp() {
        // Mock Room
        room = Room.builder()
                .name("ROOM 43")
                .description("Room 43 est en face de l'amphi 55")
                .capacity(50)
                .build();

        // Mock Employee
        employee = Employee.builder()
                .nom("Ibrahim")
                .role(RoleEmployee.MANAGER)
                .email("ibrahimKrimi2@gmail.com")
                .build();

        // Dates pour la réservation
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.NOVEMBER, 25, 10, 0); // Date fixe : 25 novembre 2024, 10:00
        dateDebut = calendar.getTime();
        calendar.add(Calendar.HOUR, 2); // Ajouter 2 heures
        dateFin = calendar.getTime();

        // Mock Reservation
        reservation = Reservation.builder()
                .descritption(description)
                .date_Debut(dateDebut)
                .date_Fin(dateFin)
                .room(room)
                .employee(employee)
                .build();
    }

    @Test
    public void shouldReturnTrueIfTheRoomIsAvailable() {
        // When
        when(reservationRepository.findConflictingReservations(room.getId(), dateDebut, dateFin))
                .thenReturn(List.of()); // Pas de conflit

        Boolean result = reservationService.verifyAvailibility(room.getId(), dateDebut, dateFin);

        // Then
        Assertions.assertTrue(result);
    }

    @Test
    public void shouldReturnFalseIfTheRoomIsNotAvailable() {
        // When
        when(reservationRepository.findConflictingReservations(room.getId(), dateDebut, dateFin))
                .thenReturn(List.of(reservation)); // Conflit trouvé

        Boolean result = reservationService.verifyAvailibility(room.getId(), dateDebut, dateFin);

        // Then
        Assertions.assertFalse(result);
    }

    @Test
    public void shouldReturnTrueIfRoomCapacityIsSufficient() {
        // When
        when(roomRepository.findById(room.getId())).thenReturn(Optional.of(room));

        Boolean result = reservationService.verifyCapacite(room.getId(), 30);

        // Then
        Assertions.assertTrue(result);
    }

    @Test
    public void shouldReturnFalseIfRoomCapacityIsInsufficient() {
        // When
        when(roomRepository.findById(room.getId())).thenReturn(Optional.of(room));

        Boolean result = reservationService.verifyCapacite(room.getId(), 130);

        // Then
        Assertions.assertFalse(result);
    }

    @Test
    public void shouldCreateReservationIfRoomIsAvailable() {
        // When
        when(reservationRepository.findConflictingReservations(room.getId(), dateDebut, dateFin))
                .thenReturn(List.of()); // Pas de conflit
        when(this.reservationRepository.save(reservation)).thenReturn(reservation);

        Reservation createdReservation = reservationService.createReservation(employee, room, description, dateDebut, dateFin);

        // Then
        Assertions.assertNotNull(createdReservation);
    }

    @Test
    public void shouldThrowExceptionIfRoomIsNotAvailableForReservation() {
        // When
        when(reservationRepository.findConflictingReservations(room.getId(), dateDebut, dateFin))
                .thenReturn(List.of(reservation)); // Conflit trouvé

        // Then
       /* Assertions.assertThrows(IllegalStateException.class, () -> {
            reservationService.createReservation(employee, room, description, dateDebut, dateFin);
        });
        */
        Reservation result = reservationService.createReservation(employee, room, description, dateDebut, dateFin);
        Assertions.assertNull(result, "La méthode devrait retourner null en cas de conflit");
    }
}
