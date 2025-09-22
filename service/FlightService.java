package com.Project_Flight.service;

import com.Project_Flight.Entity.Flight;
import com.Project_Flight.ResourceNotFoundException;
import com.Project_Flight.Shift;
import com.Project_Flight.repository.FlightRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    @Autowired
    private FlightRepo repo;


    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-uuuu")
                    .withResolverStyle(ResolverStyle.STRICT);

    public void add(Flight flight) {

        if (flight.getFlightName() == null || flight.getFlightName().isBlank()) {
            throw new RuntimeException("Flight name is required and cannot be empty.");
        }

        if (flight.getFlightNumber() == null || flight.getFlightNumber().isBlank()) {
            throw new RuntimeException("Flight number is required and cannot be empty.");
        }

        if (flight.getSource() == null || flight.getSource().isEmpty()) {
            throw new RuntimeException("Source is required.");
        }
        if (flight.getDestination() == null || flight.getDestination().isEmpty()) {
            throw new RuntimeException("Destination is required.");
        }

        if (flight.getDate() == null) {
            throw new RuntimeException("Flight date is required.");
        }

        try {
            LocalDate parsedDate = LocalDate.parse(flight.getDate().toString(), DATE_FORMATTER);
            flight.setDate(parsedDate);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Invalid date format. Please use dd-MM-yyyy format (e.g., 22-09-2025).");
        }

        if (flight.getDate().isBefore(LocalDate.now())) {
            throw new RuntimeException("Flight date cannot be in the past.");
        }

        if (flight.getShift() == null) {
            throw new RuntimeException("Flight shift is required.");
        }

        if (flight.getPrice() <= 0.0) {
            throw new RuntimeException("Flight price must be positive.");
        }
        if (flight.getPrice() <= 800.0) {
            throw new RuntimeException("Flight price must be greater than 800.");
        }

        if (flight.getAvailableSeats() == null) {
            throw new RuntimeException("Available seats are required.");
        }
        if (flight.getAvailableSeats() <= 5) {
            throw new RuntimeException("Available seats must be greater than 5.");
        }

        boolean exists = repo.existsBySourceAndDestinationAndDateAndShift(
                flight.getSource(), flight.getDestination(), flight.getDate(), flight.getShift());
        if (exists) {
            throw new RuntimeException("Flight already exists for the given source, destination, date, and shift.");
        }

        repo.save(flight);
    }

    public List<Flight> search(String source, String destination, LocalDate date, Shift shift) {
        if (source == null || source.isEmpty())
            throw new RuntimeException("Source is required");
        if (destination == null || destination.isEmpty())
            throw new RuntimeException("Destination is required");
        if (source.equalsIgnoreCase(destination))
            throw new RuntimeException("Source and destination cannot be the same");
        if (date == null)
            throw new RuntimeException("Date is required");
        if (date.isBefore(LocalDate.now()))
            throw new RuntimeException("Date cannot be in the past");
        if (shift == null)
            throw new RuntimeException("Shift is required");

        List<Flight> flights = repo.findBySourceAndDestinationAndDateAndShift(source, destination, date, shift);
        if (flights == null || flights.isEmpty())
            throw new ResourceNotFoundException("Flights not found");
        return flights;
    }

    public Flight searchById(Long id) {
        if (id == null || id <= 0)
            throw new RuntimeException("Invalid flight ID");
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Flight not found with ID: " + id));
    }

    public Iterable<Flight> retrieve() {
        return repo.findAll();
    }

    public void deleteFlight(Long id) {
        if (id == null || id <= 0)
            throw new RuntimeException("Invalid flight ID");
        if (!repo.existsById(id))
            throw new ResourceNotFoundException("Flight not found with ID: " + id);
        repo.deleteById(id);
    }

    public void deleteAll() {
        repo.deleteAll();
    }

    public Flight updateFlight(Flight flight) {

        if (flight.getId() == null) {
            throw new RuntimeException("Flight ID cannot be null.");
        }

        if (flight.getId() <= 0) {
            throw new RuntimeException("Flight ID should be a positive integer.");
        }

        try {
            Long.valueOf(flight.getId());
        } catch (NumberFormatException e) {
            throw new RuntimeException("Flight ID must be a valid numeric value.");
        }

        Optional<Flight> op = repo.findById(flight.getId());
        if (op.isEmpty()) {
            throw new ResourceNotFoundException("Flight not found with ID: " + flight.getId());
        }

        Flight existingFlight = op.get();

        if (!existingFlight.getId().equals(flight.getId())) {
            throw new RuntimeException("Flight ID cannot be updated.");
        }
        if (!existingFlight.getFlightNumber().equals(flight.getFlightNumber())) {
            throw new RuntimeException("Flight number cannot be updated.");
        }
        if (!existingFlight.getFlightName().equals(flight.getFlightName())) {
            throw new RuntimeException("Flight name cannot be updated.");
        }

        if (flight.getPrice() <=0 || flight.getPrice() <= 0.0) {
            throw new RuntimeException("Flight price must be positive.");
        }
        if (flight.getAvailableSeats() != null && flight.getAvailableSeats() < 0) {
            throw new RuntimeException("Available seats must be zero or greater.");
        }

        if (flight.getDate() != null) {
            try {
                LocalDate parsedDate = LocalDate.parse(flight.getDate().toString(), DATE_FORMATTER);
                flight.setDate(parsedDate);
            } catch (DateTimeParseException e) {
                throw new RuntimeException("Invalid date format. Please use dd-MM-yyyy format (e.g., 22-09-2025).");
            }
        }

        existingFlight.setDate(flight.getDate());
        existingFlight.setPrice(flight.getPrice());
        existingFlight.setAvailableSeats(flight.getAvailableSeats());
        existingFlight.setShift(flight.getShift());
        existingFlight.setSource(flight.getSource());
        existingFlight.setDestination(flight.getDestination());

        return repo.save(existingFlight);
    }
}
