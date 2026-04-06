# Finance Data Processing & Access Control Backend

## 🚀 Key Highlights

* Built using Spring Boot with layered architecture
* Implemented full CRUD operations
* Added Role-Based Access Control (ADMIN, ANALYST, VIEWER)
* Developed financial summary APIs (income, expense, balance)
* Implemented global exception handling for clean error responses

---

## 📌 Overview

This project is a backend system built using **Spring Boot** for managing financial records with **role-based access control**.

It simulates a finance dashboard where different users (Admin, Analyst, Viewer) interact with financial data based on their permissions.

---

## 🛠 Tech Stack

* Java 17+
* Spring Boot
* Spring Data JPA
* H2 Database (in-memory)
* Maven

---

## ✨ Features

### 👤 User Management

* Create users
* Assign roles (ADMIN, ANALYST, VIEWER)
* Fetch all users

---

### 💰 Financial Records Management

* Create financial records
* View all records
* Update records
* Delete records
* Filter by:

  * Type (INCOME / EXPENSE)
  * Category

---

### 📊 Dashboard Summary

Provides aggregated insights:

* Total Income
* Total Expense
* Net Balance
* Category-wise totals

---

### 🔐 Role-Based Access Control

| Role    | Permissions            |
| ------- | ---------------------- |
| ADMIN   | Full access (CRUD)     |
| ANALYST | View records + summary |
| VIEWER  | View only              |

---

### ⚠️ Error Handling

* Global exception handler
* Proper error responses
* HTTP Status codes (403 for forbidden actions)

---

## 🔗 API Endpoints

### 👥 Users

| Method | Endpoint            | Description   |
| ------ | ------------------- | ------------- |
| POST   | `/users?role=ADMIN` | Create user   |
| GET    | `/users`            | Get all users |

---

### 📁 Records

| Method | Endpoint                          | Description        |
| ------ | --------------------------------- | ------------------ |
| POST   | `/records?role=ADMIN`             | Create record      |
| GET    | `/records?role=ADMIN`             | Get all records    |
| PUT    | `/records/{id}?role=ADMIN`        | Update record      |
| DELETE | `/records/{id}?role=ADMIN`        | Delete record      |
| GET    | `/records/type?type=INCOME`       | Filter by type     |
| GET    | `/records/category?category=Food` | Filter by category |
| GET    | `/records/summary?role=ANALYST`   | Get summary        |

---

## ▶️ How to Run

1. Clone the repository
2. Run:

   ```bash
   mvnw spring-boot:run
   ```
3. Open: http://localhost:8080

---

## 🧪 Sample Request

### Create Record

```json
POST /records?role=ADMIN

{
  "amount": 5000,
  "type": "INCOME",
  "category": "Salary",
  "date": "2026-04-04",
  "description": "Monthly salary",
  "userId": 1
}
```

---

## 📝 Note

Role-based access is simulated using request parameters for simplicity.
