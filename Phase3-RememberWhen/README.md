# Remember When — AI-Powered Millennial Nostalgia App

**INFO 5100 Application Engineering & Development — Summer 2026**
**Northeastern University | Prakruti Jain**

## Overview
Remember When retrieves era-specific cultural data (movies, music, toys, candy) based on a
user's birth year and country, then (in future phases) generates a personalized nostalgia
narrative using an LLM agentic pipeline.

This repository contains the Phase 3 preliminary implementation: a working proof-of-concept
demonstrating the core retrieval pipeline, authentication, and a connected frontend.

## Tech Stack
- **Backend:** Java 21, Spring Boot 3.3, Spring Security, Spring Data JPA
- **Database:** PostgreSQL 16
- **Frontend:** React 18
- **External API:** TMDB (The Movie Database)

## What's Built (Phase 3 — Preliminary Proof of Concept)

- **Backend**: Java Spring Boot 3.x REST API
  - `com.rememberwhen.app.model` — `User`, `EraItem` data models
  - `com.rememberwhen.app.repository` — `UserRepository` (Spring Data JPA)
  - `com.rememberwhen.app.service` — `AuthService` (signup/login, BCrypt password hashing), `EraService` (TMDB API integration)
  - `com.rememberwhen.app.controller` — `AuthController` (`/api/auth/signup`, `/api/auth/login`), `EraController` (`/api/era`)
  - `SecurityConfig` — Spring Security configuration
- **Database**: PostgreSQL (`rememberwhen_db`) — schema in `database/schema.sql`
- **Frontend**: React 18 — signup/login form → birth year/country search → movie poster results grid
- **Working integration**: TMDB API — retrieves real movies with posters, filtered by release year

## What's Planned (Future Phases)

- Discogs API integration for music
- Wikidata SPARQL integration for toys/brands
- Open Food Facts API integration for candy/food
- LLM-generated personalized narrative (Claude/GPT-4o) — the "reason → narrate" steps of the agentic pipeline
- Shareable nostalgia card output
- `era_cache` table utilization to reduce repeat API calls

## How to Run

### Backend
```bash
cd Phase3-RememberWhen
mvn spring-boot:run
```
Runs on `http://localhost:8080`. Requires PostgreSQL running locally with a database named
`rememberwhen_db` (see `database/schema.sql`), and a TMDB API key set in
`src/main/resources/application.properties` as `tmdb.api.key`.

### Frontend
```bash
cd Phase3-RememberWhen/frontend
npm start
```
Runs on `http://localhost:3000`.

## API Endpoints
- `POST /api/auth/signup` — create account
- `POST /api/auth/login` — authenticate
- `GET /api/era?year={year}&country={country}` — fetch era-matched movies