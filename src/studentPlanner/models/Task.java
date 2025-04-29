package studentPlanner.models;

// imports
import java.time.LocalDateTime;

/**
 * Task class
 * @author Edric
*/
public abstract class Task {
    // fields
    private String name;
    private LocalDateTime dueDate;
    private Priority priority;
    private boolean isCompleted;

    // constructor
    public Task(String name, LocalDateTime dueDate, Priority priority) {
        this.name = name;
        this.dueDate = dueDate;
        this.priority = priority;
        this.isCompleted = false;
    }

    // getters
    public String getName() {
        return name;
    }
    public LocalDateTime getDueDate() {
        return dueDate;
    }
    public Priority getPriority() {
        return priority;
    }
    public boolean isCompleted() {
        return isCompleted;
    }

    // setters
    public void setDueDate(LocalDateTime inDueDate) {
        dueDate = inDueDate;
    }
    public void setPriority(Priority inPriority) {
        priority = inPriority;
    }
    public void markComplete() {
        isCompleted = true;
    }
    public void markIncomplete() {
        isCompleted = false;
    }
    
    // helper methods
    public String getInfo() {
        return String.format(
            "%s\nDue: %-20s | Priority: %-7s | Completed: %-5s",
            name,
            dueDate,
            priority,
            isCompleted ? "Yes" : "No"
        );
    }

    // abstract methods
    public abstract String getSummary();
    
    // toString
    public String toString() {
        return getInfo();
    }
}