package com.Project_Flight.Controller;

import com.Project_Flight.Entity.Flight;
import com.Project_Flight.ResourceNotFoundException;
import com.Project_Flight.Shift;
import com.Project_Flight.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService service;

    @PostMapping("/add")
    public String addFlight(@RequestBody Flight flight) {
        service.add(flight);
        return "The " + flight.getId() + " data has been added...";
    }

    @PostMapping(value = "/search", produces = "application/json")
    public List<Flight> search(@RequestBody Flight request) {
        return service.search(request.getSource(), request.getDestination(), request.getDate(), request.getShift());
    }

    @GetMapping("/{id}")
    public Flight searchById(@PathVariable Long id) {
        return service.searchById(id);
    }

    @GetMapping("/all")
    public Iterable<Flight> retrieveAll() {
        return service.retrieve();
    }

    @PutMapping("/{id}")
    public String updateFlight(@RequestBody Flight flight) {
        service.updateFlight(flight);
        return "The " + flight.getFlightName() + " id's Flight data has been updated...";
    }

    @DeleteMapping("/{id}")
    public String deleteFlight(@PathVariable Long id) {
        service.deleteFlight(id);
        return "The " + id + " flight has been deleted...";
    }

    @DeleteMapping("/all")
    public void deleteAllFlights() {
        service.deleteAll();
    }
}
