# BiteBook Review

A full-stack Restaurant Review Platform where users can discover restaurants, write reviews, upload photos, and search by location — built with Spring Boot, Elasticsearch, Next.js, and OAuth2.

---

## Tech Stack

| Layer | Technology |
|---|---|
| Backend Framework | Spring Boot 3.4, Java 21 |
| Search & Storage | Elasticsearch 8.12 |
| Authentication | OAuth2 / JWT via Keycloak |
| Mapping | MapStruct + Lombok |
| Frontend Framework | Next.js 14, TypeScript |
| UI Components | shadcn/ui, Tailwind CSS, Radix UI |
| Maps | Leaflet / OpenStreetMap |
| HTTP Client | Axios |
| Forms | React Hook Form + Zod |
| Containerisation | Docker Compose |

---

## Architecture

```
┌─────────────────────────────┐     ┌──────────────────────────────────┐
│   Frontend (Next.js 14)     │────▶│   Backend (Spring Boot 3.4)      │
│   TypeScript, Tailwind CSS  │     │   REST API on :8080              │
│   React Hook Form + Zod     │     │                                  │
│   Leaflet Maps               │     │  Controller → Service → Repo    │
└─────────────────────────────┘     │  MapStruct DTOs + Lombok         │
                                     │  OAuth2 Resource Server (JWT)    │
┌─────────────────────────────┐     └──────────────┬───────────────────┘
│   Keycloak (:9090)          │◀────────────────────┤
│   Identity Provider / OIDC  │                     │
└─────────────────────────────┘     ┌───────────────▼───────────────────┐
                                     │   Elasticsearch (:9200)           │
                                     │   Full-text restaurant search     │
                                     │   Geo-location queries            │
                                     └───────────────────────────────────┘
```

---

## API Endpoints

### Restaurants
| Method | Endpoint | Auth | Description |
|---|---|---|---|
| `GET` | `/api/restaurants` | Public | Search/list restaurants |
| `GET` | `/api/restaurants/{id}` | Public | Get restaurant details |
| `POST` | `/api/restaurants` | Required | Create restaurant |
| `PUT` | `/api/restaurants/{id}` | Required | Update restaurant |
| `DELETE` | `/api/restaurants/{id}` | Required | Delete restaurant |

### Reviews
| Method | Endpoint | Auth | Description |
|---|---|---|---|
| `POST` | `/api/restaurants/{id}/reviews` | Required | Submit a review |
| `PUT` | `/api/restaurants/{id}/reviews/{reviewId}` | Required | Update a review |
| `DELETE` | `/api/restaurants/{id}/reviews/{reviewId}` | Required | Delete a review |

### Photos
| Method | Endpoint | Auth | Description |
|---|---|---|---|
| `GET` | `/api/photos/{photoId}` | Public | Serve a photo |
| `POST` | `/api/restaurants/{id}/photos` | Required | Upload a photo |
| `DELETE` | `/api/restaurants/{id}/photos/{photoId}` | Required | Delete a photo |

---

## Running Locally

### Prerequisites
- Java 21+
- Maven 3.9+
- Node.js 18+
- Docker + Docker Compose

### Step 1 — Start infrastructure

```bash
cd backend
docker compose up -d
```

This starts Elasticsearch (`:9200`), Kibana (`:5601`), and Keycloak (`:9090`).

### Step 2 — Start the backend

```bash
cd backend
./mvnw spring-boot:run
```

API available at `http://localhost:8080`.

### Step 3 — Start the frontend

```bash
cd frontend
npm install
npm run dev
```

UI available at `http://localhost:3000`.

---

## Key Design Decisions

- **Elasticsearch** for full-text and geo-location restaurant search — enables fast, scalable queries beyond what a relational DB can offer
- **Keycloak + OAuth2 JWT** for stateless, token-based authentication — public read endpoints, authenticated writes
- **MapStruct + Lombok** for compile-time safe DTO mapping with zero boilerplate
- **Layered architecture** with clear separation of Controller, Service, Repository, and Domain
- **OpenStreetMap + Leaflet** for map integration — no API key required, open-source
- **React Hook Form + Zod** for type-safe form validation on the frontend

---

## Project Structure

```
bitebookreview/
├── backend/
│   ├── docker-compose.yaml          # Elasticsearch + Kibana + Keycloak
│   ├── pom.xml
│   └── src/main/java/com/bitebookreview/restaurant/
│       ├── BitebookreviewApplication.java
│       ├── config/SecurityConfig.java
│       ├── controllers/
│       │   ├── RestaurantController.java
│       │   ├── ReviewController.java
│       │   ├── PhotoController.java
│       │   └── ErrorController.java
│       ├── domain/
│       │   ├── entities/            # Restaurant, Review, Photo, User, Address...
│       │   └── dtos/                # Request/Response DTOs
│       ├── exceptions/              # Custom exception hierarchy
│       ├── mappers/                 # MapStruct mappers
│       ├── repositories/            # Elasticsearch repositories
│       └── services/                # Business logic + implementations
└── frontend/
    ├── app/                         # Next.js App Router pages
    ├── components/                  # Restaurant cards, search, maps, forms
    ├── domain/domain.ts             # TypeScript domain types
    ├── providers/                   # Auth + context providers
    └── services/api/                # Axios API service layer
```
