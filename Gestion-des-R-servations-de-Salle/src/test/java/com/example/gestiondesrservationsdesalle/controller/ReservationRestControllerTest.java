package com.example.gestiondesrservationsdesalle.controller;

import com.example.gestiondesrservationsdesalle.DTO.ReservationDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import jdk.jfr.DataAmount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Calendar;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;


@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class ReservationRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void ShouldReturnAllReservations() throws Exception {
    mockMvc.perform(get("/api/reservations")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    void ShouldReturnReservationById()  throws Exception {
        mockMvc.perform(get("/api/reservations/{id}",1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

    @Test
    void ShouldCreateReservation() throws Exception {
        //given
        Calendar calendar = Calendar.getInstance();
        calendar.set(2026, Calendar.SEPTEMBER, 25, 10, 0); // Date fixe : 25 novembre 2024, 10:00
        Date dateDebut = calendar.getTime();
        calendar.add(Calendar.HOUR, 2); // Ajouter 2 heures
        Date dateFin = calendar.getTime();
        ReservationDTO reservationDTO = ReservationDTO.builder()
                .employee_id(1)
                .room_id(1)
                .id(1)
                .date_Debut(dateDebut)
                .date_Fin(dateFin)
                .descritption("this is test description")
                .build();
        //then
        mockMvc.perform(post("/api/reservations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(reservationDTO)))
                .andDo(print())
                .andExpect(status().isCreated());

    }
    @DisplayName("Should Not Create Reservation Because there is already a reservation With the same Date")
    @Test
    void ShouldNotCreateReservation() throws Exception {
        //given
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.NOVEMBER, 27, 10, 0); //
        Date dateDebut = calendar.getTime();
        calendar.add(Calendar.HOUR, 2); // Ajouter 2 heures
        Date dateFin = calendar.getTime();
        ReservationDTO reservationDTO = ReservationDTO.builder()
                .employee_id(1)
                .room_id(1)
                .id(1)
                .date_Debut(dateDebut)
                .date_Fin(dateFin)
                .descritption("this is test description")
                .build();
        //then
        mockMvc.perform(post("/api/reservations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(reservationDTO)))
                .andDo(print())
                .andExpect(status().isConflict());

    }

    public static String asJsonString(final Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}