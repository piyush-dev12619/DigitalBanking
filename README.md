# Digital Banking Platform - Help Guide

## Table of Contents
1. [Project Overview](#project-overview)
2. [Prerequisites](#prerequisites)
3. [Setup and Installation](#setup-and-installation)
4. [Building the Project](#building-the-project)
5. [Running the Application](#running-the-application)
6. [API Documentation](#api-documentation)
7. [Project Structure](#project-structure)
8. [Key Features](#key-features)
9. [Configuration](#configuration)
10. [Database Setup](#database-setup)
11. [Email Configuration](#email-configuration)
12. [Caching](#caching)
13. [Testing](#testing)
14. [Troubleshooting](#troubleshooting)
15. [Contributing Guidelines](#contributing-guidelines)

---

## Project Overview

**Digital Banking Platform** is a Spring Boot-based backend application designed to manage digital banking operations including:
- Customer onboarding and management
- Account creation and management
- Customer Information File (CIF) handling
- Email notifications for banking transactions
- REST API endpoints with OpenAPI/Swagger documentation

**Technology Stack:**
- Java 21
- Spring Boot 4.1.1
- MongoDB (NoSQL Database)
- Maven (Build Tool)
- Swagger/OpenAPI 3.0 (API Documentation)
- Lombok (Boilerplate Code Generation)
- Spring Mail (Email Service)
- Spring Caching

---

## Prerequisites

Before you begin, ensure you have the following installed:

1. **Java Development Kit (JDK)**: Version 21 or higher
   - Download from [oracle.com](https://www.oracle.com/java/technologies/downloads/)
   - Verify: `java -version`

2. **Apache Maven**: Version 3.6.0 or higher
   - Download from [maven.apache.org](https://maven.apache.org/download.cgi)
   - Verify: `mvn -version`

3. **MongoDB**: Version 5.0 or higher
   - Download from [mongodb.com](https://www.mongodb.com/try/download/community)
   - Default: `localhost:27017`

4. **Git**: For version control
   - Download from [git-scm.com](https://git-scm.com/)

---

## Setup and Installation

### Step 1: Clone Repository

```bash
git clone https://github.com/piyush-dev12619/DigitalBanking.git
cd DigitalBanking
```

### Step 2: Install Dependencies

```bash
mvnw clean install
```

---

## Building the Project

```bash
# Build with tests
mvnw clean package

# Build without tests
mvnw clean package -DskipTests
```

Output: `target/DigitalBanking-0.0.1-SNAPSHOT.jar`

---

## Running the Application

### Prerequisites: MongoDB must be running

### Option 1: Using Maven

```bash
mvnw spring-boot:run
```

### Option 2: Using Java

```bash
java -jar target/DigitalBanking-0.0.1-SNAPSHOT.jar
```

**Access Application:**
- Base URL: `http://localhost:8080`
- Swagger UI: `http://localhost:8080/swagger-ui.html`

---

## API Documentation

### Swagger UI Access

- **Interactive UI:** http://localhost:8080/swagger-ui.html
- **OpenAPI JSON:** http://localhost:8080/v3/api-docs

### Main Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/customer` | Create new customer |
| GET | `/customer/{id}` | Get customer details |
| PUT | `/customer/{id}` | Update customer |
| DELETE | `/customer/{id}` | Delete customer |
| POST | `/account` | Create new account |
| GET | `/account/{id}` | Get account details |
| POST | `/cif` | Create CIF record |
| GET | `/cif/{id}` | Get CIF details |

---

## Project Structure

```
src/main/java/com/piyush/DigitalBanking/
├── Controller/
│   ├── CustomerController.java
│   ├── AccountController.java
│   └── CIFController.java
├── Service/
│   ├── CustomerOnboardingService.java
│   ├── AccountService.java
│   ├── CIFService.java
│   └── EmailService.java
├── Entity/
│   ├── Customer.java
│   ├── Account.java
│   └── CIF.java
├── Repository/
│   └── (MongoDB repositories)
└── SwaggerConfig.java

src/main/resources/
└── application.properties
```

---

## Key Features

- ✅ Customer Management (Create, Read, Update, Delete)
- ✅ Account Management
- ✅ Customer Information File (CIF) Handling
- ✅ Email Notifications (Gmail SMTP)
- ✅ API Documentation (Swagger/OpenAPI)
- ✅ Input Validation
- ✅ Caching (Reduce DB calls)
- ✅ RESTful Architecture

---

## Configuration

**File:** `src/main/resources/application.properties`

### Key Settings

```properties
# Application
spring.application.name=DigitalBanking

# MongoDB
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=AccountsOpneningDB

# Email (Gmail)
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your-email@gmail.com
spring.mail.password=your-app-password

# SMTP
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

# Logging
logging.level.org.springframework.cache=TRACE
```

### Change Database Name

Edit `spring.data.mongodb.database` to your desired database name.

---

## Database Setup

### Start MongoDB

**Windows:**
```bash
net start MongoDB
```

**macOS:**
```bash
brew services start mongodb-community
```

**Linux:**
```bash
sudo systemctl start mongodb
```

### Verify Connection

```bash
mongosh
show dbs
exit
```

---

## Email Configuration

### Gmail Setup

1. Enable 2-Factor Authentication
2. Visit: https://myaccount.google.com/apppasswords
3. Generate an "App Password"
4. Update `application.properties`:

```properties
spring.mail.username=your-email@gmail.com
spring.mail.password=your-app-password
```

⚠️ **Never commit credentials to Git!** Use environment variables for production.

---

## Caching

Caching is enabled in the application:

```java
@EnableCaching
```

### Using Cache

```java
@Cacheable("customers")
public Customer getCustomer(String id) {
    // Query database
}
```

---

## Testing

```bash
# Run all tests
mvnw test

# Run specific test
mvnw test -Dtest=CustomerControllerTest

# View coverage
mvnw clean test jacoco:report
```

---

## Troubleshooting

### MongoDB Connection Error

```bash
# Start MongoDB
mongosh
```

### Port 8080 Already in Use

```properties
# Change in application.properties
server.port=8081
```

### Email Not Sending

1. Verify credentials in `application.properties`
2. Ensure Gmail app password (not regular password)
3. Enable 2FA on Gmail account
4. Check firewall (allow port 587)

### Swagger UI Not Found (404)

- Restart application
- Access: `http://localhost:8080/swagger-ui.html`
- Verify springdoc-openapi dependency in pom.xml

### Enable Debug Logs

```properties
logging.level.root=INFO
logging.level.com.piyush=DEBUG
```

---

## Contributing Guidelines

### Code Standards

- Use PascalCase for classes: `CustomerService`
- Use camelCase for methods: `getCustomerById()`
- Use UPPER_CASE for constants: `DEFAULT_TIMEOUT`
- 4 spaces indentation
- Max line length: 120 characters

### Folder Organization

- `Controller/` - REST endpoints
- `Service/` - Business logic
- `Entity/` - Data models
- `Repository/` - Data access
- `Config/` - Spring configuration

### Git Workflow

```bash
# Create feature branch
git checkout -b feature/feature-name

# Make changes and test
mvnw test

# Commit
git commit -m "feat: Add feature description"

# Push
git push origin feature/feature-name
```

### Commit Format

```
<type>: <subject>

<body>
```

Types: `feat`, `fix`, `docs`, `style`, `refactor`, `test`, `chore`

### Pull Request Checklist

- [ ] Tests included
- [ ] Code follows style guidelines
- [ ] Documentation updated
- [ ] No hardcoded credentials
- [ ] Tests pass locally

---

## Getting Help

### Documentation Links

- [Spring Boot Docs](https://spring.io/projects/spring-boot)
- [Spring Data MongoDB](https://spring.io/projects/spring-data-mongodb)
- [Maven Docs](https://maven.apache.org/guides/)
- [OpenAPI/Swagger](https://swagger.io/)

### Common Questions

**Q: How do I use a different database?**
A: Update `spring.data.mongodb.database` in application.properties

**Q: How do I add SSL/HTTPS?**
A: Generate certificate and configure in application.properties

**Q: How do I use PostgreSQL instead of MongoDB?**
A: Add PostgreSQL dependency to pom.xml and update configuration

### Report Issues

Include in issue reports:
1. Java version: `java -version`
2. Maven version: `mvn -version`
3. MongoDB status
4. Full error message
5. Steps to reproduce

---

## Repository

**GitHub:** https://github.com/piyush-dev12619/DigitalBanking

---

**Version:** 0.0.1-SNAPSHOT | **Java:** 21 | **Spring Boot:** 4.1.1  
**Last Updated:** 2026-09-18

