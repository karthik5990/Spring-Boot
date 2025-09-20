package com.Project_Flight.repository;

import com.Project_Flight.Entity.BookingDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingDetailsRepo extends CrudRepository<BookingDetails, Long> {
}
