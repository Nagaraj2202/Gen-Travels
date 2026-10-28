package com.gentravels.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "slot_bookings")
@Data
public class SlotBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String transportType; // Bus, Train, Flight, Ship
    private String destinationCountry;
    private String destinationState;
    private String passengerName;
    private String status; // PENDING, CONFIRMED, REJECTED
}