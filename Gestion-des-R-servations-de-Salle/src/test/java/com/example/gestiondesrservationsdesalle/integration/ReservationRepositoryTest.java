package com.example.gestiondesrservationsdesalle.integration;

import com.example.gestiondesrservationsdesalle.Entity.Employee;
import com.example.gestiondesrservationsdesalle.Entity.Reservation;
import com.example.gestiondesrservationsdesalle.Entity.Room;
import com.example.gestiondesrservationsdesalle.Enum.RoleEmployee;
import com.example.gestiondesrservationsdesalle.Repository.EmployeeRepository;
import com.example.gestiondesrservationsdesalle.Repository.ReservationRepository;
import com.example.gestiondesrservationsdesalle.Repository.RoomRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@DataJpaTest@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ReservationRepositoryTest {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    Reservation reservation;
    Room room;
    Employee employee;

    @BeforeEach
    public void setup(){
        this.room = Room.builder()
                .name("ROOM 43")
                .description("Room 43  est en face de l'amphi 55")
                .capacity(50).build();
        room =this.roomRepository.save(room);

        employee = Employee.builder()
                .nom("Ibrahim")
                .role(RoleEmployee.MANAGER)
                .email("ibrahimKrimi2@gmail.com").build();
        employee = this.employeeRepository.save(employee);


        Calendar  calendar = Calendar.getInstance(); // Obtenir la date actuelle
        Date dateDebut = calendar.getTime(); // Date actuelle

        calendar.add(Calendar.HOUR, 2); // Ajouter 2 heures
        Date dateFin = calendar.getTime(); // Date après 2 heures

        reservation = Reservation.builder()
                .descritption("Reservation pour la fete d'Anniversaire de mon Pote")
                .date_Debut(dateDebut)
                .date_Fin(dateFin)
                .room(room)
                .employee(employee)
                .build();
    }
    @DisplayName("Junit Repo Test for Saving Reservation")
    @Test
    public void shouldCreateReservation(){
        //when
         Reservation savedReservation = reservationRepository.save(reservation);
         //then
        Assertions.assertNotNull(savedReservation);
        Assertions.assertNotEquals(0, reservation.getId());
    }

    @DisplayName("Junit Repo Test for Modify Reservation")
    @Test
    public void shouldModifyReservation(){
        //when
        Reservation savedReservation = reservationRepository.save(reservation);
        savedReservation.setDescritption("COCO");
        Reservation savedReservationSecondTime = reservationRepository.save(savedReservation);
        //then
        Assertions.assertNotNull(savedReservationSecondTime);
        Assertions.assertEquals(savedReservationSecondTime.getDescritption(), savedReservation.getDescritption());
    }

    @DisplayName("Junit Repo Test for test find Reservation By room")
    @Test
    public void shouldFindReservationByRoom(){
        //Given
        reservationRepository.save(reservation);
        //when
        List<Reservation> reservationsByroom = this.reservationRepository.findByRoom(room);
        //Then

        reservationsByroom.forEach(reservation1 -> {
            Assertions.assertEquals(reservation1.getRoom(), room);
        });
    }

    @DisplayName("Junit Repo Test for test find Reservation By Employee")
    @Test
    public void shouldFindReservationByEmployee(){
        //Given
        reservationRepository.save(reservation);
        //when
        List<Reservation> reservationsByEmployee = this.reservationRepository.findByEmployee(employee);
        //Then

        reservationsByEmployee.forEach(reservation1 -> {
            Assertions.assertEquals(reservation1.getEmployee(), employee);
        });
    }
}
