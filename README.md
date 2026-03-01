# git-portfolio-vlad-konrad

A console-based **To-Do List application** built with Java 17 and Maven,
created as a portfolio project to demonstrate Git fundamentals, branching
workflows, conflict resolution, and build automation.

---

## Project Description

The application lets you manage to-do items from the terminal. You can add
tasks, mark them as completed, delete them, and filter by status. The
project is intentionally simple so that the focus stays on **Git workflow
and Maven configuration** rather than application complexity.

### Java files (6)
| File | Role |
|------|------|
| `Main.java` | Entry point – reads commands from stdin |
| `TodoItem.java` | Immutable data class for a single to-do |
| `TodoStatus.java` | Enum: `PENDING` / `COMPLETED` |
| `TodoRepository.java` | Interface defining CRUD contract |
| `InMemoryTodoRepository.java` | In-memory implementation of the repository |
| `TodoService.java` | Business-logic layer (validation, delegation) |

---

## How to Build and Run

### Prerequisites
- Java 17+
- Apache Maven 3.8+

### Build
```bash
mvn clean install
```
The JAR is created at `json-validator/target/json-validator-1.0-SNAPSHOT.jar`.

### Run tests
```bash
mvn test
```

### Run the application
```bash
# Linux / macOS
mvn compile exec:java -Dexec.mainClass=com.vladkonrad.Main

# Windows PowerShell (quotes required for -D flag)
mvn compile exec:java "-Dexec.mainClass=com.vladkonrad.Main"
```

### Available commands
| Command | Description |
|---------|-------------|
| `add <title>` | Create a new to-do |
| `done <id>` | Mark as completed |
| `delete <id>` | Remove a to-do |
| `list` | Show all to-dos |
| `list pending` | Show only pending |
| `list done` | Show only completed |
| `help` | Print command reference |
| `exit` | Quit the app |

---

## Git Concepts Demonstrated

| Concept | Where |
|---------|-------|
| Feature branches | `feature/maven-config`, `feature/validator-core`, `feature/filtering` |
| Bugfix branches | `bugfix/input-validation` |
| Three-way merge | `feature/filtering` merged into `main` after an independent commit on `main` |
| Merge conflict + resolution | `bugfix/input-validation` vs `main` (see Conflict Resolution below) |
| Meaningful commit messages | Imperative mood throughout the history |
| Logical commit grouping | One feature / fix per commit |

---

## Build Tool: Maven

**Why Maven?**
Maven was chosen because it has a well-established convention-over-configuration
approach (`src/main/java`, `src/test/java`) that mirrors real-world Java
projects. The declarative `pom.xml` makes dependency management and version
pinning easy to read and review. The `exec-maven-plugin` also allows running
the app without packaging a fat JAR.

### Key `pom.xml` features
- `<maven.compiler.release>17</maven.compiler.release>` – locks Java version
- Version properties: `<gson.version>`, `<commons.lang3.version>`, `<junit.version>`
- Dependencies: **Gson 2.10.1** (compile), **Apache Commons Lang 3.14.0** (compile), **JUnit Jupiter 5.11.0** (test scope)
- `exec-maven-plugin` for `mvn exec:java`

---

## Conflict Resolution

### Which files conflicted
`src/main/java/com/vladkonrad/TodoService.java` – the `addTodo` validation
error message was edited independently on both `main` and `bugfix/input-validation`.

### How it happened
1. Branch `bugfix/input-validation` was created from an earlier commit.
2. A commit was made on `main` that changed the wording of the blank-title error.
3. The bugfix branch **also** changed the same line with different wording.
4. Merging `bugfix/input-validation` into `main` produced a conflict on that line.

### How it was resolved
The conflict was resolved by **combining both changes**: the whitespace-trimming
logic from the bugfix branch was kept, and the clearer error message wording
from `main` was retained. Neither side was simply accepted wholesale — the
result is a blend of both contributions.

Resolved commit: `9fa6260 – Resolve conflict: combine wording from main and bugfix/input-validation`

---

## Screenshots

| Screenshot | Description |
|------------|-------------|
| [git-history.png](screenshots/git-history.png) | `git log --oneline --all --graph` output |
| [build-success.png](screenshots/build-success.png) | Successful `mvn clean install` output |
| [tests-passing.png](screenshots/tests-passing.png) | All JUnit 5 tests passing |
