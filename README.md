# Git & Build Tools Portfolio: JSON Validator To-Do Utility

This repository contains a Java 17 command-line to-do validator application with Maven build automation.
It demonstrates practical Git workflows (feature branches, merge commits, conflict resolution)
and a maintainable Maven project setup.

## Project Description

The application is a lightweight in-memory to-do manager with commands to add, complete,
delete, and list tasks by status.

## Tech Stack

- Java 17
- Maven
- Gson
- Apache Commons Lang
- JUnit 5

## Why Maven

Maven was chosen for predictable lifecycle phases (`clean`, `test`, `install`), simple dependency
management in one `pom.xml`, and straightforward reproducible command-line builds.

## Build And Run

Build and package:

```bash
mvn clean install
```

Run tests:

```bash
mvn test
```

Run app:

```bash
mvn exec:java
```

## Git Concepts Demonstrated

- Feature branches with isolated work
- Bugfix branch workflow
- Merge commits in `main`
- Intentional merge conflict and manual resolution
- Clean, incremental commit history

## Conflict Resolution Notes

- Conflicted file: `README.md`
- Scenario: both `main` and `feature/conflict-demo` changed the same project-summary line.
- Resolution: combined both edits into one final sentence:
  `Java 17 command-line to-do validator application with Maven build automation.`

## Evidence Files

Assignment artifacts are stored in `screenshots/`:

- `git-history.png`, `build-success.png`, `tests-passing.png`
- command logs: `git-history.txt`, `build-success.txt`, `tests-passing.txt`
