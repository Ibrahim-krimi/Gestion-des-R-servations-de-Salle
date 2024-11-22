package com.example.gestiondesrservationsdesalle.Repository;

import com.example.gestiondesrservationsdesalle.Entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

}