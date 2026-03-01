package com.vladkonrad;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        InMemoryTodoRepository repository = new InMemoryTodoRepository();
        TodoService service = new TodoService(repository);
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== To-Do List App ===");
        printHelp();

        while (true) {
            System.out.print("\n> ");
            String line = scanner.nextLine().trim();

            if (line.startsWith("add ")) {
                String title = line.substring(4).trim();
                TodoItem item = service.addTodo(title);
                System.out.println("Added: " + item);

            } else if (line.startsWith("done ")) {
                int id = Integer.parseInt(line.substring(5).trim());
                boolean ok = service.completeTodo(id);
                System.out.println(ok ? "Marked as completed." : "ID not found.");

            } else if (line.startsWith("delete ")) {
                int id = Integer.parseInt(line.substring(7).trim());
                boolean ok = service.deleteTodo(id);
                System.out.println(ok ? "Deleted." : "ID not found.");

            } else if (line.equals("list")) {
                List<TodoItem> items = service.listAll();
                if (items.isEmpty()) {
                    System.out.println("No to-dos yet.");
                } else {
                    items.forEach(System.out::println);
                }

            } else if (line.equals("list pending")) {
                List<TodoItem> items = service.listByStatus(TodoStatus.PENDING);
                if (items.isEmpty()) {
                    System.out.println("No pending to-dos.");
                } else {
                    items.forEach(System.out::println);
                }

            } else if (line.equals("list done")) {
                List<TodoItem> items = service.listByStatus(TodoStatus.COMPLETED);
                if (items.isEmpty()) {
                    System.out.println("No completed to-dos.");
                } else {
                    items.forEach(System.out::println);
                }

            } else if (line.equals("help")) {
                printHelp();

            } else if (line.equals("exit")) {
                System.out.println("Goodbye!");
                break;

            } else {
                System.out.println("Unknown command. Type 'help' for usage.");
            }
        }

        scanner.close();
    }

    private static void printHelp() {
        System.out.println("Commands:");
        System.out.println("  add <title>    - Add a new to-do");
        System.out.println("  done <id>      - Mark a to-do as completed");
        System.out.println("  delete <id>    - Delete a to-do");
        System.out.println("  list           - List all to-dos");
        System.out.println("  list pending   - List only pending to-dos");
        System.out.println("  list done      - List only completed to-dos");
        System.out.println("  help           - Show this help");
        System.out.println("  exit           - Quit the app");
    }
}
