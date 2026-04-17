# 🚀 Spring Boot Todo API (Teaching Project)

A complete, professional-grade Spring Boot application designed for teaching purposes. This project demonstrates modern best practices, including Layered Architecture, Security (JWT + RBAC), Database integration (MySQL), and Standardized API Responses.

---

## 📚 Features

*   **CRUD Operations**: Create, Read, Update, Delete Todos.
*   **Authentication**: Secure Login & Registration using **JWT (JSON Web Tokens)**.
*   **Authorization (RBAC)**:
    *   **USER**: Can manage their *own* todos.
    *   **ADMIN**: Can manage *all* todos and delete any todo.
*   **Data Isolation**: Users cannot see or edit each other's data.
*   **Validation**: Input validation (e.g., Title cannot be empty) with clean error messages.
*   **Standardized Responses**: All API responses follow a consistent format (`success`, `message`, `data`).
*   **Global Exception Handling**: Centralized error handling using `@ControllerAdvice`.

---

## 🛠️ Tech Stack

*   **Java 21**
*   **Spring Boot 3.x**
*   **Spring Security** (JWT)
*   **Spring Data JPA** (Hibernate)
*   **MySQL** (Database)
*   **Lombok** (Boilerplate reduction)

---

## 🚀 Getting Started

### 1. Prerequisites
*   Java 21 installed.
*   MySQL installed and running.
*   Maven installed (or use `mvnw`).

### 2. Database Setup
Create a database named `todo_db` in MySQL:
```sql
CREATE DATABASE todo_db;
```
*Note: The application will automatically create the tables (`users`, `todos`) on the first run.*

### 3. Configuration
Check `src/main/resources/application.properties` and update your MySQL username/password if needed:
```properties
spring.datasource.username=root
spring.datasource.password=your_password
```

### 4. Run the Application
```bash
./mvnw spring-boot:run
```
The server will start on **port 5000**.

---

## 🧪 Testing the API

### 1. Register a User
**POST** `http://localhost:5000/api/auth/register`
```json
{
  "username": "john_doe",
  "password": "password123"
}
```

### 2. Register an Admin (Optional)
**POST** `http://localhost:5000/api/auth/register`
```json
{
  "username": "admin_user",
  "password": "password123",
  "role": "ADMIN"
}
```

### 3. Login
**POST** `http://localhost:5000/api/auth/login`
```json
{
  "username": "john_doe",
  "password": "password123"
}
```
*Response:*
```json
{
  "success": true,
  "message": "Login successful",
  "data": {
    "token": "eyJhbGciOiJIUzI1NiJ9..."
  }
}
```
**Copy the token!**

### 4. Create a Todo
**POST** `http://localhost:5000/api/v1/todos/create`
*   **Header**: `Authorization: Bearer <YOUR_TOKEN>`
*   **Body**:
    ```json
    {
      "title": "Learn Spring Boot",
      "description": "Master the basics of REST APIs"
    }
    ```

### 5. Get All Todos
**GET** `http://localhost:5000/api/v1/todos/all`
*   **Header**: `Authorization: Bearer <YOUR_TOKEN>`

---

## 📂 Project Structure

```text
com.example.startSpring
│
├── config/              # Security & App Config
├── controller/          # API Endpoints (The Waiter)
├── service/             # Business Logic (The Chef)
├── repository/          # Database Access (The Pantry)
├── model/               # Database Entities (The Ingredients)
├── dto/                 # Data Transfer Objects (The Menu)
├── exception/           # Global Error Handling
└── security/            # JWT Logic
```

See `Project_Structure_Explained.md` for a detailed breakdown.

---

## 📖 Documentation & Guides

*   [Project Structure Explained](Project_Structure_Explained.md)
*   [Security Guide (JWT & RBAC)](Security_Guide.md)
*   [Authentication & Authorization Guide](Authentication_Authorization_Guide.md)
*   [Spring Boot Cheat Sheet](Spring_Boot_Cheat_Sheet.md)
*   [Best Practices Guide](Todo_App_Best_Practices_Guide.md)

---

## 📝 License
This project is for educational purposes. Feel free to use it to teach or learn!
