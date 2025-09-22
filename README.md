✈️ Flight Booking System

📖 Overview

This project is a Spring Boot Flight Booking System that enables users to:
•	Manage flights (Add, Search, Update, Delete).
•	Book flights and modify/cancel bookings.
•	View booking history for each user.
The system follows a layered architecture (Entity → Repository → Service → Controller) ensuring maintainability and scalability.
________________________________________


🛠 Technologies Used

•	Java 23
•	Spring Boot 3.x
•	Spring Data JPA / Hibernate
•	H2 Database (or MySQL/PostgreSQL)
•	Maven
•	Lombok (optional)
•	Postman / Swagger for API testing
________________________________________


📂 Project Structure

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


🚀 Setup, Build, and Run Instructions

Prerequisites

Java or higher installed on your system.
Maven build tool installed.
Heidi SQL installed and running.
Git installed for version control.

Setup Your Database

Create a new database in Heidi SQL.
Update your application's src/main/resources/application.properties with your database details.
Run Spring Boot Application of Banking****
Use tools like Postman to test the REST API endpoints.

🌐 API Endpoints

✈️ Flight Endpoints

Method	      Endpoint	             Description
POST	-	/flights/add	-	Add a new flight record
POST	-	/flights/search	-	Search flights by source, destination, date, and shift
GET	    -	/flights/{id}	-	Retrieve a flight by its ID
PUT	    -	/flights/{id}	-	Update flight details by ID
DELETE	-	/flights/{id}	-	Delete a flight by ID


👤 User Endpoints

Method	     Endpoint	     Description
POST	-	/users/add	-	Add a new user
GET	    -   /users/{id}	-	Get user details by ID
PUT	  	-   /users/{id}	-	Update user details
DELETE	-	/users/{id}	-	Delete a user

🧾 Booking Endpoints

Method	         Endpoint	                                              Description
POST	-	/bookings/{id}?userId={userId}&flightId={flightId}	-	Add a booking for a user and flight
GET	    -	/bookings/{id}	                                    -	Retrieve booking by ID
PUT	    -	/bookings/{id}	                                    -	Update booking details by ID
DELETE	-	/bookings/{id}	                                    -	Cancel a booking by ID
GET	    -	/bookings/history/{userId}	                        -	Get booking history for a specific user

