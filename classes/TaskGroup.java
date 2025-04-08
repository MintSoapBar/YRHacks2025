package classes;

import java.util.ArrayList;

public class TaskGroup {
    String name;
	ArrayList<Task> tasks = new ArrayList<>();
	Time startDate;
	Time dueDate;
	Double priority;
	String description;

	// Constructor
	public TaskGroup(String name, Time startDate, Time dueDate, String description, Double priority) {
		this.name = name;
		this.startDate = startDate;
		this.dueDate = dueDate;
		this.description = description;
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

	public String toString() {
		return String.format("%s%n%s%n%s%n%f%n", name, startDate, dueDate, priority);
	}
}
