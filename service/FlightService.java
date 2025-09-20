package com.Project_Flight.service;

import com.Project_Flight.Entity.Flight;
import com.Project_Flight.repository.FlightRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {
    @Autowired
    FlightRepo repo;
    public void add( Flight flight){
        repo.save(flight);
    }
    public List<Flight> search(String source, String destination, LocalDateTime date){
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
    public Flight updateFlight(Long id, LocalDateTime time, double price, Integer availableSeats) {
        Optional<Flight> op = repo.findById(id);
        if (op.isPresent()) {
            Flight flight = op.get();
            flight.setDate(time);
            flight.setPrice(price);
            flight.setAvailableSeats(availableSeats);
            return repo.save(flight);
        } else {
            throw new RuntimeException("Flight not found with this Id: " + id);
        }
    }
}
