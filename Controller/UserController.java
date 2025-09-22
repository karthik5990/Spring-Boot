package com.Project_Flight.Controller;

import com.Project_Flight.Entity.User;
import com.Project_Flight.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/add")
    public String addUser(@RequestBody User user) {
        service.addUser(user);
        return "The user " + user.getName() + " has been added.";
    }

    @GetMapping("/all")
    public Iterable<User> getAll() {
        return service.getAllRecords();
    }

    @GetMapping("/{id}")
    public Optional<User> getById(@PathVariable Long id) {
        if (service.getById(id).isEmpty()) {
            throw new RuntimeException("User not found with ID: " + id);
        }
        return service.getById(id);
    }

    @PutMapping("/update/{id}")
    public String update(@PathVariable Long id,
                         @RequestParam(required = false) String email,
                         @RequestParam(required = false) String password,
                         @RequestParam(required = false) String purposeOfVisit) {
        if (email == null && password == null && purposeOfVisit == null) {
            throw new RuntimeException("At least one field (email, password, purposeOfVisit) must be provided for update");
        }
        return service.update(id, email, password, purposeOfVisit);
    }
}
