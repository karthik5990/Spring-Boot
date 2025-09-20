package com.Project_Flight.repository;

import com.Project_Flight.Entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
    public User findByEmail(String email);
    public Optional<User> findByName(String name);
}
