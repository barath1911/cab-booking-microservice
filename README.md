# 🚖 Cab Booking Microservice Project

A scalable Cab Booking Application built using Spring Boot Microservices Architecture.

---

# 📌 Architecture

Client → API Gateway → Microservices → Database

Services communicate using Eureka Service Discovery.

---

# 🛠️ Tech Stack

- Java 21
- Spring Boot
- Spring Cloud
- Eureka Server
- API Gateway
- Spring Data JPA
- MySQL
- Maven
- REST API
- Microservices Architecture

---

# 📂 Microservices

## 1️⃣ Eureka Server
- Service Registry
- Handles service discovery

## 2️⃣ API Gateway
- Single entry point for all APIs
- Routes requests to respective services

## 3️⃣ User Service
- User registration
- User management

## 4️⃣ Driver Service
- Driver availability
- Driver management

## 5️⃣ Ride Service
- Ride booking
- Ride status management
- Ride tracking

## 6️⃣ Payment Service
- Payment processing
- Payment status management

---

# 🔥 Features

- Service Discovery using Eureka
- API Gateway Routing
- RESTful APIs
- Microservice Communication
- Ride Booking System
- Driver Allocation
- Payment Handling
- Scalable Architecture

---

# 📁 Project Structure

```text
cab-booking-microservice
│
├── eureka-server
├── api-gateway
├── user-service
├── driver-service
├── ride-service
├── payment-service
│
├── README.md
└── .gitignore
