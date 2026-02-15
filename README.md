# -Portal-

# Multi-Tenant Authentication & Authorization Service

A RESTful authentication and authorization service designed for corporate environments with multi-tenant support and Role-Based Access Control (RBAC).

This system enables centralized management of users and companies while ensuring logical data isolation per company and fine-grained permission control.

---

## Overview

This project provides:

- Token-based authentication (JWT)
- Session management with refresh token support
- Multi-tenant architecture (users can belong to multiple companies)
- Different roles per company
- Global Super Administrator with full system control
- Role-Based Access Control (RBAC)
- Context-aware authorization per company

---

## Architecture

The system is organized into four main domains:

### Users
- User registration
- Update and logical exclusion (desactivate)
- Mandatory association with one or more companies
- Support for multiple roles across different companies

### Companies
- Company registration and maintenance
- Logical data isolation per company

### Roles
- Permission control per company
- Example roles:
  - `SUPER_ADMIN`
  - `COMPANY_ADMIN`
  - `TEAM_LEADER`
  - `USER`

### Authentication
- Email/password login
- Access Token (JWT) generation
- Refresh Token stored in the database
- Session tracking and revocation support

---

## Authorization Model

The system operates at two permission levels:

### Global Level

Users with the `SUPER_ADMIN` role have full control over:
- All companies
- All users
- All roles
- System-wide configuration

### Company Level

Permissions are defined per company:

- A user can be `COMPANY_ADMIN` in Company A
- The same user can be `USER` in Company B

Authorization is always validated against the active company selected in the session.

---

## Authentication Flow

1. User logs in with email and password  
2. System validates credentials  
3. Access token is issued  
4. If the user belongs to multiple companies, they select the active company  
5. A contextualized token is generated containing:
   - User ID
   - Active company ID
   - Role within that company
   - Expiration timestamp  

---

## Data Model (Summary)

### Tables

- `user`
- `companie`
- `role`
- `user_company` (many-to-many relationship with role assignment)
- `user_session` (session control and refresh tokens)

### Relationship Model

- A user must belong to at least one company
- A user can belong to multiple companies
- A user can have different roles in different companies

---

## Security Features

- Secure password hashing (bcrypt or argon2)
- Short-lived access tokens
- Refresh tokens stored and validated server-side
- Session revocation capability
- Middleware-based authorization enforcement
- Designed for HTTPS-only environments
- Isolation of data per company (multi-tenant safety)

---

##  Example REST Endpoints

### Authentication

- POST /auth/login
- POST /auth/refresh
- POST /auth/logout
- POST /auth/select-company

### Company Management

- GET /companies
- POST /companie
- PUT /companie/{id}
- DELETE /companie/{id}

### User Management (Company Context)

GET /companie/{companyId}/users
POST /companie/{companyId}/user
PUT /companie/{companyId}/user/{userId}
DELETE /companie/{companyId}/user/{userId}

---

## Use Cases

- Multi-company corporate portals
- B2B SaaS platforms
- Enterprise administrative systems
- Systems requiring strict role separation and tenant isolation

---

## Project Goal

To provide a secure, scalable, and flexible foundation for authentication and authorization in enterprise-grade systems, ensuring:

- Strong security practices
- Scalability for SaaS environments
- Clear separation of concerns
- Fine-grained access control
- Multi-tenant readiness
