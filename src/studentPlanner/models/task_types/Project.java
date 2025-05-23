package studentPlanner.models.task_types;

// imports
import java.util.*;
import java.time.LocalDateTime;

import studentPlanner.models.*;

/**
 * Project subclass
 * @author Edric
*/
public class Project extends Task {
    // fields
    private List<String> groupMembers;

    // constructor
    public Project (String name, LocalDateTime dueDate, Priority priority) {
        super(name, dueDate, priority);
        this.groupMembers = new ArrayList<>();
    }

    // getters
    public List<String> getMembers() {
        return groupMembers;
    }

    // setters
    public void addMember(String member) {
        groupMembers.add(member);
    }
    public void removeMember(String member) {
        if (groupMembers.contains(member)) {
            groupMembers.remove(member);
        }
    }
    
    // abstract methods
    public String getSummary() {
        String taskInfo = super.getInfo();
        String groupInfo = groupMembers.isEmpty() ? "No members" : String.join(", ", groupMembers);
        return String.format(
            "%s\nGroup Members: %s", 
            taskInfo, groupInfo
        );
    }
}