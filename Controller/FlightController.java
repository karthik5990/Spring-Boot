package com.Project_Flight.Controller;

import com.Project_Flight.Entity.Flight;

import com.Project_Flight.ResourceNotFoundException;
import com.Project_Flight.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@RestController
public class FlightController {
    @Autowired
    FlightService service;

    //POST: http://localhost:2003/flights/add
    @PostMapping(value="/flights/add")
    public String addFlight(@RequestBody Flight flight){
        service.add(flight);
        return "The "+flight.getId()+" data has been added...";
    }
    //POST: http://localhost:2003/flights/search
    @PostMapping(value = "/flights/search", produces = "application/json")
    public List<Flight> search(@RequestBody Flight request) {
        return service.search(request.getSource(), request.getDestination(), request.getDate(), request.getShift());
    }

    //GET: http://localhost:2003/flights/115
    @GetMapping(value="/flights/{id}")
    public Optional<Flight> searchById(@PathVariable Long id){
        return service.searchById(id);
    }
    //GET:  http://localhost:2003/flights/all
    @GetMapping(value="/flights/all")
    public Iterable<Flight> retrieveall(){
        return service.retrieve();
    }

    //PUT: http://localhost:2003/flights/205
    @PutMapping(value="/flights/{id}")
    public String updateFlight(@RequestBody Flight flight){
        service.updateFlight(flight);
        return "The "+flight.getFlightName()+" id's Flight data has been updated...";
    }

    //DELETE: http://localhost:2003/flights/205
    @DeleteMapping(value="/flights/{id}")
    public String deleteFlight(@PathVariable("id")Long idNo){
        service.deleteFlight(idNo);
        return "The "+idNo+" flight has been deleted...";
    }

    //DELETE: http://localhost:2003/flights/all
    @DeleteMapping(value="/flights/all")
    public void deleteAllFlights(){
        service.deleteAll();
    }
}
