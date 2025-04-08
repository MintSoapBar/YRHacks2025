package classes;

public class Task extends TimeBlock implements Comparable<Task> {
    Time dueDate;
    long length;
    Double priority;

    public Task(String name, String description, Time dueDate, long length, Double priority) {
        super(name, description);
        this.dueDate = dueDate;
        this.length = length;
        this.priority = priority;
    }

    public String toString() {
        return String.format("Name: %s%nDescription: %s%nDue Date: %s%nLength: %d%nPriority: %f%n", 
            name, description, dueDate, length, priority);
    }

    public int compareTo(Task task) {
        if (System.currentTimeMillis() - dueDate.time > 0) {
            return -1; // this task is overdue
        }
        return -Double.compare(((System.currentTimeMillis() - this.dueDate.time) / this.priority), ((System.currentTimeMillis() - task.dueDate.time) / task.priority));
    }
}
