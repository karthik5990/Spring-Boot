package com.Project_Flight.service;

import com.Project_Flight.Entity.Flight;
import com.Project_Flight.ResourceNotFoundException;
import com.Project_Flight.Shift;
import com.Project_Flight.repository.FlightRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    @Autowired
    private FlightRepo repo;

    public void add(Flight flight) {
        boolean exists = repo.existsBySourceAndDestinationAndDateAndShift(
                flight.getSource(), flight.getDestination(), flight.getDate(), flight.getShift());
        if (exists)
            throw new RuntimeException("Flight already exists for the given source, destination, date, and shift");
        repo.save(flight);
    }
    public List<Flight> search(String source, String destination, LocalDate date, Shift shift) {
        if (source == null || source.isEmpty()) {
            throw new RuntimeException("Source is required");
        }
        if (destination == null || destination.isEmpty()) {
            throw new RuntimeException("Destination is required");
        }
        if (date == null) {
            throw new RuntimeException("Date is required");
        }
        if (shift == null) {
            throw new RuntimeException("Shift is required");
        }
        List<Flight> flights = repo.findBySourceAndDestinationAndDateAndShift(source, destination, date, shift);
        if (flights == null || flights.isEmpty()) {
            throw new ResourceNotFoundException("Flights Not Found");
        }
        return flights;
    }

    public Optional<Flight> searchById(Long id) {
        return repo.findById(id);
    }

    public Iterable<Flight> retrieve() {
        return repo.findAll();
    }

    public void deleteFlight(Long id) {
        repo.deleteById(id);
    }

    public void deleteAll() {
        repo.deleteAll();
    }

    public Flight updateFlight(Flight flight) {
        Optional<Flight> op = repo.findById(flight.getId());
        if (op.isEmpty())
            throw new RuntimeException("Flight not found with ID: " + flight.getId());

        if (flight.getFlightName() == null || flight.getFlightName().isBlank())
            throw new RuntimeException("Flight name is required and cannot be empty");
        if (flight.getDate() == null)
            throw new RuntimeException("Flight date is required");
        if (flight.getFlightNumber() == null)
            throw new RuntimeException("Flight Number is required");
        if (flight.getPrice() == 0.0)
            throw new RuntimeException("Flight Price is required");
        if (flight.getShift() == null)
            throw new RuntimeException("Flight Shift is required");
        if (flight.getSource() == null || flight.getSource().isEmpty())
            throw new RuntimeException("Source is required");
        if (flight.getDestination() == null || flight.getDestination().isEmpty())
            throw new RuntimeException("Destination is required");
        if (op.isPresent()) {
            Flight existingFlight = op.get();
            existingFlight.setDate(flight.getDate());
            existingFlight.setPrice(flight.getPrice());
            existingFlight.setAvailableSeats(flight.getAvailableSeats());
            existingFlight.setShift(flight.getShift());
            return repo.save(existingFlight);
        } else {
            throw new RuntimeException("Flight not found with ID: " + flight.getId());
        }
    }
}
