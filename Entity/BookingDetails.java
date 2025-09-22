
package com.Project_Flight.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Generated;

import java.time.LocalDateTime;

@Entity
@Table
public class BookingDetails {
    @Id
    @Column
    Long id;
    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy HH:mm")
    LocalDateTime bookingTime;
    @Column
    String status;
    @Column
    Integer seatsBooked;
    @Column
    Double totalPrice;
    public BookingDetails() {
    }
    public BookingDetails(Long id, LocalDateTime bookingTime, String status, Integer seatsBooked, Double totalPrice) {
        this.id = id;
        this.bookingTime = bookingTime;
        this.status = status;
        this.seatsBooked = seatsBooked;
        this.totalPrice = totalPrice;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDateTime getBookingTime() {
        return bookingTime;
    }
    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Integer getSeatsBooked() {
        return seatsBooked;
    }
    public void setSeatsBooked(Integer seatsBooked) {
        this.seatsBooked = seatsBooked;
    }
    public Double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
