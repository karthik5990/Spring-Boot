package com.Project_Flight.service;

import com.Project_Flight.Entity.User;
import com.Project_Flight.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
@Service
public class UserService {
    @Autowired
    UserRepo repo;
    public void addUser(User user){
        boolean userExists = repo.existsByEmail(user.getEmail());
        if (userExists)
            throw new RuntimeException("User already exists with this email");
        repo.save(user);
    }
    public Iterable<User> getAllRecords(){
        return repo.findAll();
    }
    public Optional<User> getById(Long id){
        return repo.findById(id);
    }
    public String update(Long id, String email, String password, String purposeOfVisit) {
        Optional<User> op = repo.findById(id);
        if (op.isEmpty()) {
            throw new RuntimeException("User not found with ID: " + id);
        }
        User user = op.get();
        if (email != null)
            user.setEmail(email);
        if (password != null)
            user.setPassword(password);
        if (purposeOfVisit != null)
            user.setPurposeOfVisit(purposeOfVisit);
        repo.save(user);
        return "The values have been updated for user ID: " + id;
    }
}
