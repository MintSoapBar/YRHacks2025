package classes;

public class Task {
    String name;
    Time dueDate;
    long length;
    String description;
    Double priority;

    public Task(String name, Time dueDate, String description, Double priority) {
        this.name = name;
        this.dueDate = dueDate;
        this.length = dueDate.time - System.currentTimeMillis();
        this.description = description;
        this.priority = priority;
    }
}
