package com.Project_Flight.service;

import com.Project_Flight.Entity.User;
import com.Project_Flight.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    public void addUser(User user) {
        if (user.getId() == null || user.getId() <= 0)
            throw new RuntimeException("Invalid User ID");
        if (user.getName() == null || user.getName().isEmpty())
            throw new RuntimeException("User Name is required");
        if (user.getEmail() == null || user.getEmail().isEmpty())
            throw new RuntimeException("User Email is required");
        if (!user.getEmail().matches("^[\\w\\.-]+@[\\w\\.-]+\\.[a-zA-Z]{2,}$"))
            throw new RuntimeException("Email must contain '@' and a valid domain extension.");
        if (user.getPassword() == null || user.getPassword().isEmpty())
            throw new RuntimeException("User Password is required");
        if (user.getPurposeOfVisit() == null || user.getPurposeOfVisit().isEmpty())
            throw new RuntimeException("User Purpose of Visit is required");
        if (repo.existsByEmail(user.getEmail()))
            throw new RuntimeException("User with this email already exists");

        repo.save(user);
    }

    public Iterable<User> getAllRecords() {
        return repo.findAll();
    }

    public Optional<User> getById(Long id) {
        if (id == null || id <= 0)
            throw new RuntimeException("Invalid User ID");
        return repo.findById(id);
    }

    public String update(Long id, String email, String password, String purposeOfVisit) {
        if (id == null || id <= 0)
            throw new RuntimeException("Invalid User ID");

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
