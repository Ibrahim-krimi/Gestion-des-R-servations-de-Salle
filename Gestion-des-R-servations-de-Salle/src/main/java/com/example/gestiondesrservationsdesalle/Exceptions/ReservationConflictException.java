package com.example.gestiondesrservationsdesalle.Exceptions;

public class ReservationConflictException extends RuntimeException {

    public ReservationConflictException(String message) {
        super(message);
    }
}
