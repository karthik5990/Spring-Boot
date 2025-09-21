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

    //POST: http://localhost:2003/bookings/1?userId=3&flightId=155

                                        //1=idNo(Bookings id)
                                        // In the Json that we're giving the id to BookingDetails class.
    @PostMapping(value="/bookings/{id}")
    public Booking createBooking(@PathVariable("id") Long idNo, @RequestParam Long userId,
                                 @RequestParam Long flightId,
                                 @RequestBody BookingDetails bookingDetails) {
        return bookingService.addBooking(idNo, userId, flightId, bookingDetails);
    }

    //GET: http://localhost:2003/bookings/1
    @GetMapping(value = "/bookings/{id}")
    public Booking getBookingById(@PathVariable Long id) {
        return bookingService.getById(id);

    }
    //PUT: http://localhost:2003/bookings/1
    @PutMapping(value="/bookings/{id}")
    public String update(@PathVariable("id") Long idNo, @RequestBody BookingDetails details) {
        bookingService.update(idNo, details);
        return "The Values Have been updated for the "+details.getId();
    }

    //GET: http://localhost:2003/bookings/all
    @GetMapping(value="/bookings/all")
    public Iterable<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }
    @DeleteMapping(value="/bookings/{id}")
    public String  cancel(@PathVariable("id") Long bookingId) {
        bookingService.cancel(bookingId);
        return "BOOKING-CANCELLED";
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
