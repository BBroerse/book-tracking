# book-tracking
The Book Tracking System is a web application that allows users to search for books, track their reading status, and store book information efficiently. The application will be built using a modern web stack, ensuring scalability and maintainability.

## 1. Technology Stack
- Frontend: Nuxt.js (Vue 3, Composition API, Pinia for state management, Vue Router, TailwindCSS)
- Backend: Java Spring Boot (Spring Web, Spring Security, Spring Data JPA, Spring Cache)
- Database: PostgreSQL (with Liquibase for schema migrations)
- Caching: Redis (for caching frequent book searches and user sessions)
- Deployment: Docker (containerized development, testing, and production environments)
- External Data Sources: Google Books API, Open Library API

## 2. Core Features

### 2.1 Book Search
- Users can search for books via the frontend.
- The backend will first check the database for existing book records.
- If the book is not found, the backend fetches data from external sources, normalizes it, returns it to the frontend, and stores it in the database for future searches.

### 2.2 Book Status Management
- Users can mark books with the following statuses:
  - Planning to Read
  - Reading
  - Finished
  - Dropped
- Status changes will be stored in the database.

### 2.3 Fetch Books by Status
- Users can filter and fetch books based on their reading status.
- The backend will query the database and return books corresponding to the requested status.
