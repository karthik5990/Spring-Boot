package com.Project_Flight.repository;


import com.Project_Flight.Entity.Flight;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FlightRepo extends CrudRepository<Flight, Long> {
    public List<Flight> findBySourceAndDestinationAndDate(String source, String destination, LocalDate date);
}
