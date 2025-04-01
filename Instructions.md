#  Popcorn Palace - Movie Ticket Booking System
##  Project Overview

**Popcorn Palace** is a RESTful backend system built with **Java Spring Boot**, designed to manage movie data, showtimes, and ticket bookings.

It supports full CRUD operations for movies and showtimes, as well as secure seat booking logic — while enforcing business rules like:

- No overlapping showtimes per theater
- No double-booking the same seat
- Automatic cascade deletion for related data (e.g. bookings deleted when a showtime is removed)
- Strong input validation on movie, showtime, and booking creation

The app ensures clean architecture, layered design (controllers, services, repositories, DTOs, mappers), and proper error handling across all endpoints.

---

##  Tech Stack

This project was developed using the following technologies:

- **Java 21** – Core programming language for backend logic
- **Spring Boot 3.4.2** – Framework for building RESTful APIs
- **PostgreSQL** – Relational database for storing movies, showtimes, and bookings
- **Docker** – Used to run PostgreSQL in a containerized environment via `compose.yml`
- **Hibernate (JPA)** – ORM for managing entity relationships and schema generation
- **Lombok** – To reduce boilerplate code (getters, setters, constructors)
- **Maven** – Project build and dependency management tool
- **HikariCP** – Connection pooling for efficient DB access (auto-configured by Spring Boot)



---

##  How to Run the Project

Follow these steps to run the backend project locally using Docker for the PostgreSQL database:

### 1. Clone the repository
```bash
git clone https://github.com/lubabanairoukh/popcorn-palace.git
cd popcorn-palace
```


### 2. Start PostgreSQL via Docker

Make sure **Docker Desktop** is installed and running.

Then in your terminal, navigate to the project folder (where `compose.yml` is located) and run:

```bash
docker compose up
```


### 3. Run the Spring Boot Application

You can run the backend in either of the following ways:

####  Using Maven (recommended):
```bash
./mvnw spring-boot:run
```
### 4. Access and Test the APIs

Once the app is running, you can use tools like **Postman**, **cURL**, or your browser to test the endpoints.

---


## How to Run the Tests

You can run the tests using Maven directly from the command line:

```bash
./mvnw test
```

---

##  Implemented Features

The following features were fully implemented across the Movies, Showtimes, and Bookings APIs:

###  Movie Management
- [x] Add a new movie
- [x] Update existing movie details
- [x] Delete a movie
- [x] Fetch all movies
- [x] Validation:
    - `title` and `genre` are required
    - `duration` must be at least 1 minute
    - `rating` must be between 0 and 10

###  Showtime Management
- [x] Add a new showtime
- [x] Update existing showtime
- [x] Delete a showtime
- [x] Fetch showtime by ID
- [x] Check that movie ID exists before adding a showtime
- [x] Validate showtime duration equals the movie’s duration
- [x] Prevent overlapping showtimes in the same theater
- [x] Automatically delete all showtimes when a movie is deleted

###  Booking System
- [x] Book a seat for a showtime
- [x] Prevent double-booking the same seat for the same showtime
- [x] Automatically delete all bookings when a showtime is deleted
- [x] Validation:
    - `seatNumber` must be ≥ 1
    - `userId` and `showtimeId` are required
