# WortSchatz Roadmap

WortSchatz turns German texts you actually read into a personal vocabulary trainer:
an LLM extracts new words from an article, the backend keeps track of what you already know,
and a spaced-repetition scheduler tells you what to review each day.

This file tracks the plan. Each step is small (about 30–60 minutes) and ends with one commit.

---

## v0.1 — Core features

**Goal:** a version I can use every day for my own German studies.

### Data foundation

- [x] **0.1** Project setup with Spring Boot and a `/hello` endpoint
- [x] **0.2** Run PostgreSQL locally with Docker Compose (`compose.yaml`) and connect to it from IntelliJ
- [x] **0.3** Add Spring Data JPA and the PostgreSQL driver; configure the datasource
- [x] **0.4** `Word` entity and repository (lemma, part of speech, gender, plural, meaning, created at)
- [x] **0.5** REST API to create and list words (controller → service → repository, request/response DTOs)
- [ ] **0.6** Input validation and a global error handler with consistent error responses
- [ ] **0.7** `Article` entity: save pasted German text; store the example sentence each word came from
- [ ] **0.8** First integration test with Testcontainers (a real PostgreSQL in the test)

### LLM extraction

- [ ] **0.9** LLM client: call the DeepSeek API (OpenAI-compatible) with Spring's `RestClient`; API key from an
  environment variable
- [ ] **0.10** Prompt and JSON response format for word extraction; parse into DTOs; handle invalid responses
- [ ] **0.11** Extraction pipeline: article → extracted words → skip words already known → save new ones
- [ ] **0.12** Make LLM calls robust: timeout, retry on temporary failures, and log token usage for every call
- [ ] **0.13** Extraction evaluation: a small set of articles with the words I expect to be extracted; a test
  reports how many were found and how many extra words came back; record the result in the README

### Review

- [ ] **0.14** Review state per word (ease factor, interval, due date); implement SM-2 with unit tests
- [ ] **0.15** Review API: get today's due words, submit an answer (again / hard / good / easy)
- [ ] **0.16** Minimal web UI: add article, word list, review session
- [ ] **0.17** README in English: problem, features, architecture diagram, how to run

---

## v0.2 — Engineering quality

**Goal:** a project that runs with one command and is checked automatically.

- [ ] **0.18** Database migrations with Flyway (replace `ddl-auto`)
- [ ] **0.19** Redis: cache LLM results per word to save API calls
- [ ] **0.20** Redis: daily review queue
- [ ] **0.21** `Dockerfile` for the app; full `compose.yaml` (app + PostgreSQL + Redis) — `docker compose up` runs
  everything
- [ ] **0.22** GitHub Actions CI: build and run all tests on every push
- [ ] **0.23** API documentation with OpenAPI and Swagger UI, so the endpoints can be tried in the browser
- [ ] **0.24** Health check and basic metrics with Spring Boot Actuator
- [ ] **0.25** Cloze exercise: review a word using its original sentence with the word blanked out

---

## v0.3 — Showcase

**Goal:** a version anyone can open in the browser, and that I can show in applications and interviews.

- [ ] **0.26** User accounts and login (Spring Security)
- [ ] **0.27** Daily limit on LLM extraction per user (protects the API budget once the app is public)
- [ ] **0.28** Learning statistics (words learned, review accuracy, hardest words)
- [ ] **0.29** Deploy to a cloud server with HTTPS
- [ ] **0.30** Continuous deployment: GitHub Actions deploys every push to `main` after the tests pass
- [ ] **0.31** Screenshots / demo GIF, a link to the live demo, and a short German introduction in the README
- [ ] **0.32** Optional: run the same setup on a local Kubernetes cluster with kind

---

## Development notes

- Built step by step; every step is one focused commit with an English commit message.
- AI tools are used as a tutor and reviewer. Design decisions and the final code are my own,
  and I can explain every part of it.
