package studentPlanner;

public class Main {
    public static void main(String[] args) {
        // creates new system
        Manager manager = new Manager();
        InteractiveSystem interactiveSystem = new InteractiveSystem(manager);

        // runs interactive system
        interactiveSystem.run();
    }
}