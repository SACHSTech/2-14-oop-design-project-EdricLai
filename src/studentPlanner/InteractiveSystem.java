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
            printMenu();
            int choice = getIntInput("Choose an option: ");
            // runs method based on choice
            switch (choice) {
                case 1 -> addCourse();
                case 2 -> addTask();
                case 3 -> viewTasks();
                case 4 -> viewFilteredTasks();
                case 5 -> prioritizeTasks();
                case 6 -> adjustTasks();
                case 7 -> markTaskCompletion();
                case 8 -> {
                    System.out.println("Exiting.");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Prints student planner menu
     * print statements
    */
    private void printMenu() {
        System.out.println("\nStudent Planner:");
        System.out.println("1. Add Course");
        System.out.println("2. Add Task");
        System.out.println("3. View Tasks");
        System.out.println("4. View Filtered Tasks");
        System.out.println("5. Prioritize Tasks");
        System.out.println("6. Adjust Task");
        System.out.println("7. Mark Task as Complete/Incomplete");
        System.out.println("8. Exit");
    }
    
    /**
     * Handles add course
     * user input
    */
    private void addCourse() {
        String code = getStringInput("Enter course code: ");
        manager.addCourse(new Course(code));
        System.out.println("Course added: " + code);
    }

    /**
     * Handles add task
     * user input
    */
    private void addTask() {
        // get course from user
        Course course = getCourseFromUser();
        if (course == null) return;

        // get task and its properties from user
        String taskName = getStringInput("Enter task name: ");
        LocalDateTime dueDate = LocalDateTime.parse(getStringInput("Enter due date (YYYY-MM-DD HH:MM): ").replace(" ", "T"));
        Priority priority = Priority.valueOf(getStringInput("Enter priority (LOW, MEDIUM, HIGH): ").toUpperCase());
        int taskType = getIntInput("Select task type (1. Homework, 2. Project, 3. Test): ");

        // creates task based on type
        Task task = switch (taskType) {
            case 1 -> new Homework(taskName, dueDate, priority, getIntInput("Enter total number of problems: "));
            case 2 -> new Project(taskName, dueDate, priority);
            case 3 -> new Test(taskName, dueDate, priority);
            default -> null;
        };

        // confirmation message
        if (task != null) {
            course.addTask(task);
            System.out.println("Task added: " + task.getName());
        }
    }

    /**
     * Prints all task from course
     * user input
    */
    private void viewTasks() {
        // get course from user
        Course course = getCourseFromUser();
        if (course == null) return;
        
        // prints all tasks in course
        System.out.println("\nTasks for Course: " + course.getCode());
        for (Task task : course.getTasks()) {
            System.out.println(task.getSummary());
        }
    }
    
    /**
     * Prints all filtered tasks from course
     * user input
    */
    private void viewFilteredTasks() {
        // get course from user
        Course course = getCourseFromUser();
        if (course == null) return;

        // get filters from user
        String dueDateStr = getStringInput("Enter date (YYYY-MM-DD HH:MM or press Enter to skip): ");
        LocalDateTime dueDate = dueDateStr.isEmpty() ? null : LocalDateTime.parse(dueDateStr.replace(" ", "T"));

        String priorityStr = getStringInput("Enter priority (LOW, MEDIUM, HIGH or press Enter to skip): ");
        Priority priority = priorityStr.isEmpty() ? null : Priority.valueOf(priorityStr.toUpperCase());

        String isCompletedStr = getStringInput("Enter completion status (true/false or press Enter to skip): ");
        boolean isCompleted = isCompletedStr.isEmpty() ? false : Boolean.parseBoolean(isCompletedStr);
        
        // print filtered tasks
        List<Task> filteredTasks = manager.filterTasks(course.getCode(), dueDate, priority, isCompleted);
        if (filteredTasks.isEmpty()) {
            System.out.println("No matching tasks found.");
        }
        else {
            System.out.println("\n" + filteredTasks.size() + " task(s) found:");
            for (Task task : filteredTasks) {
                System.out.println(task.getSummary());
            }
        }
    }

    /**
     * Prints automatically prioritized tasks
     * user input
    */
    private void prioritizeTasks() {
        // get course from user
        Course course = getCourseFromUser();
        if (course == null) return;

        // prints newly prioritized tasks
        List<Task> prioritizedTasks = manager.getNewPrioritizedTasks(course.getCode());
        System.out.println("\nPrioritized Tasks:");
        for (Task task : prioritizedTasks) {
            System.out.println(task.getSummary());
        }
    }

    /**
     * Handles adjust task
     * user input
    */
    private void adjustTasks() {
        // get course from user
        Course course = getCourseFromUser();
        if (course == null) return;

        // get task from user
        String taskName = getStringInput("Enter task name: ");
        Task task = course.getTaskByName(taskName);
        if (task == null) {
            System.out.println("Task not found!");
            return;
        }

        // special adjustments: homework
        if (task instanceof Homework homework) {
            homework.markSolved(getIntInput("Enter the number of problems solved: "));
        }
        // special adjustments: project
        else if (task instanceof Project project) {
            while (true) {
                String member = getStringInput("Enter group member name (or press Enter to exit): ");
                if (member.isEmpty()) break;
                int choice = getIntInput("1. add, 2. remove: ");

                if (choice == 1) project.addMember(member);
                else if (choice == 2) project.removeMember(member);
                else System.out.println("Invalid input.");
            }
        }
        // special adjustments: test
        else if (task instanceof Test test) {
            while (true) {
                String lesson = getStringInput("Enter lesson name (or press Enter to exit): ");
                if (lesson.isEmpty()) break;
                int choice = getIntInput("1. add, 2. remove: ");

                if (choice == 1) test.addLesson(lesson);
                else if (choice == 2) test.removeLesson(lesson);
                else System.out.println("Invalid input.");
            }
        }
    }

    /**
     * Handles marking task as complete
     * user input
    */
    private void markTaskCompletion() {
        // get course from user
        Course course = getCourseFromUser();
        if (course == null) return;

        // get task from user
        String taskName = getStringInput("Enter task name: ");
        Task task = course.getTaskByName(taskName);
        if (task == null) {
            System.out.println("Task not found!");
            return;
        }

        // marks task as complete/incomplete
        int choice = getIntInput("1. Mark as complete, 2. Mark as incomplete: ");
        if (choice == 1) {
            task.markComplete();
            System.out.println("Marked as complete.");
        }
        else if (choice == 2) {
            task.markIncomplete();
            System.out.println("Marked as incomplete.");
        }
        else {
            System.out.println("Invalid choice.");
        }
    }

    /**
     * Gets string input from user
     * helper method
     * @param prompt prompt message
     * @return string user input
    */
    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    /**
     * Gets int input from user
     * helper method
     * @param prompt prompt message
     * @return int user input
    */
    private int getIntInput(String prompt) {
        System.out.print(prompt);

        // ensures the user enters a valid integer
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. " + prompt);
            scanner.next();
        }

        // return inputted int
        int value = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return value;
    }

    /**
     * Gets course by inputted code from user
     * helper method
     * @return course by code
    */
    private Course getCourseFromUser() {
        // get user input
        String courseCode = getStringInput("Enter course code: ");
        Course course = manager.getCourseByCode(courseCode);

        // if course code does not exist
        if (course == null) {
            System.out.println("Course not found!");
        }

        return course;
    }
}