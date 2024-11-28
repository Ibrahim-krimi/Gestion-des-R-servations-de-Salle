package com.example.gestiondesrservationsdesalle.DTO;

import com.example.gestiondesrservationsdesalle.Entity.Employee;
import com.example.gestiondesrservationsdesalle.Entity.Room;
import lombok.Builder;

import java.util.Date;

@Builder
public record ReservationDTO(int id, String descritption, Date date_Debut, Date date_Fin, int employee_id, int room_id) {

}
