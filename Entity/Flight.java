package com.Project_Flight.Entity;
import com.Project_Flight.Shift;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table

public class Flight {
    @Id
    @Column
    Long id;
    @Column
    String flightNumber;
    @Column
    String flightName;
    @Column
    String source;
    @Column
    String destination;
    @Column
    @JsonFormat(pattern = "dd-MM-yyyy")
    LocalDate date;

    @Column
    @Enumerated(EnumType.STRING)
    Shift shift;
    @Column
    double price;
    @Column
    Integer availableSeats;
    public Flight() {
    }
    public Flight(Long id, String flightNumber, String flightName, String source, String destination, LocalDate date, Shift shift, double price, Integer availableSeats) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.flightName=flightName;
        this.source = source;
        this.destination = destination;
        this.date = date;
        this.shift=shift;
        this.price = price;
        this.availableSeats = availableSeats;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFlightNumber() {
        return flightNumber;
    }
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }
    public String getFlightName() {
        return flightName;
    }
    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }
    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }
    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public Shift getShift() {
        return shift;
    }
    public void setShift(Shift shift) {
        this.shift = shift;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public Integer getAvailableSeats() {
        return availableSeats;
    }
    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }
}
