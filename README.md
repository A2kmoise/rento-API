# Rental App (Spring Boot)

A role-based rental management system built with **Spring Boot** where **OWNERS** can list houses for rent and **TENANTS** can book and pay for them. The application supports **role-based access control**, **multiple payment methods**, and **SMS notifications**.

---

##  Features

###  Roles

* **OWNER**

  * Add and manage rental properties
  * View bookings and payments

* **TENANT**

  * Browse available houses
  * Book houses
  * Make payments

Roles are managed using a **single User entity** with role-based authorization.

---

##  Payments

* Supports **two payment methods** (pluggable design)

  * Mobile Money
  * Card Payment
* Easy to extend with additional payment providers

---

##  SMS Notifications

* Integrated with **one SMS service provider**
* Used for:

  * Payment confirmation
  * Booking confirmation

SMS logic is isolated to allow easy provider replacement.

---

##  Project Structure (Layer-based)

```text
com.yourapp.rentalapp
│
├── controller     # REST controllers
├── service        # Business logic
│   ├── payment    # Payment implementations
│   └── sms        # SMS services
├── repository     # JPA repositories
├── model          # Entities & enums
├── dto            # Request/Response objects
├── security       # Spring Security & JWT
├── config         # App configuration
├── exception      # Global exception handling
└── RentalAppApplication.java
```

---

##  Tech Stack

* Java 17+
* Spring Boot
* Spring Data JPA
* Spring Security (JWT)
* MySQL
* Maven
* IntelliJ IDEA Ultimate

---

##  Setup Instructions

### 1️ Prerequisites

* Java 17 or higher
* Maven
* MySQL

Verify:

```bash
java -version
mvn -version
```

---

###  Database Setup

Create database:

```sql
CREATE DATABASE rental_app;
```

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/rental_app
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---
