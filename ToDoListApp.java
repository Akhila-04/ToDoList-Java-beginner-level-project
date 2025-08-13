import java.io.*;
import java.util.*;

public class ToDoListApp {
    static ArrayList<String> tasks = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static final String FILE_NAME = "tasks.txt";

    public static void main(String[] args) {
        loadTasks();
        int choice;
        do {
            System.out.println("\n📝 To-Do List");
            System.out.println("1. View Tasks");
            System.out.println("2. Add Task");
            System.out.println("3. Remove Task");
            System.out.println("4. Save & Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> viewTasks();
                case 2 -> addTask();
                case 3 -> removeTask();
                case 4 -> saveTasks();
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 4);
    }

    static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found!");
            return;
        }
        System.out.println("\nYour Tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    static void addTask() {
        System.out.print("Enter new task: ");
        String task = sc.nextLine();
        tasks.add(task);
        System.out.println("✅ Task added!");
    }

    static void removeTask() {
        viewTasks();
        if (tasks.isEmpty()) return;

        System.out.print("Enter task number to remove: ");
        int index = sc.nextInt() - 1;
        if (index >= 0 && index < tasks.size()) {
            System.out.println("🗑 Removed: " + tasks.remove(index));
        } else {
            System.out.println("Invalid number!");
        }
    }

    static void saveTasks() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String task : tasks) {
                writer.write(task);
                writer.newLine();
            }
            System.out.println("💾 Tasks saved. Goodbye!");
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    static void loadTasks() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(line);
            }
            if (!tasks.isEmpty()) {
                System.out.println("📂 Loaded " + tasks.size() + " tasks from file.");
            }
        } catch (IOException e) {
            System.out.println("No saved tasks found. Starting fresh.");
        }
    }
}