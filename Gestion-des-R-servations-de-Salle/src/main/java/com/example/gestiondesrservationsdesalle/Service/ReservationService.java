package com.example.gestiondesrservationsdesalle.Service;

import com.example.gestiondesrservationsdesalle.Entity.Employee;
import com.example.gestiondesrservationsdesalle.Entity.Reservation;
import com.example.gestiondesrservationsdesalle.Entity.Room;

import java.util.Date;

public interface ReservationService {

   public Boolean verifyAvailibility(Integer roomId , Date dateDebut,Date dateFin);
   public Boolean verifyCapacite(Integer roomId , Integer capacite);
   public Reservation createReservation(Employee employee,Room room,String description,Date dateDebut,Date dateFin);
}
