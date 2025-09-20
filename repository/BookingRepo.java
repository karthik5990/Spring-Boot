package com.Project_Flight.repository;

import com.Project_Flight.Entity.Booking;
import com.Project_Flight.Entity.BookingDetails;

import com.Project_Flight.Entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepo extends CrudRepository<Booking, Long> {
    public List<BookingDetails> findByUser(User user);
    public List<Booking> findAllByUserId(Long Id);

}
