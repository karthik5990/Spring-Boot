package com.Project_Flight.Controller;

import com.Project_Flight.Entity.Booking;
import com.Project_Flight.Entity.BookingDetails;
import com.Project_Flight.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/{id}")
    public Booking createBooking(@PathVariable Long id,
                                 @RequestParam Long userId,
                                 @RequestParam Long flightId,
                                 @RequestBody BookingDetails bookingDetails) {
        return bookingService.addBooking(id, userId, flightId, bookingDetails);
    }

    @GetMapping("/{id}")
    public Booking getBookingById(@PathVariable Long id) {
        return bookingService.getById(id);
    }

    @PutMapping("/{id}")
    public String updateBooking(@PathVariable Long id, @RequestBody BookingDetails bookingDetails) {
        bookingService.update(id, bookingDetails);
        return "Booking details updated for ID: " + bookingDetails.getId();
    }

    @GetMapping("/all")
    public Iterable<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @DeleteMapping("/{id}")
    public String cancelBooking(@PathVariable Long id) {
        bookingService.cancel(id);
        return "Booking cancelled with ID: " + id;
    }

    @DeleteMapping("/all")
    public String cancelAllBookings() {
        bookingService.cancelAll();
        return "All bookings have been cancelled";
    }

    @GetMapping("/history/{userId}")
    public List<Booking> bookingHistory(@PathVariable Long userId) {
        return bookingService.bookingHistory(userId);
    }
}
