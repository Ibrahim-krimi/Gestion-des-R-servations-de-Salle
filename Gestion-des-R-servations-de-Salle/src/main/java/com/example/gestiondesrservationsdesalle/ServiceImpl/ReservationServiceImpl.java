package com.example.gestiondesrservationsdesalle.ServiceImpl;

import com.example.gestiondesrservationsdesalle.Entity.Employee;
import com.example.gestiondesrservationsdesalle.Entity.Reservation;
import com.example.gestiondesrservationsdesalle.Entity.Room;
import com.example.gestiondesrservationsdesalle.Service.ReservationService;

import java.util.Date;

public class ReservationServiceImpl implements ReservationService {

    @Override
    public Boolean verifyAvailibility(Integer roomId, Date dateDebut, Date dateFin) {
        return null;
    }

    @Override
    public Boolean verifyCapacite(Integer roomId, Integer capacite) {
        return null;
    }

    @Override
    public Reservation createReservation(Employee employee, Room room, String description, Date dateDebut, Date dateFin) {
        return null;
    }
}
