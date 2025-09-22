package com.Project_Flight.repository;

import com.Project_Flight.Entity.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookingRepo extends CrudRepository<Booking, Long> {
    List<Booking> findAllByUserId(Long id);
    boolean existsByFlightIdAndBookingDetails_SeatsBooked(Long flightId, Integer seatsBooked);
    boolean existsByUserIdAndFlightIdAndBookingDetails_SeatsBooked(Long userId, Long flightId, Integer seatsBooked);
}
