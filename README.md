<h2 align="center">
Backend Challenge SELAZ
</h2>


- [Requirements](https://github.com/ManagerThalles/backend-java-spring-test/blob/main/requirements.md)

- [Challenge](https://github.com/ManagerThalles/backend-java-spring-test/blob/main/README.md)

# :rocket: Technologies used

- [H2](https://www.h2database.com/html/main.html)
- [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Maven](https://maven.apache.org/)
- [JUnit](https://junit.org/junit5/)

# How to run

Clone the repository

```bash
git clone https://github.com/mmjck/backend_challenge_selaz.git
```
⚠️  Start application into `ms/` directory

```bash
mvn spring-boot:run
```

Application will be running `http://localhost:8080/` ✅

## Rest Usage Documentation (Swagger)

The swagger documentation is available in the following URL: http://localhost:8080/swagger-ui.html


# Architecture

The project had a division that considering the separation of concerns and the possibility of changing the external world interface without changing the core of the system, 
it was influenced by the concept of the clean architecture. 


# Endpoint

The REST API app is described below.

## Auth module

### 1. Create new token

`POST /api/token`

```json
{
  "username": "maria123"
}
```

## User module

### 1. Create

`POST /api/users`

```json
{
  "username": "maria123",
   "nivel": "ADMIN" || "USER"
}
```

### 2. Update

`POST /api/users/{id}`

```json
{
  "username": "maria123",
  "nivel": "ADMIN" || "USER"
}
```

### 3. Get all

`GET /api/users/`

Response:

```json
{
  "total": 1,
  "data": [
    {
      "id": 3,
      "username": "maria123",
      "nivel": "USER"
    }
  ]
}
```

### 4. Delete

`DELETE /api/users/{id}`

Response: `200 OK`

### 5. Get all tasks by user

`GET /api/users/{id}/tasks`

Response:

```json
{
  "total": 1,
  "data": [
    {
      "id": 1,
      "title": "Configuration",
      "description": "Task todo",
      "status": "EM_ANDAMENTO",
      "due_date": "2024-12-24T08:47:04.941",
      "created_at": "2024-08-09T09:10:52.335769"
    }
  ]
}
```

## Task module

### 1. Create

`POST /api/task`

```json
{
  "title": "some title",
  "description": "some description",
  "due_date": "2024-08-07T07:59:34.352163"
}
```

### 2. Update

`PUT /api/task/1`

```json
{
  "title": "some title",
  "description": "some description",
  "due_date": "2024-08-07T07:59:34.352163",
  "status": "CONCLUIDA"
}
```

### 3. Get all by user Id and filter by status or dueDate

`GET /api/task/{userId}?status={status}&sort={dueDate}`

```json
{
  "total": 1,
  "data": [
    {
      "id": 1,
      "title": "Configuration",
      "description": "Task todo",
      "status": "EM_ANDAMENTO",
      "due_date": "2024-12-24T08:47:04.941",
      "created_at": "2024-08-09T09:10:52.335769"
    }
  ]
}
```

### 4. Delete

`DELETE /api/task/{id}`

Response: `200 OK`
