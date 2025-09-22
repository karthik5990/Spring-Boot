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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

        if (id == null || id <0) {
            throw new RuntimeException("Booking ID should be a positive integer.");
        }


        if (userId == null || userId < 0) {
            throw new RuntimeException("User ID should be a positive integer.");
        }


        if (flightId == null || flightId < 100) {
            throw new RuntimeException("Flight ID should be a positive integer.");
        }


        if (bookingDetails == null) {
            throw new RuntimeException("Booking details are required.");
        }
        if (bookingDetails.getSeatsBooked() == null || bookingDetails.getSeatsBooked() < 0) {
            throw new RuntimeException("Seats booked must be greater than 0.");
        }


        boolean exists = bookingRepo.existsByUserIdAndFlightIdAndBookingDetails_SeatsBooked(
                userId, flightId, bookingDetails.getSeatsBooked());
        if (exists) {
            throw new RuntimeException("Booking already exists for this user and flight with the same seat count.");
        }


        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Flight flight = flightRepo.findById(flightId)
                .orElseThrow(() -> new RuntimeException("Flight not found"));


        if (flight.getDate().isBefore(LocalDate.now())) {
            throw new RuntimeException("Cannot book tickets for past flights.");
        }


        if (flight.getAvailableSeats() < bookingDetails.getSeatsBooked()) {
            throw new RuntimeException("Not enough seats available for this flight.");
        }


        flight.setAvailableSeats(flight.getAvailableSeats() - bookingDetails.getSeatsBooked());
        flightRepo.save(flight);


        bookingDetails.setBookingTime(LocalDateTime.now());
        Double totalPrice = flight.getPrice() * bookingDetails.getSeatsBooked();
        bookingDetails.setTotalPrice(totalPrice);
        bookingDetails.setStatus("BOOKING-CONFIRMED");


        if (bookingDetails.getId() != null) {
            BookingDetails existingDetails = detailsRepo.findById(bookingDetails.getId())
                    .orElseThrow(() -> new RuntimeException("Booking details not found for provided ID."));
            existingDetails.setSeatsBooked(bookingDetails.getSeatsBooked());
            existingDetails.setBookingTime(bookingDetails.getBookingTime());
            existingDetails.setTotalPrice(bookingDetails.getTotalPrice());
            existingDetails.setStatus(bookingDetails.getStatus());
            bookingDetails = detailsRepo.save(existingDetails);
        } else {
            bookingDetails = detailsRepo.save(bookingDetails);
        }


        Booking booking = new Booking();
        booking.setId(id);
        booking.setUser(user);
        booking.setFlight(flight);
        booking.setBookingDetails(bookingDetails);

        return bookingRepo.save(booking);
    }


    public Booking getById(Long id) {
        if (id == null || id < 0)
            throw new RuntimeException("Invalid Booking ID");
        return bookingRepo.findById(id).orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    public BookingDetails getBookingById(Object idInput) {
        if (idInput == null) {
            throw new RuntimeException("Booking Details ID cannot be null.");
        }

        Long id;
        try {
            if (idInput instanceof String) {
                id = Long.parseLong((String) idInput);
            } else if (idInput instanceof Number) {
                id = ((Number) idInput).longValue();
            } else {
                throw new RuntimeException("Booking Details ID should be an integer.");
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException("Booking Details ID should be an integer.");
        }
        if (id <= 0) {
            throw new RuntimeException("Booking Details ID should be a positive integer.");
        }

        return detailsRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking details not found"));
    }


    public Iterable<Booking> getAllBookings() {
        return bookingRepo.findAll();
    }

    public void update(Long bookingId, BookingDetails details) {
        if (bookingId == null || bookingId <= 0)
            throw new RuntimeException("Invalid Booking ID");
        BookingDetails existing = detailsRepo.findById(bookingId).orElseThrow(() -> new RuntimeException("Booking details not found"));

        if (details.getSeatsBooked() != null)
            existing.setSeatsBooked(details.getSeatsBooked());
        if (details.getStatus() != null)
            existing.setStatus(details.getStatus());

        existing.setBookingTime(LocalDateTime.now());
        detailsRepo.save(existing);
    }

    public void cancel(Object bookingIdInput) {
        if (bookingIdInput == null) {
            throw new RuntimeException("Booking ID cannot be null.");
        }

        Long bookingId;
        try {
            if (bookingIdInput instanceof String) {
                bookingId = Long.parseLong((String) bookingIdInput);
            } else if (bookingIdInput instanceof Number) {
                bookingId = ((Number) bookingIdInput).longValue();
            } else {
                throw new RuntimeException("Booking ID should be an integer.");
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException("Booking ID should be an integer.");
        }
        if (bookingId <= 0) {
            throw new RuntimeException("Booking ID should be a positive integer.");
        }
        BookingDetails booking = getBookingById(bookingId);
        booking.setStatus("Booking-Cancelled...");
        detailsRepo.save(booking);
    }


    public void cancelAll() {
        detailsRepo.deleteAll();
    }

    public List<Booking> bookingHistory(Long userId) {
        if (userId == null) {
            throw new RuntimeException("User ID cannot be null.");
        }
        if (userId <= 0) {
            throw new RuntimeException("User ID should be a positive integer.");
        }

        List<Booking> bookings = bookingRepo.findAllByUserId(userId);
        if (bookings == null || bookings.isEmpty()) {
            throw new RuntimeException("Requesting history for userId that does not exist.");
        }

        return bookings;
    }

}
