package com.Project_Flight.service;

import com.Project_Flight.Entity.Flight;
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
    FlightRepo repo;
    public void add( Flight flight){
        repo.save(flight);
    }
    public List<Flight> search(String source, String destination, LocalDate date){
        return repo.findBySourceAndDestinationAndDate(source, destination,date);
    }
    public Optional<Flight> searchById(Long id){
        return repo.findById(id);
    }
    public Iterable<Flight> retrieve(){
        return repo.findAll();
    }
    public void deleteFlight(Long id){
        repo.deleteById(id);
    }
    public void deleteAll(){
        repo.deleteAll();
    }
    public Flight updateFlight(Flight flight) {
        Optional<Flight> op = repo.findById(flight.getId());
        if (op.isPresent()) {
            flight.setDate(flight.getDate());
            flight.setPrice(flight.getPrice());
            flight.setAvailableSeats(flight.getAvailableSeats());
            flight.setShift(flight.getShift());
            return repo.save(flight);
        } else {
            throw new RuntimeException("Flight not found with this Id: " + flight.getFlightName());
        }
    }
}
