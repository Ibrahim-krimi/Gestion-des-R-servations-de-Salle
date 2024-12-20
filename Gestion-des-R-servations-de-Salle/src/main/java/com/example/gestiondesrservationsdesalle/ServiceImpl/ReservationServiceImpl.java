package com.example.gestiondesrservationsdesalle.ServiceImpl;

import com.example.gestiondesrservationsdesalle.DTO.ReservationDTO;
import com.example.gestiondesrservationsdesalle.Entity.Employee;
import com.example.gestiondesrservationsdesalle.Entity.Reservation;
import com.example.gestiondesrservationsdesalle.Entity.Room;
import com.example.gestiondesrservationsdesalle.Exceptions.ReservationConflictException;
import com.example.gestiondesrservationsdesalle.Mapper.ReservationMapper;
import com.example.gestiondesrservationsdesalle.Repository.EmployeeRepository;
import com.example.gestiondesrservationsdesalle.Repository.ReservationRepository;
import com.example.gestiondesrservationsdesalle.Repository.RoomRepository;
import com.example.gestiondesrservationsdesalle.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
 import java.util.List;

 @Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    ReservationMapper reservationMapper;

    @Autowired
    EmployeeRepository employeeRepository;

     public Reservation getReservation(Integer id) {
         return reservationRepository.findById(id).orElse(null);
     }


     public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Boolean verifyAvailibility(Integer roomId, Date dateDebut, Date dateFin) {

       List<Reservation> reservations = this.reservationRepository.findConflictingReservations(roomId, dateDebut, dateFin);
        return reservations.isEmpty();
    }

    @Override
    public Boolean verifyCapacite(Integer roomId, Integer capacite) {
        Room room=this.roomRepository.findById(roomId).orElseThrow();
        return room.getCapacity() >= capacite;
    }
     @Override
     public Reservation createReservation(ReservationDTO reservationDTO) {
         List<Reservation> reservationsWithConflict = this.reservationRepository.findConflictingReservations(
                 reservationDTO.room_id(), reservationDTO.date_Debut(), reservationDTO.date_Fin());
         if (!reservationsWithConflict.isEmpty()) {
             throw new ReservationConflictException("Reservation already exists for the specified time frame");
         }

         Employee employee = this.employeeRepository.findById(reservationDTO.employee_id())
                 .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
         Room room = this.roomRepository.findById(reservationDTO.room_id())
                 .orElseThrow(() -> new IllegalArgumentException("Room not found"));

         Reservation reservation = this.reservationMapper.toEntity(reservationDTO, employee, room);

         return this.reservationRepository.save(reservation);
     }

    /*
    @Override
    public Reservation createReservation(Employee employee, Room room, String description, Date dateDebut, Date dateFin)  {

        try {
            List<Reservation> reservationsWithConlict =  this.reservationRepository.findConflictingReservations(room.getId(), dateDebut, dateFin);
            if (!reservationsWithConlict.isEmpty()) {
                throw new IllegalStateException ("Reservation already exists");
            }
            Reservation reservation = new Reservation(0,description,dateDebut,dateFin,employee,room);
            return this.reservationRepository.save(reservation);

        }catch (IllegalStateException  e) {
            System.err.println("Erreur capturée : " + e.getMessage());
            return null;
        }
    }

     */
}
