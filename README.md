# Hospital Management System 🏥

A secure REST API built with Spring Boot to manage daily hospital operations like patients, doctors, and appointments.

## 🚀 Tech Stack
**Java 26** | **Spring Boot 4.1.1** | **MySQL** | **Spring Security + JWT**

## ⚙️ How to Run
1. Create an `application.properties` file in `src/main/resources/` with your local database details:

spring.datasource.url=jdbc:mysql://localhost:3306/Hospital_Managment?createDatabaseIfNotExist=true
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
spring.jpa.hibernate.ddl-auto=update

2. Start the application. It will run on `http://localhost:8084`.

## 🔐 Security & Authentication
* Send your username and password to `POST /login` to receive a secure JWT token.
* Include this token in the header as `Authorization: Bearer <token>` to access all other secured endpoints in the system.
