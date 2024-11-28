package com.example.gestiondesrservationsdesalle.Mapper;

import com.example.gestiondesrservationsdesalle.DTO.ReservationDTO;
import com.example.gestiondesrservationsdesalle.Entity.Employee;
import com.example.gestiondesrservationsdesalle.Entity.Reservation;
import com.example.gestiondesrservationsdesalle.Entity.Room;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {

    public Reservation toEntity(ReservationDTO reservationDTO, Employee employee, Room room) {
        return Reservation.builder()
                .id(reservationDTO.id())
                .date_Debut(reservationDTO.date_Debut())
                .date_Fin(reservationDTO.date_Fin())
                .descritption(reservationDTO.descritption())
                .room(room)
                .employee(employee)
                .build();
    }

    public ReservationDTO toDTO(Reservation reservation) {
        return ReservationDTO.builder()
                .id(reservation.getId())
                .date_Debut(reservation.getDate_Debut())
                .date_Fin(reservation.getDate_Fin())
                .descritption(reservation.getDescritption())
                .employee_id(reservation.getEmployee().getId())
                .room_id(reservation.getRoom().getId())
                .build();
    }
}
