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

    //POST: http://localhost:2003/user/add
    @PostMapping(value="/user/add")
    public String addUser(@RequestBody User user){
        if (user.getId() == null) {
            throw new RuntimeException("User ID is required");
        }
        if (user.getName() == null || user.getName().isEmpty()) {
            throw new RuntimeException("User Name is required");
        }
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new RuntimeException("User Email is required");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new RuntimeException("User Password is required");
        }
        if (user.getPurposeOfVisit() == null || user.getPurposeOfVisit().isEmpty()) {
            throw new RuntimeException("User Purpose of Visit is required");
        }
        service.addUser(user);
        return "The user "+user.getName()+" has been added...";
    }

    //GET: http://localhost:2003/user/all
    @GetMapping(value="/user/all")
    public Iterable<User> allUsers(){
        return service.getAllRecords();
    }

    //GET: http://localhost:2003/user/8
    @GetMapping(value="/user/{id}")
    public Optional<User> getById(@PathVariable("id") Long idNo){
        if(!service.getById(idNo).isEmpty()){
            return service.getById(idNo);
        }
        throw new RuntimeException("User not found with ID: "+idNo);
    }

    //PUT: http://localhost:2003/user/update/4?email=hothri22@gmail.com&password=Hothri@22&purposeOfVisit=Research
    @PutMapping(value="/user/update/{id}")
    public String update(@PathVariable("id") Long id, @RequestParam String email, @RequestParam String password, @RequestParam String purposeOfVisit){
        if (!(email == null) && !(password == null) && !(purposeOfVisit == null)) {
            service.update(id, email, password, purposeOfVisit);
            return "The Values Has Been Updated...";
        }
        throw new RuntimeException("At least one field (email, password, purposeOfVisit) must be provided for update");
    }
}
