package classes;

public class TimeBlock {
	String name;
	String description;
	long startTime; // in milliseconds
	long endTime; // in milliseconds

	Button scheduleButton;

	public TimeBlock(String name, String description, long startTime, long endTime) {
		this.name = name;
		this.description = description;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public String toString() {
		return String.format("Name: %s%nDescription: %s%nStart Time: %tF %<tT%nEnd Time: %tF %<tT%n", 
			name, description, startTime, endTime);
	}
}