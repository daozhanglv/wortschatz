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
- [x] **0.6** Input validation and a global error handler with consistent error responses
- [x] **0.7** `Article` entity: save pasted German text; store the example sentence each word came from
- [x] **0.8** First integration test with Testcontainers (a real PostgreSQL in the test)
- [x] **0.22** GitHub Actions CI: build and run all tests on every push

### LLM extraction

- [x] Choose 3–5 German texts I have read. For each one, mark the words I want to learn and the words I already know.
  Reserve one text for the final evaluation in 0.13 and use the others to tune the prompt.
- [x] **0.9** LLM client: call the DeepSeek API (OpenAI-compatible) with Spring's `RestClient`; API key from an
  environment variable
- [ ] **0.10a** Refactor `DeepSeekClient` to use typed request/response DTOs and externalized config (`model`, `baseUrl`).
  Pass a sentence from either prompt-tuning text, ask for a word worth learning, and return the assistant's reply text.
  Try a second sentence without editing the client.
- [ ] **0.10b** Design a JSON format for candidate words, including a canonical deduplication rule (how nouns, articles,
  gender, part of speech, and multiword expressions are normalized). Use fixed JSON samples to test that Java parses
  valid candidates into DTOs and reports an error for malformed replies. These tests run without an API key.
- [ ] **0.10c** Write an extraction prompt requesting that JSON format (using JSON mode) and verbatim original sentences
  from the text, with a sensible input length limit. Run it on one prompt-tuning article, parse the reply, and compare
  it with my annotations.
- [ ] **0.11a** Let the learner choose “Learn” or “Already known” for a candidate and save the choice, plus basic word
  corrections (edit meaning, flip Learn/Known, delete). Test both outcomes: marking *der Haushalt* as known skips it
  in later suggestions; choosing to learn *das Mietrecht* adds it to the learning list.
- [ ] **0.18** Database migrations with Flyway (replace `ddl-auto`). Set up the baseline migration before refactoring
  the schema in 0.11b, and verify that existing data survives migrations cleanly.
- [ ] **0.11b** Move example sentences into a dedicated table linked to both `Word` and `Article` (with a Flyway
  migration and a uniqueness constraint to prevent duplicate examples). Test with *das Mietrecht* in two articles:
  saving both examples should yield one word and two examples, each linked to its source article.
- [ ] **0.11c** Connect extraction to the saved vocabulary. Show new candidates for a decision, skip known
  words, and attach new examples to words already being learned. Process the first article from 0.11b
  again with the same extraction result and verify that there is still one word with two examples.
- [ ] **0.12** Make LLM calls robust: configurable timeouts, retry with backoff on transient failures, and structured
  logging of latency and token usage for every call.
- [ ] **0.13** Run an automated evaluation against the annotations, including the reserved text after prompt tuning.
  Report target-word recall, known-word false positives, and extra words for manual review. Record the results in the README.

### Review

- [ ] **0.14** Review state per word (ease factor, interval, due date); implement SM-2 with an explicit mapping from
  `again / hard / good / easy` and clear `LocalDate` day-boundary rules, covered by unit tests.
- [ ] **0.15** Review API: query PostgreSQL by due date for today's words, submit an answer (`again / hard / good / easy`),
  and update review state transactionally with integration tests.
- [ ] **0.16a** Minimal web UI setup (lightweight stack so backend stays the focus) and word list view (inspect, edit,
  toggle Learn/Known, delete).
- [ ] **0.16b** Web UI: paste an article, trigger extraction, and choose “Learn” or “Already known” for its candidates.
- [ ] **0.16c** Web UI: daily review flow for due words (show prompt/context, reveal meaning, submit rating).
- [ ] **0.17** README in English: problem, features, architecture diagram, key design trade-offs, evaluation results,
  and how to run.

**v0.1 completion check:** Use WortSchatz for seven consecutive days to read my own German texts and review
any words that are due. Record one difficulty I encountered and the change I made in response.

---

## v0.2 — Engineering quality

**Goal:** a project that runs with one command and is checked automatically.

- [ ] **0.19** Optional: if logs show repeated equivalent LLM requests, cache results in Redis using a key that includes
  the input context.
- [ ] **0.20** Optional: if the PostgreSQL due-date query becomes a measured bottleneck, evaluate a Redis review queue.
- [ ] **0.21** Multi-stage `Dockerfile` for the app; full `compose.yaml` (app + PostgreSQL, plus Redis if used) —
  `docker compose up` runs everything.
- [ ] **0.23** API documentation with OpenAPI and Swagger UI, so the endpoints can be tried in the browser.
- [ ] **0.24** Health check and basic metrics with Spring Boot Actuator.
- [ ] **0.25** Cloze exercise: review a word using its original sentence with the word blanked out.

---

## v0.3 — Showcase

**Goal:** a version anyone can open in the browser, and that I can show in applications and interviews.

- [ ] **0.26a** User accounts and authentication (Spring Security).
- [ ] **0.26b** Multi-user data isolation: scope articles, word status, examples, and review states per user, with
  authorization tests preventing cross-user access.
- [ ] **0.27** Daily limit on LLM extraction per user (protects the API budget once the app is public).
- [ ] **0.28** Learning statistics (words learned, review accuracy, hardest words).
- [ ] **0.29** Deploy to a cloud server with HTTPS and automated PostgreSQL backups.
- [ ] **0.30** Continuous deployment: GitHub Actions deploys every push to `main` after the tests pass.
- [ ] **0.31** Screenshots / demo GIF, live demo link (with a seeded demo account or sample article so visitors can try
  it immediately), and a short German introduction in the README.
- [ ] **0.32** Optional: run the same setup on a local Kubernetes cluster with kind.

---

## Development notes

- Built step by step; every step is one focused commit with an English commit message.
- AI tools are used as a tutor and reviewer. Design decisions and the final code are my own,
  and I can explain every part of it.
