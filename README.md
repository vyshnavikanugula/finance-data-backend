# Finance Data Processing and Access Control Backend

## Overview

This project is a backend system for managing financial records with role based access control. It allows users to interact with financial data based on their assigned roles such as Admin and Viewer.

## Features

* Role Based Access Control (Admin / Viewer)
* Create Financial Records
* View Financial Records
* Update Financial Records
* Delete Financial Records
* Filter by Category
* Filter by Date
* Input Validation
* Error Handling

## Tech Stack

* Java
* Spring Boot
* Spring Data JPA
* MySQL
* REST APIs

## User Roles

### Admin

* Create records
* Update records
* Delete records
* View all records

### Viewer

* Limited access
* Cannot modify records

## API Endpoints

### Create Record

POST /records

### Get All Records

GET /records

### Get Record by ID

GET /records/{id}

### Update Record

PUT /records/{id}

### Delete Record

DELETE /records/{id}

### Filter by Category

GET /records/category/{category}

### Filter by Date

GET /records/date/{date}

## How to Run

1. Clone repository
2. Open project in IDE
3. Configure MySQL database
4. Run Spring Boot application
5. Test APIs using Postman

## Header for Admin Access

X-ROLE : ADMIN

## Author

Vyshnavi
