package studentPlanner.models.task_types;

// imports
import java.util.*;
import java.time.LocalDateTime;

import studentPlanner.models.*;

/**
 * Test subclass
 * @author Edric
*/
public class Test extends Task {
    // fields
    private List<String> lessonsCovered;

    // constructor
    public Test(String name, LocalDateTime dueDate, Priority priority) {
        super(name, dueDate, priority);
        this.lessonsCovered = new ArrayList<>();
    }

    // getters
    public List<String> getLessons() {
        return lessonsCovered;
    }

    // setters
    public void addLesson(String lesson) {
        lessonsCovered.add(lesson);
    }
    public void removeLesson(String lesson) {
        if (lessonsCovered.contains(lesson)) {
            lessonsCovered.remove(lesson);
        }
    }

    // helper methods
    public boolean isComprehensive() {
        return lessonsCovered.size() >= 5;
    }

    // abstract methods
    public String getSummary() {
        return super.getInfo() + " | Test with lessons: " + String.join(", ", lessonsCovered);
    }
}