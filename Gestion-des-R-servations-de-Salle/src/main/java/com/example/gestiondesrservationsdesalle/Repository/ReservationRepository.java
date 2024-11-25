package com.example.gestiondesrservationsdesalle.Repository;

import com.example.gestiondesrservationsdesalle.Entity.Employee;
import com.example.gestiondesrservationsdesalle.Entity.Reservation;
import com.example.gestiondesrservationsdesalle.Entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    List<Reservation> findByEmployee(Employee employee);
    List<Reservation> findByRoom(Room room);
    @Query("SELECT r FROM Reservation r WHERE r.room.id = :roomId " +
            "AND (r.date_Debut < :dateFin AND r.date_Fin > :dateDebut)")
    List<Reservation> findConflictingReservations(@Param("roomId") Integer roomId,
                                                  @Param("dateDebut") Date dateDebut,
                                                  @Param("dateFin") Date dateFin);
}