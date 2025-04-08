package classes;

public class Task {
    String name;
    String description;
    Time dueDate;
    long length;
    Double priority;

    public Task(String name, String description, Time dueDate, Double priority) {
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.length = dueDate.time - System.currentTimeMillis();
        this.priority = priority;
    }

    public String toString() {
        return String.format("Name: %s%nDescription: %s%nDue Date: %s%nLength: %d%nPriority: %f%n", 
            name, description, dueDate, length, priority);
    }
}
