package studentPlanner.models;

// imports
import java.util.*;

/**
 * Course class
 * @author Edric
*/
public class Course {
    // fields
    private String code;
    private List<Task> tasks;

    // constructor
    public Course(String code) {
        this.code = code;
        this.tasks = new ArrayList<>();
    }

    // getters
    public String getCode() {
        return code;
    }
    public List<Task> getTasks() {
        return tasks;
    }

    // setters
    public void addTask(Task task) {
        tasks.add(task);
    }
    
    // toString
    public String toString() {
        return "Code: " + code;
    }
}