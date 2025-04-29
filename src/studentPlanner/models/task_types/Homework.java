package studentPlanner.models.task_types;

// imports
import java.time.LocalDateTime;

import studentPlanner.models.*;

/**
 * Homework subclass
 * @author Edric
*/
public class Homework extends Task {
    // fields
    private int problemCount;
    private int problemsSolved;

    // constructor
    public Homework(String name, LocalDateTime dueDate, Priority priority, int problemCount) {
        super(name, dueDate, priority);
        this.problemCount = problemCount;
        this.problemsSolved = 0;
    }
    
    // getters
    public int getProblemCount() {
        return problemCount;
    }
    public int getProblemsSolved() {
        return problemsSolved;
    }
    public double getCompletionRatio() {
        return problemCount == 0 ? 0 : (double) problemsSolved / problemCount;
    }

    // setters
    public void markSolved(int numSolved) {
        problemsSolved += numSolved;
        if (problemsSolved >= problemCount) {
            problemsSolved = problemCount;
        }
    }

    // abstract methods
    public String getSummary() {
        return super.getInfo() + " | Homework with " + problemsSolved + " problems solved out of " + problemCount;
    }
}