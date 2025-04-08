package classes;

public class TimeBlock {
	String name;
	String description;

	Button scheduleButton;

	public TimeBlock(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public String toString() {
		return "TimeBlock{name='" + name + "', Description=" + description + "}";
	}
}