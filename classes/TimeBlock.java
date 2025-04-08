package classes;

import java.awt.*;

public class TimeBlock {
	String name;
	String description;
	long startTime; // in milliseconds
	long endTime; // in milliseconds

	Button scheduleButton;

	public void refreshButtons() {
        scheduleButton = new Button(
            Driver.timeBlockLeft, 
            Driver.timeBlockGap, 
            Driver.timeBlockWidth, 
            Driver.timeBlockHeight, 
            new Color(255, 255, 255), 
			name + " - " + Time.interpretLong(startTime) + "-" + Time.interpretLong(endTime)
            );
	}

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