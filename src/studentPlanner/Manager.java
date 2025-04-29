package studentPlanner;

// imports
import java.util.*;
import java.time.LocalDateTime;

import studentPlanner.models.*;
import studentPlanner.models.task_types.*;

/**
 * Represents a manager for a list of courses
 * Core student planner logic
 * @author Edric
*/
public class Manager {
    // fields
    private List<Course> courses;

    // constructor
    public Manager() {
        this.courses = new ArrayList<>();
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
     * @return a list of tasks matching the filters
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

    /**
     * Auto prioritizes tasks for a course based on:
     * 1. Completion (unfinished first)
     * 2. Due date (earliest first)
     * 3. Homework (lower problems solved ratio first)
     * 4. Test (comprehensive tests first)
     * @param code selected course to prioritize tasks
     * @return a list of tasks with their priorities set automatically
     */
    public List<Task> getNewPrioritizedTasks(String code) {
        // initialize
        Course course = getCourseByCode(code);
        if (course == null) return new ArrayList<>();
        List<Task> prioritizedTasks = new ArrayList<>(course.getTasks());

        /*
         * t1 and t2 represent the pair of elements being compared
         * return negative = t1 comes before t2
         * return positive = t1 comes after t2
         * return 0 = no change in order
         * .compare(a, b) => 
            - returns negative if a < b 
            - returns 0 if a == b
            - returns positive if a > b
        */
        prioritizedTasks.sort((t1, t2) -> {
            // 1. compare by completion (unfinished first)
            if (t1.isCompleted() != t2.isCompleted()) {
                return Boolean.compare(t1.isCompleted(), t2.isCompleted());
            }

            // 2. compare by due date (earlier first)
            int dueDateComparison = t1.getDueDate().compareTo(t2.getDueDate());
            if (dueDateComparison != 0) {
                return dueDateComparison;
            }

            // 3. special handling: homework
            if (t1 instanceof Homework && t2 instanceof Homework) {
                Homework homework1 = (Homework) t1;
                Homework homework2 = (Homework) t2;

                double progress1 = homework1.getCompletionRatio();
                double progress2 = homework2.getCompletionRatio();

                return Double.compare(progress1, progress2);
            }

            // 4. special handling: test
            if (t1 instanceof Test && t2 instanceof Test) {
                Test test1 = (Test) t1;
                Test test2 = (Test) t2;

                return Boolean.compare(test2.isComprehensive(), test1.isComprehensive());
            }

            // 5. otherwise, fallback: compare by initial priority
            return t1.getPriority().compareTo(t2.getPriority());
        });
        
        // after sorting, divide the list into thirds and prioritize
        for (int i = 0; i < prioritizedTasks.size(); i++) {
            Task task = prioritizedTasks.get(i);
            double taskPositionRatio = (double) i / prioritizedTasks.size();

            // if task is top third of the list
            if (taskPositionRatio < 1.0 / 3) {
                task.setPriority(Priority.HIGH);
            }
            // if task is middle third of the list
            else if (taskPositionRatio < 2.0 / 3) {
                task.setPriority(Priority.MEDIUM);
            }
            // if task is bottom third of the list
            else {
                task.setPriority(Priority.LOW);
            }
        }
        
        return prioritizedTasks;
    }
}