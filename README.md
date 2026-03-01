# Git & Build Tools Portfolio: JSON Validator To-Do Utility

This repository contains a Java 17 console to-do validator with Maven build automation.
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
