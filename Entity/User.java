package com.Project_Flight.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Passenger")
public class User {
    @Id
    @Column
    Long id;
    @Column(unique = true)
    String name;
    @Column(unique = true)
    String email;
    @Column
    String password;
    @Column
    String purposeOfVisit;
    public User() {
    }
    public User(Long id, String name, String email, String password, String purposeOfVisit) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.purposeOfVisit = purposeOfVisit;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPurposeOfVisit() {
        return purposeOfVisit;
    }
    public void setPurposeOfVisit(String purposeOfVisit) {
        this.purposeOfVisit = purposeOfVisit;
    }
}
