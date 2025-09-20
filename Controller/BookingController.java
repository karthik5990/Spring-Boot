package com.Project_Flight.Controller;

import com.Project_Flight.Entity.Booking;
import com.Project_Flight.Entity.BookingDetails;
import com.Project_Flight.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping(value="/bookings/{id}")
    public Booking createBooking(@PathVariable("id") Long idNo, @RequestParam Long userId,
                                 @RequestParam Long flightId,
                                 @RequestBody BookingDetails bookingDetails) {
        return bookingService.addBooking(idNo, userId, flightId, bookingDetails);
    }

    @GetMapping(value = "/bookings/{id}")
    public Booking getBookingById(@PathVariable Long id) {
        return bookingService.getById(id);

    }
    @PutMapping(value="/bookings")
    public String update(@RequestBody BookingDetails details) {
        bookingService.update(details);
        return "The Values Have been updated for the "+details.getId();
    }

    @GetMapping(value="/bookings")
    public Iterable<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }
    @DeleteMapping(value="/bookings")
    public void cancel(Long bookingId) {
        bookingService.cancel(bookingId);
    }
    @DeleteMapping(value="/bookings/all")
    public String cancelAll(){
       bookingService.cancelAll();
       return "All the Bookingshave been cancelled...";
    }
    @GetMapping(value="/bookings/history/{userId}")
    public List<Booking> bookingHistory(@PathVariable Long userId) {
        return bookingService.bookingHistory(userId);
    }
}
