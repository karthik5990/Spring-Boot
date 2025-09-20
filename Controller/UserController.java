package com.Project_Flight.Controller;

import com.Project_Flight.Entity.User;
import com.Project_Flight.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
@RestController
public class UserController {
    @Autowired
    UserService service;
    @PostMapping(value="/user/add")
    public String addUser(@RequestBody User user){
        service.addUser(user);
        return "The user "+user.getName()+" has been added...";
    }
    @GetMapping(value="/user/all")
    public Iterable<User> allUsers(){
        return service.getAllRecords();
    }
    @GetMapping(value="/user/{id}")
    public Optional<User> getById(@PathVariable("id") Long idNo){
        return service.getById(idNo);
    }
    @PutMapping(value="/user/update/{id}")
    public String update(@PathVariable("id") Long id, @RequestParam String email, @RequestParam String password, @RequestParam String purposeOfVisit){
        service.update(id, email, password, purposeOfVisit);
        return "The Values Has Been Updated...";
    }
}
