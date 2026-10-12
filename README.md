# 🏥 Hospital Management System (REST API)

A secure backend system built with **Spring Boot** and **MySQL** to manage hospital operations, appointments, patients,deparment,insurance and doctors.

---

## ⚡ Key Highlights

- **Role-Based Security (JWT):** Secure access for Admin, Doctor, Receptionist, and Patient using Spring Security.
- **Pagination & Sorting:** Server-side pagination (`Pageable`) on high-volume endpoints (`/appointment`, `/patient`, `/doctor`) for fast data loading.
- **Layered DTO Architecture:** Clean separation using Controllers, Services, Repositories, Entities, and DTOs.
- **Global Error Handling:** Centralized API error handling using `@RestControllerAdvice`.

---

## 🛠️ Tech Stack

- **Java & Spring Boot**
- **Spring Data JPA & Hibernate**
- **MySQL Database**
- **Spring Security & JWT**
- **Maven & Lombok**

---

## 📌 Core Endpoints

| Module | Method | Endpoint | Query Params / Notes |
| :--- | :--- | :--- | :--- |
| **Auth** | `POST` | `/api/auth/login` | Returns JWT token |
| **Auth** | `POST` | `/api/auth/register` | Registers new user |
| **Appointment** | `GET` | `/api/appointment/getall` | `?page=0&size=5` (Sorted) |
| **Appointment** | `POST` | `/api/appointment/create` | Schedule appointment |
| **Patient** | `GET` | `/api/patient/getall` | `?page=0&size=5` |
| **Patient** | `POST` | `/api/patient/create` | Add new patient |
| **Doctor** | `GET` | `/api/doctor/getall` | `?page=0&size=5` |
| **Doctor** | `POST` | `/api/doctor/create` | Add new doctor |

---

## 🚀 How to Run Locally
1. **Clone repo:**
   ```bash
   git clone [https://github.com/owaisvu2024/Hospital-Management-System.git](https://github.com/owaisvu2024/Hospital-Management-System.git)

1. Configure Database:
Update your database credentials in src/main/resources/application.properties:

2.Run Application:
./mvnw spring-boot:run



1. **Clone repo:**
   ```bash
   git clone [https://github.com/owaisvu2024/Hospital-Management-System.git](https://github.com/owaisvu2024/Hospital-Management-System.git)
