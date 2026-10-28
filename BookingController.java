package com.gentravels.controller;

import com.gentravels.model.SlotBooking;
import com.gentravels.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bookings")
@CrossOrigin(origins = "*")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // User submits slot booking request
    @PostMapping("/request-slot")
    public ResponseEntity<SlotBooking> requestSlot(@RequestBody SlotBooking bookingRequest) {
        SlotBooking pendingBooking = bookingService.createPendingBooking(bookingRequest);
        return ResponseEntity.ok(pendingBooking);
    }

    // Admin verifies and approves slot booking
    @PostMapping("/admin/approve/{bookingId}")
    public ResponseEntity<SlotBooking> approveSlotByAdmin(
            @PathVariable Long bookingId,
            @RequestParam String adminToken) {
        
        if (!bookingService.isValidAdminToken(adminToken)) {
            return ResponseEntity.status(403).build();
        }

        SlotBooking confirmedBooking = bookingService.confirmBooking(bookingId);
        return ResponseEntity.ok(confirmedBooking);
    }

    // Client checks booking status for confirmation push notification
    @GetMapping("/status/{bookingId}")
    public ResponseEntity<String> checkStatus(@PathVariable Long bookingId) {
        String status = bookingService.getBookingStatus(bookingId);
        return ResponseEntity.ok(status);
    }
}