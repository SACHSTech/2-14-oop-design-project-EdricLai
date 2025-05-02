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

    public Task getTaskByName(String name) {
        // return task by name
        for (Task task : tasks) {
            if (task.getName().equals(name)) {
                return task;
            }
        }
        // return null if no task found
        return null;
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