# aICE - 𝐀𝐈 𝐈𝐧𝐭𝐞𝐫𝐚𝐜𝐭𝐢𝐨𝐧 𝐂𝐨𝐧𝐭𝐫𝐨𝐥 𝐄𝐧𝐠𝐢𝐧𝐞 
# Backend Engineering Mega Project

Enterprise-grade Spring Boot microservice implementing Redis-powered virality guardrails, concurrency-safe atomic locks, smart notification batching, nested threaded comments, and real-time analytics.

---

# Project Overview

This project was built as a high-performance backend system designed to simulate a scalable AI-driven social platform architecture.

The system focuses on:

- Real-time virality scoring
- Redis atomic concurrency control
- Notification throttling and batching
- Bot interaction guardrails
- Nested recursive comment systems
- WebSocket live updates
- JWT authentication
- Stateless backend architecture

---

# Tech Stack

## Backend
- Java 17
- Spring Boot 3
- Spring Security
- Spring WebSocket
- Spring Data JPA
- Spring Scheduler

## Database
- PostgreSQL

## Distributed Cache / Concurrency
- Redis

## Frontend
- React.js
- React Router

## Containerization
- Docker
- Docker Compose

---

# System Architecture

```text
Client (React Frontend)
        │
        ▼
Spring Boot API Layer
        │
 ┌──────┴──────┐
 ▼             ▼
PostgreSQL     Redis
(Source of     (Guardrails,
Truth)         Counters,
                Atomic Locks,
                Cooldowns)
```

---

# Features Implemented

## Phase 1 - Virality Scoring System
- Dynamic virality score tracking
- Redis-backed real-time score updates
- Trending post retrieval
- Like and comment interaction APIs

---

## Phase 2 - Atomic Guardrails & Concurrency Safety
- Redis atomic locks
- Bot reply spam prevention
- Notification cooldown enforcement
- Concurrency-safe interaction control

### Thread Safety Approach for Atomic Locks

Thread safety was guaranteed using Redis atomic operations instead of in-memory Java synchronization.

The backend uses Redis because:
- Redis operations are atomic by design
- Atomic counters are safe across multiple threads
- Atomic counters are also safe across multiple distributed instances

The system specifically used:

```text
INCR
SETNX
EXPIRE
```

to guarantee race-condition-free execution.

### How Concurrency Safety Was Achieved

For bot interaction limits:

```text
post:{id}:bot_count
```

was incremented using Redis atomic increment operations.

Example logic:

```java
Long botCount =
    redisTemplate.opsForValue()
        .increment("post:" + postId + ":bot_count");
```

Because Redis executes commands sequentially internally, concurrent requests could not corrupt the counter value.

This prevented:
- duplicate writes
- lost updates
- race conditions
- inconsistent counters

### Cooldown Locking

Notification cooldowns were protected using Redis TTL-based locks.

Example:

```text
cooldown:user:{id}
```

The system checked lock existence before allowing notification dispatch.

If lock existed:
- notification was queued instead of sent

If lock expired:
- notification delivery resumed

This ensured:
- thread-safe throttling
- distributed-safe cooldowns
- prevention of notification flooding

### Why This Approach Is Production Safe

The Redis atomic locking strategy works safely even when:
- hundreds of concurrent requests arrive
- multiple backend instances are deployed
- load balancers distribute traffic
- application threads execute simultaneously

This is significantly safer than:
- synchronized blocks
- local JVM locks
- in-memory counters

because Redis acts as a centralized concurrency coordinator.

---

## Phase 3 - Smart Notification System
- Notification batching
- Cooldown-aware delivery
- Pending notification queue
- Real-time WebSocket notification updates

---

## Phase 4 - Nested Comment Depth System
- Recursive threaded comments
- Parent-child comment relationships
- Configurable maximum depth protection
- Depth validation APIs

---

## Phase 5 - Analytics & Monitoring
- Real-time analytics dashboard
- Live virality tracking
- WebSocket broadcasting
- Redis-backed monitoring metrics

---

# Security Features

- JWT Authentication
- Stateless session management
- Protected API routes
- Spring Security integration

---

# Docker Support

The backend system is fully containerized.

## Run Containers

```bash
docker compose up -d
```

## Active Services

- PostgreSQL
- Redis

---

# API Collection

Included Postman collection:

```text
aICE-postman-collection.json
```

Contains:
- Authentication APIs
- Post APIs
- Comment APIs
- Stress testing APIs
- Analytics APIs
- Notification APIs

---

# Frontend Dashboard

The React frontend provides:
- Live post feed
- Analytics dashboard
- Notification monitoring
- Real-time WebSocket updates
- System monitoring interface

---

# Project Highlights

- Enterprise backend architecture
- Redis concurrency engineering
- Distributed-safe atomic locking
- Real-time analytics
- WebSocket communication
- JWT security
- Dockerized infrastructure
- Nested recursive systems
- Scalable backend design

---

# Author

Pratik Pokhrel

B.Tech CSE  
Backend Engineering Assignment - aICE Platform
