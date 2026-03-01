# Usage Guide

## Running the Application

```bash
mvn exec:java
```

## Build Lifecycle Commands

```bash
mvn clean install
mvn test
```

## Available Commands

| Command         | Description                   |
|-----------------|-------------------------------|
| `add <title>`   | Add a new to-do item          |
| `done <id>`     | Mark an item as completed     |
| `delete <id>`   | Remove an item by ID          |
| `list`          | Show all to-do items          |
| `help`          | Show available commands        |
| `exit`          | Quit the application          |

## Example Session

```
=== To-Do List App ===
> add Buy groceries
Added: [1] Buy groceries (PENDING)
> add Write unit tests
Added: [2] Write unit tests (PENDING)
> done 1
Marked as completed.
> list
[1] Buy groceries (COMPLETED)
[2] Write unit tests (PENDING)
> delete 2
Deleted.
> exit
Goodbye!
```
