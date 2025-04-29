package studentPlanner;

// imports
import java.util.*;
import java.time.LocalDateTime;

import studentPlanner.models.*;

/**
 * studentPlanner manager
 * @author Edric
*/
public class Manager {
    // fields
    private List<Course> courses;

    // constructor
    public Manager() {
        courses = new ArrayList<>();
    }

    // setters
    public void addCourse(Course course) {
        courses.add(course);
    }

    /**
     * Gets course by code
     * @param code code of the course
    */
    public Course getCourseByCode(String code) {
        // return course by code
        for (Course course : courses) {
            if (course.getCode().equals(code)) {
                return course;
            }
        }
        // return null if no course found
        return null;
    }
    
    /**
     * Filters tasks by date, priority, or isCompleted
     * Any parameter can be empty to skip that filter
     * @param code selected course to filter tasks
     * @param date filters tasks due before this date
     * @param priority filters by priority
     * @param isCompleted filters by completion status
    */
    public List<Task> filterTasks(String code, LocalDateTime date, Priority priority, boolean isCompleted) {
        // initialize
        List<Task> filteredTasks = new ArrayList<>();
        Course course = getCourseByCode(code);

        // return empty array if course is not found
        if (course == null) return filteredTasks;

        // iterates through all tasks
        for (Task task : course.getTasks()) {
            // check if the task matches the filter
            boolean matchDate = (date == null || task.getDueDate().isBefore(date));
            boolean matchPriority = (priority == null || task.getPriority() == priority);
            boolean matchComplete = task.isCompleted() == isCompleted;
            // if so, add it to the list
            if (matchDate && matchPriority && matchComplete) {
                filteredTasks.add(task);
            }
        }

        return filteredTasks;
    }
}