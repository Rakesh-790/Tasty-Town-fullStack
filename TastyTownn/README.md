# 🍔 Tasty-Town a Food Delivery Backend API 

This project is the **backend of a Food Delivery Web Application**, built using **Spring Boot**. It provides RESTful APIs for managing users, food items, cart, and orders. It includes **Spring Security with JWT authentication**, **Spring Data JPA**, **MySQL integration**, and **global exception handling**.

---

## 🚀 Features

- User Registration and Login with JWT Authentication
- Role-based Access Control (e.g., USER, ADMIN)
- Secure REST APIs with Spring Security
- Password encryption using BCrypt
- CRUD operations on Users, Foods, Cart, Orders
- JPA Repository with custom methods
- Global Exception Handling (`@ControllerAdvice`)
- DTO pattern for request/response models

---

## 🔐 Authentication & Security

- Uses **Spring Security + JWT** for stateless authentication
- Access token returned after login
- Protected routes based on roles
- Passwords stored securely using **BCrypt**

### Role Examples:
- `USER`: can browse foods, place orders
- `ADMIN`: can add/edit/delete foods, view all orders

### Authentication Flow:

1. Register using `/api/v1/auth/register`
2. Login using `/api/v1/auth/login`
3. Receive JWT token in response
4. Pass JWT token in `Authorization: Bearer <token>` header for all protected endpoints

---

## 🧱 Tech Stack

- Java 17+
- Spring Boot
- Spring Security
- JWT (JSON Web Tokens)
- Spring Data JPA
- MySQL
- Maven
- Lombok

---



