package classes;

import java.util.ArrayList;

public class TaskGroup {
    String name;
	String description;
	ArrayList<Task> tasks = new ArrayList<>();
	Time startDate;
	Time dueDate;
	Double priority;
	

	// Constructor
	public TaskGroup(String name, String description, Time startDate, Time dueDate, Double priority) {
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.dueDate = dueDate;
		this.priority = priority;
	}

	public void addTask(Task task) {
		tasks.add(task);
	}
	public Task getTask(int index) {
		return tasks.get(index);
	}
	public ArrayList<Task> getTasks() {
		return tasks;
	}
	public void removeTask(Task task) {
		tasks.remove(task);
	}

	@Override
	public String toString() {
		return String.format(
			"Task Group: %s%nDescription: %s%nStart Date: %s%nDue Date: %s%nPriority: %.2f%nTasks: %s",
			name, description, startDate, dueDate, priority, tasks
		);
	}
}
