package com.example.gestiondesrservationsdesalle.service;

import com.example.gestiondesrservationsdesalle.Repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceImplTest {

    @Mock
    ReservationRepository reservationRepository;

    @InjectMocks
    ReservationServiceImpl reservationService;


}
