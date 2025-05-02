package studentPlanner;

// imports
import java.time.LocalDateTime;

import studentPlanner.models.*;
import studentPlanner.models.task_types.*;

public class Main {
    public static void main(String[] args) {
        // creates new system
        Manager manager = new Manager();
        InteractiveSystem interactiveSystem = new InteractiveSystem(manager);
        demo(manager);

        // runs interactive system
        interactiveSystem.run();
    }
    
    /**
     * Creates courses and tasks for demo
     * can be removed from main
    */
    private static void demo(Manager manager) {
        // create courses
        Course compSci = new Course("Computer Science");
        Course english = new Course("English");
        Course physics = new Course("Physics");

        // create and add tasks to comp sci
        Homework compSciHw1 = new Homework("CodeHS: 4.1", LocalDateTime.now().plusDays(1), Priority.LOW, 9);
        Homework compSciHw2 = new Homework("CodeHS: 4.2", LocalDateTime.now().plusDays(1), Priority.LOW, 5);
        Homework compSciHw3 = new Homework("CodeHS: 4.3", LocalDateTime.now().plusDays(2), Priority.LOW, 9);
        Homework compSciHw4 = new Homework("CodeHS: 4.4", LocalDateTime.now().plusDays(2), Priority.LOW, 10);
        Homework compSciHw5 = new Homework("CodeHS: 4.5", LocalDateTime.now().plusDays(3), Priority.LOW, 9);

        Project compSciProj1 = new Project("Native Java Review", LocalDateTime.now().plusDays(1), Priority.HIGH);
        Project compSciProj2 = new Project("Careers in Computer Science", LocalDateTime.now().plusDays(2), Priority.HIGH);
        Project compSciProj3 = new Project("Testing and Collaboration", LocalDateTime.now().plusDays(3), Priority.HIGH);
        Project compSciProj4 = new Project("Rate-A-Place Review", LocalDateTime.now().plusDays(4), Priority.HIGH);

        Test compSciTest1 = new Test("Unit 1 Test", LocalDateTime.now().plusDays(2), Priority.HIGH);
        Test compSciTest2 = new Test("Unit 2 Test", LocalDateTime.now().plusDays(2), Priority.HIGH);

        compSciProj3.addMember("Edric");
        compSciProj3.addMember("Hasini");

        compSciTest1.addLesson("Development Environment");
        compSciTest1.addLesson("Command Line Interface");
        compSciTest1.addLesson("GitHub Workflow");
        compSciTest1.addLesson("Native Java");

        compSciTest2.addLesson("Encapsulation");
        compSciTest2.addLesson("Class Methods and Variables");
        compSciTest2.addLesson("Aggregation");
        compSciTest2.addLesson("Inheritance");
        compSciTest2.addLesson("Polymorphism");
        compSciTest2.addLesson("UML Diagrams");

        compSci.addTask(compSciHw1);
        compSci.addTask(compSciHw2);
        compSci.addTask(compSciHw3);
        compSci.addTask(compSciHw4);
        compSci.addTask(compSciHw5);

        compSci.addTask(compSciProj1);
        compSci.addTask(compSciProj2);
        compSci.addTask(compSciProj3);
        compSci.addTask(compSciProj4);

        compSci.addTask(compSciTest1);
        compSci.addTask(compSciTest2);

        // create and add tasks to physics
        Homework physicsHw1 = new Homework("Hw: Math Skills Review", LocalDateTime.now().plusDays(1), Priority.LOW, 4);
        Homework physicsHw2 = new Homework("Hw: Kinematics", LocalDateTime.now().plusDays(2), Priority.LOW, 6);
        Homework physicsHw3 = new Homework("Hw: Dynamics", LocalDateTime.now().plusDays(3), Priority.LOW, 7);
        Homework physicsHw4 = new Homework("Hw: Energy", LocalDateTime.now().plusDays(4), Priority.LOW, 8);
        Homework physicsHw5 = new Homework("Hw: Fields", LocalDateTime.now().plusDays(5), Priority.LOW, 7);

        Project physicsProj1 = new Project("Lab: Stomp Rocket", LocalDateTime.now().plusDays(1), Priority.HIGH);
        Project physicsProj2 = new Project("Lab: Motion Down a Ramp", LocalDateTime.now().plusDays(2), Priority.HIGH);
        Project physicsProj3 = new Project("Lab: Friction Investigation", LocalDateTime.now().plusDays(3), Priority.HIGH);
        Project physicsProj4 = new Project("Lab: Pendulum", LocalDateTime.now().plusDays(4), Priority.HIGH);

        Test physicsTest1 = new Test("Kinematics Test", LocalDateTime.now().plusDays(1), Priority.HIGH);
        Test physicsTest2 = new Test("Dynamics Test", LocalDateTime.now().plusDays(2), Priority.HIGH);
        Test physicsTest3 = new Test("Energy Test", LocalDateTime.now().plusDays(3), Priority.HIGH);
        Test physicsTest4 = new Test("Fields Test", LocalDateTime.now().plusDays(4), Priority.HIGH);

        physics.addTask(physicsHw1);
        physics.addTask(physicsHw2);
        physics.addTask(physicsHw3);
        physics.addTask(physicsHw4);
        physics.addTask(physicsHw5);

        physics.addTask(physicsProj1);
        physics.addTask(physicsProj2);
        physics.addTask(physicsProj3);
        physics.addTask(physicsProj4);

        physics.addTask(physicsTest1);
        physics.addTask(physicsTest2);
        physics.addTask(physicsTest3);
        physics.addTask(physicsTest4);

        // create and add tasks to english
        Homework englishHw1 = new Homework("Being a Scientist", LocalDateTime.now().plusDays(5), Priority.LOW, 10);
        Homework englishHw2 = new Homework("Story of Your Life", LocalDateTime.now().plusDays(5), Priority.LOW, 10);

        Project englishProj1 = new Project("Personal Essay", LocalDateTime.now().plusDays(1), Priority.HIGH);
        Project englishProj2 = new Project("Poetry: Verbal Visual", LocalDateTime.now().plusDays(2), Priority.HIGH);
        Project englishProj3 = new Project("Station Eleven Seminar", LocalDateTime.now().plusDays(3), Priority.HIGH);

        Test englishTest1 = new Test("Anthology Test", LocalDateTime.now().plusDays(1), Priority.HIGH);
        Test englishTest2 = new Test("Poetry Test", LocalDateTime.now().plusDays(2), Priority.HIGH);
        Test englishTest3 = new Test("Station Eleven Test", LocalDateTime.now().plusDays(3), Priority.HIGH);

        english.addTask(englishHw1);
        english.addTask(englishHw2);

        english.addTask(englishProj1);
        english.addTask(englishProj2);
        english.addTask(englishProj3);

        english.addTask(englishTest1);
        english.addTask(englishTest2);
        english.addTask(englishTest3);

        // add courses to manager
        manager.addCourse(compSci);
        manager.addCourse(english);
        manager.addCourse(physics);
    }
}