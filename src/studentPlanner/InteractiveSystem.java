package studentPlanner;

// imports
import java.util.*;
import java.time.LocalDateTime;

import studentPlanner.models.*;
import studentPlanner.models.task_types.*;

/**
 * Interactive user interface for studentPlanner
 * @author Edric
 */
public class InteractiveSystem {
    // fields
    private Manager manager;
    private Scanner scanner;

    // constructor
    public InteractiveSystem(Manager manager) {
        this.manager = manager;
        this.scanner = new Scanner(System.in);
    }
    
    // runs interactive system
    public void run() {
        // menu loop
        while (true) {
            // prints menu options
            System.out.println("\nStudent Planner:");
            System.out.println("1. Add Course");
            System.out.println("2. Add Task");
            System.out.println("3. View Tasks");
            System.out.println("4. View Filtered Tasks");
            System.out.println("5. Prioritize Tasks");
            System.out.println("6. Adjust tasks");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            // runs method based on choice
            switch (choice) {
                case 1:
                    addCourse();
                    break;
                case 2:
                    addTask();
                    break;
                case 3:
                    viewTasks();
                    break;
                case 4:
                    viewFilteredTasks();
                    break;
                case 5:
                    prioritizeTasks();
                    break;
                case 6:
                    adjustTasks();
                    break;
                case 7:
                    System.out.println("Exiting.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // addCourse method
    private void addCourse() {
        // get user input
        System.out.print("Enter course code: ");
        String code = scanner.nextLine();
        Course course = new Course(code);
        // adds course
        manager.addCourse(course);
        // confirmation message
        System.out.println("Course added: " + course);
    }

    // addTask method
    private void addTask() {
        // get user input
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        Course course = manager.getCourseByCode(courseCode);

        // if course code does not exist
        if (course == null) {
            System.out.println("Course not found!");
            return;
        }

        // get user inputs
        System.out.print("Enter task name: ");
        String taskName = scanner.nextLine();

        System.out.print("Enter due date (YYYY-MM-DD HH:MM): ");
        String dueDateStr = scanner.nextLine();
        LocalDateTime dueDate = LocalDateTime.parse(dueDateStr.replace(" ", "T"));
        
        System.out.print("Enter priority (LOW, MEDIUM, HIGH): ");
        String priorityStr = scanner.nextLine();
        Priority priority = Priority.valueOf(priorityStr.toUpperCase());

        System.out.print("Select task type (1. Homework, 2. Project, 3. Test): ");
        int taskType = scanner.nextInt();
        scanner.nextLine(); // consume newline

        // creates task based on type
        Task task = null;
        if (taskType == 1) {
            System.out.print("Enter total number of problems: ");
            int problemCount = scanner.nextInt();
            task = new Homework(taskName, dueDate, priority, problemCount);
        }
        else if (taskType == 2) {
            task = new Project(taskName, dueDate, priority);
        }
        else if (taskType == 3) {
            task = new Test(taskName, dueDate, priority);
        }

        if (task != null) {
            course.addTask(task);
            System.out.println("Task added: " + task.getName());
        }
    }

    // viewTasks method
    private void viewTasks() {
        // get user input
        System.out.print("Enter course code to view all tasks: ");
        String courseCode = scanner.nextLine();
        Course course = manager.getCourseByCode(courseCode);

        // if course code does not exist
        if (course == null) {
            System.out.println("Course not found!");
            return;
        }

        // prints all tasks in course
        System.out.println("\nTasks for Course: " + course.getCode());
        for (Task task : course.getTasks()) {
            System.out.println(task.getSummary());
        }
    }

    // viewFilteredTasks method
    private void viewFilteredTasks() {
        // get user input
        System.out.print("Enter course code to filter tasks: ");
        String courseCode = scanner.nextLine();
        Course course = manager.getCourseByCode(courseCode);

        // if course code does not exist
        if (course == null) {
            System.out.println("Course not found!");
            return;
        }

        // get user inputs
        System.out.print("Enter date (YYYY-MM-DD HH:MM or press Enter to skip): ");
        String dueDateStr = scanner.nextLine();
        LocalDateTime dueDate = dueDateStr.isEmpty() ? null : LocalDateTime.parse(dueDateStr.replace(" ", "T"));

        System.out.print("Enter priority (LOW, MEDIUM, HIGH or press Enter to skip): ");
        String priorityStr = scanner.nextLine();
        Priority priority = priorityStr.isEmpty() ? null : Priority.valueOf(priorityStr.toUpperCase());

        System.out.print("Enter completion status (true/false or press Enter to skip): ");
        String isCompletedStr = scanner.nextLine();
        boolean isCompleted = isCompletedStr.isEmpty() ? false : Boolean.parseBoolean(isCompletedStr);

        // get filtered tasks
        List<Task> filteredTasks = manager.filterTasks(courseCode, dueDate, priority, isCompleted);

        // confirmation message
        if (filteredTasks.isEmpty()) {
            System.out.println("No matching tasks found.");
        }
        else {
            System.out.println(filteredTasks.size() + " task(s) found matching the criteria:");
            for (Task task : filteredTasks) {
                System.out.println(task.getSummary());
            }
        }
    }

    // prioritizeTasks method
    private void prioritizeTasks() {
        // get user input
        System.out.print("Enter course code to prioritize tasks: ");
        String courseCode = scanner.nextLine();
        Course course = manager.getCourseByCode(courseCode);

        // if course code does not exist
        if (course == null) {
            System.out.println("Course not found!");
            return;
        }

        // prioritizes tasks
        System.out.println("\nPrioritizing tasks...");
        List<Task> prioritizedTasks = manager.getNewPrioritizedTasks(courseCode);

        // prints newly prioritized tasks
        System.out.println("\nPrioritized Tasks:");
        for (Task task : prioritizedTasks) {
            System.out.println(task.getSummary());
        }
    }
    
    // adjustTasks method
    private void adjustTasks() {
        // code
    }
}