# Course Management API

A RESTful Course Management API using Java Spring Boot with authentication and role-based access control.

---

## 📚 Table of Contents

- [Features](#features)  
- [Tech Stack](#tech-stack)  
- [Getting Started](#getting-started)  
- [Running the App](#clone-build-and-run)  
- [Authentication](#authentication)  
- [API Endpoints](#api-endpoints)  
- [Project Structure](#project-structure)  
- [MYSQL Database](#mysql-database)  
- [Notes](#notes)

---

## ✅ Features

- JWT-based user authentication  
- Role-based access control (ADMIN, INSTRUCTOR, STUDENT)  
- CRUD operations for courses  
- RESTful API design  
- Input validation
- MYSQL database for testing

---

## 💻 Tech Stack

- Java 17  
- Spring Boot 3.4.4  
- Spring Security  
- JWT (Java Web Token)  
- MYSQL Database  
- Maven  

---

## 🚀 Getting Started

### 🔧 Prerequisites

- Java 17+  
- Maven 3.8+

---

## 📦 Clone, Build and Run

```bash
git clone https://github.com/KhineSwe20/CourseManagement.git
cd CourseManagement
mvn clean install
mvn spring-boot:run
```

The application will start at:
http://localhost:8080

---

## 🔐 Authentication

### 🔸 Register

```
POST /api/auth/register
```

### 🔸 Login

```
POST /api/auth/login
```

Returns a JWT token for authorization.

Use the token in the Authorization header:

```http
Authorization: Bearer <your_token>
```

---

## 🔁 API Endpoints

| Method | Endpoint              				| Role          | Description         								|
|--------|--------------------------------------|---------------|--------------------------------------------------	|
| POST   | `/api/auth/login`     				| All Roles		| Login endpoint for all users; return a JWT token	|
| POST   | `/api/auth/register`  				| ADMIN 		| Create a new user   								|
| POST   | `/api/courses`        				| INSTRUCTOR    | Create a course     								|
| GET    | `/api/courses`        				| All Roles 	| List all courses    								|
| PUT    | `/api/courses/{id}`   				| INSTRUCTOR    | Update a course (only if owned by the INSTRUCTOR)	|
| DELETE | `/api/courses/{id}`   				| INSTRUCTOR    | Delete a course (only if owned by the INSTRUCTOR) |
| POST   | `/api/courses/{courseId}/enroll`		| STUDENT    	| Enroll in a course     							|
| GET    | `/api/students/{studentId}/courses`  | STUDENT    	| Get courses a student is enrolled in     			|

---

## 🗂️ Project Structure

```bash
src/main/java/com/cdsg/coursemanagement
├── config            # Security configuration
├── controller        # REST controllers (Course, Enrollment, Login)
├── dto               # Data Transfer Objects
├── entity            # JPA entities (BaseEntity, Course, Enrollment, User)
├── enums			  # Enum class for User Role (ADMIN, INSTRUCTOR, STUDENT)
├── repository        # Spring Data JPA repositories
├── security          # JWT filters, token utilities
├── service           # Service interface (business logic)
├── service/impl      # Service implementations
├── util		      # Utility and constant classes

src/main/resources
└── application.properties # Spring Boot Application Config
```

---

## 🧪 MYSQL Database

- **Host:** `localhost`  
- **Port:** `3306`  
- **Database name:** `course_management`  
- **Username:** `your_mysql_user`  
- **Password:** `your_mysql_password`

Make sure to create the database manually or enable `spring.jpa.hibernate.ddl-auto=create` for auto-generation.

### 🔧 Sample `application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/course_management?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=root

spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

---

## 📝 Notes

- As for the sample request data, please refer to "Course Management.postman_collection.json"
- No frontend included.

---
