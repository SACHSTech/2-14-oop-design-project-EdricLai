import java.util.*;

/**
 * Course class
 * @author Edric
*/
public class Course {
    // fields
    private String code;
    private List<Task> tasks;

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
}