package classes;

public class Task extends TimeBlock implements Comparable<Task> {
    Time dueDate;
    long length;
    Double priority;

    public Task(String name, String description, long startTime, long endTime, Time dueDate, long length, Double priority) {
        super(name, description, startTime, endTime);
        this.dueDate = dueDate;
        this.length = length;
        this.priority = priority;
    }

    public String toString() {
        return String.format("Name: %s%nDescription: %s%nStart Time: %02d:%02d%nEnd Time: %02d:%02d%nDue Date: %s%nLength: %d%nPriority: %.2f%n", 
            name, description, startTime / 3600000, startTime / 60000 % 60, endTime / 3600000, endTime / 60000 % 60, dueDate, length, priority);
    }

    public int compareTo(Task task) {
        if (System.currentTimeMillis() - dueDate.time > 0) {
            return -1; // this task is overdue
        }
        return -Double.compare(((System.currentTimeMillis() - this.dueDate.time) / this.priority), ((System.currentTimeMillis() - task.dueDate.time) / task.priority));
    }
}
