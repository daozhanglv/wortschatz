# WortSchatz

[![CI](https://github.com/daozhanglv/wortschatz/actions/workflows/ci.yml/badge.svg)](https://github.com/daozhanglv/wortschatz/actions/workflows/ci.yml)

WortSchatz turns German texts you actually read into a personal vocabulary trainer: an LLM extracts new words from an article, the backend tracks what you already know, and a spaced-repetition scheduler tells you what to review each day.

> **Status:** Work in progress — currently building v0.1 (LLM vocabulary extraction). See [ROADMAP.md](ROADMAP.md) for the step-by-step plan and progress.

## Tech Stack

- **Backend:** Java 21, Spring Boot (Web MVC, Data JPA, Validation, RestClient)
- **Database:** PostgreSQL 17 (Docker Compose)
- **Testing & CI:** JUnit 5, Testcontainers (PostgreSQL), GitHub Actions
- **LLM:** DeepSeek API (OpenAI-compatible)

## Running Locally

### Prerequisites

- Java 21
- Docker (for PostgreSQL and Testcontainers)

### Start the database and application

```bash
docker compose up -d
./mvnw spring-boot:run
```

For LLM calls, set your API key as an environment variable:

```bash
export DEEPSEEK_API_KEY=your-api-key
```

### Run tests

```bash
./mvnw test
```
