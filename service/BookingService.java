package com.Project_Flight.service;

import com.Project_Flight.Entity.Booking;
import com.Project_Flight.Entity.BookingDetails;
import com.Project_Flight.Entity.Flight;
import com.Project_Flight.Entity.User;
import com.Project_Flight.repository.BookingDetailsRepo;
import com.Project_Flight.repository.BookingRepo;
import com.Project_Flight.repository.FlightRepo;
import com.Project_Flight.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private FlightRepo flightRepo;

    @Autowired
    private BookingDetailsRepo detailsRepo;

    public Booking addBooking(Long id, Long userId, Long flightId, BookingDetails bookingDetails) {
        boolean exists = bookingRepo.existsByUserIdAndFlightIdAndBookingDetails_SeatsBooked(userId, flightId, bookingDetails.getSeatsBooked());
        if (exists) {
            throw new RuntimeException("Booking already exists for this user and flight with the same seat count");
        }
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Flight flight = flightRepo.findById(flightId)
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        if (flight.getAvailableSeats() < bookingDetails.getSeatsBooked())
            throw new RuntimeException("Not enough seats available");

        flight.setAvailableSeats(flight.getAvailableSeats() - bookingDetails.getSeatsBooked());
        flightRepo.save(flight);
        bookingDetails.setBookingTime(LocalDateTime.now());
        Double p = flight.getPrice()*bookingDetails.getSeatsBooked();
        bookingDetails.setTotalPrice(p);
        detailsRepo.save(bookingDetails);
        Booking booking = new Booking();
        booking.setId(id);
        booking.setUser(user);
        booking.setFlight(flight);
        booking.setBookingDetails(bookingDetails);
        return bookingRepo.save(booking);
    }

    public Booking getById(Long id) {
        return bookingRepo.findById(id).orElseThrow(() -> new RuntimeException("Booking has not found in the Data..."));
    }
    public BookingDetails getBookingById(Long id){
        return detailsRepo.findById(id).orElseThrow(() -> new RuntimeException("Booking has not found in the Data..."));

    }
    public Iterable<Booking> getAllBookings() {
        return bookingRepo.findAll();
    }
    public void update(Long bookingId, BookingDetails details) {
        BookingDetails existing = detailsRepo.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking details not found with ID: " + bookingId));
        if (details.getSeatsBooked() != null) {
            existing.setSeatsBooked(details.getSeatsBooked());
        }
        if (details.getStatus() != null) {
            existing.setStatus(details.getStatus());
        }
        existing.setBookingTime(LocalDateTime.now());
        detailsRepo.save(existing);
    }


    public void cancel(Long bookingId) {
        BookingDetails booking = getBookingById(bookingId);
        booking.setStatus("Booking-Cancelled...");
        detailsRepo.save(booking);
    }
    public void cancelAll(){
        detailsRepo.deleteAll();
    }
    public List<Booking> bookingHistory(Long userId) {
        return bookingRepo.findAllByUserId(userId);
    }
}


