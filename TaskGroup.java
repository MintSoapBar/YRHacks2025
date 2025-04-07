import java.util.ArrayList;

public class TaskGroup {
    String name;
    int id;

	ArrayList<Task> tasks = new ArrayList<>();
    
	Time startDate;
	Time dueDate;
	Double priority;

	// Constructor
	public TaskGroup(String name, Time dueDate, Time startDate, Double priority) {
		this.name = name;
		this.startDate = startDate;
		this.dueDate = dueDate;
		this.priority = priority;
	}

	public void addTask(Task task) {
		tasks.add(task);
	}
	public void removeTask(Task task) {
		tasks.remove(task);
	}
}
