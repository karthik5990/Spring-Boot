Flight Booking System
Overview

This project is a Spring Boot Flight Booking System designed to manage flights, users, and bookings. It allows users to:

Add, update, retrieve, and delete flights.

Book flights and manage bookings.

View booking history.

The project follows a layered architecture with Entities, Services, and Controllers.

Technologies Used

Java 23

Spring Boot 3.x

Spring Data JPA / Hibernate

H2 Database (or MySQL/PostgreSQL)

Maven

Lombok (optional)

Postman / Swagger for API testing

Project Structure
com.Project_Flight
│
├─ Entity
│   ├─ Booking.java
│   ├─ Flight.java
│   └─ User.java
│
├─ Repository
│   ├─ BookingRepo.java
│   ├─ FlightRepo.java
│   └─ UserRepo.java
│
├─ Service
│   ├─ BookingService.java
│   ├─ FlightService.java
│   └─ UserService.java
│
├─ Controller
│   ├─ BookingController.java
│   ├─ FlightController.java
│   └─ UserController.java
│
└─ Application.java

Setup Instructions

Clone the repository:

git clone https://github.com/<your-username>/flight-booking-system.git


Navigate to the project directory:

cd flight-booking-system


Build the project using Maven:

mvn clean install


Run the Spring Boot application:

mvn spring-boot:run


Test APIs at http://localhost:2003/ using Postman or Swagger.

Flight Endpoints
1) POST: Add a Flight

URL: http://localhost:2003/flights/add

Request JSON:

{
    "id": 101,
    "flightNumber": "AI202",
    "flightName": "Air India Express",
    "source": "New York",
    "destination": "London",
    "date": "22-09-2025",
    "shift": "EVENING",
    "price": 850.75,
    "availableSeats": 134
}

2) POST: Search for a Flight

URL: http://localhost:2003/flights/search

Request JSON:

{
  "source": "New York",
  "destination": "London",
  "date": "22-09-2025",
  "shift": "EVENING"
}


Response Example:

[
  {
    "id": 101,
    "flightNumber": "AI202",
    "flightName": "Air India Express",
    "source": "New York",
    "destination": "London",
    "date": "22-09-2025",
    "shift": "EVENING",
    "price": 850.75,
    "availableSeats": 134
  }
]

3) GET: Get a Flight by ID

URL: http://localhost:2003/flights/110

Response Example:

{
    "id": 110,
    "flightNumber": "BA303",
    "flightName": "British Airways",
    "source": "London",
    "destination": "New York",
    "date": "25-09-2025",
    "shift": "MORNING",
    "price": 950.0,
    "availableSeats": 150
}

4) PUT: Update a Flight

URL: http://localhost:2003/flights/215

Request JSON:

{
    "id": 215,
    "flightNumber": "KP02",
    "flightName": "Kantara Express",
    "source": "Kandukur",
    "destination": "Kakinada",
    "date": "24-09-2025",
    "shift": "AFTERNOON",
    "price": 1000.0,
    "availableSeats": 120
}

5) DELETE: Delete a Flight

URL: http://localhost:2003/flights/215

Response:

The 215 flight has been deleted...

Booking Endpoints
6) POST: Add Booking

URL: http://localhost:2003/bookings/7?userId=1&flightId=108

Request JSON:

{
  "id": 4,
  "seatsBooked": 3
}

Response Example:

{
    "id": 4,
    "user": {
        "id": 3,
        "name": "Hothri",
        "email": "hothri22@gmail.com",
        "password": "Hothri1222",
        "purposeOfVisit": "Work-Based"
    },
    "flight": {
        "id": 178,
        "flightNumber": "6E320",
        "flightName": "IndiGo",
        "source": "New Delhi",
        "destination": "Hong Kong",
        "date": "26-09-2025",
        "shift": "NIGHT",
        "price": 5500.0,
        "availableSeats": 147
    },
    "bookingDetails": {
        "id": 4,
        "bookingTime": "22-09-2025 15:53",
        "status": "BOOKING-CONFIRMED",
        "seatsBooked": 2,
        "totalPrice": 16500.0
    }
}

7) GET: Get Booking by ID

URL: http://localhost:2003/bookings/4

Response Example:

{
    "id": 4,
    "user": {
        "id": 3,
        "name": "Hothri",
        "email": "hothri22@gmail.com",
        "password": "Hothri1222",
        "purposeOfVisit": "Work-Based"
    },
    "flight": {
        "id": 178,
        "flightNumber": "6E320",
        "flightName": "IndiGo",
        "source": "New Delhi",
        "destination": "Hong Kong",
        "date": "26-09-2025",
        "shift": "NIGHT",
        "price": 5500.0,
        "availableSeats": 147
    },
    "bookingDetails": {
        "id": 4,
        "bookingTime": "22-09-2025 15:53",
        "status": "BOOKING-CONFIRMED",
        "seatsBooked": 2,
        "totalPrice": 16500.0
    }
}
8) PUT: Update Booking

URL: http://localhost:2003/bookings/4

Request JSON:

{
    "id": 4,
    "seatsBooked": 2
}


Response Example:

{
    "id": 4,
    "userId": 3,
    "flightId": 178,
    "seatsBooked": 2,
    "bookingDate": "22-09-2025",
    "totalPrice": 16500
}

9) DELETE: Delete Booking

URL: http://localhost:2003/bookings/4

Response:

Booking cancelled with ID: 4

10) GET: Get User Booking History

URL: http://localhost:2003/bookings/history/1

Response Example:

[
    {
        "id": 4,
        "user": {
            "id": 3,
            "name": "Hothri",
            "email": "hothri22@gmail.com",
            "password": "Hothri1222",
            "purposeOfVisit": "Work-Based"
        },
        "flight": {
            "id": 178,
            "flightNumber": "6E320",
            "flightName": "IndiGo",
            "source": "New Delhi",
            "destination": "Hong Kong",
            "date": "26-09-2025",
            "shift": "NIGHT",
            "price": 5500.0,
            "availableSeats": 147
        },
        "bookingDetails": {
            "id": 4,
            "bookingTime": "22-09-2025 15:29",
            "status": "Booking-Cancelled...",
            "seatsBooked": 2,
            "totalPrice": 16500.0
        }
    }
]
