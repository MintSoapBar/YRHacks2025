package classes;

import java.awt.*;

public class Activity extends TimeBlock implements Comparable<Activity> {
	long startTime; // in milliseconds
	long endTime; // in milliseconds
	byte daysOfWeek; // 0-6 for Sun-Sat, 7 for all days
	
    Button activityListButton;

	public void createButtons() {
        scheduleButton = new Button(Driver.timeBlockLeft, Driver.activityListGap, Driver.timeBlockWidth, Driver.timeBlockHeight, new Color(255, 255, 255), name);
        Driver.scheduleScrollingFrame.addChild(scheduleButton);

        activityListButton = new Button(Driver.activityListLeft, Driver.activityListGap, Driver.activityListWidth, Driver.activityListHeight, new Color(255, 255, 255));
        Driver.activityListScrollingFrame.addChild(activityListButton);
        Frame titlelabel = new Frame(10, 0, 0, Driver.activityListHeight, name);
        titlelabel.textAlignX = -1;
        activityListButton.addChild(titlelabel);
    }

	// Constructor
	public Activity(String name, String description, long startTime, long endTime, byte daysOfWeek) {
		super(name, description);
		this.startTime = startTime;
		this.endTime = endTime;
		this.daysOfWeek = daysOfWeek;

		createButtons();
	}

	@Override
	public String toString() {
		return String.format("Name: %s%nDescription: %s%nStart Time: %tF %<tT%nEnd Time: %tF %<tT%nDays of Week: %s%n", 
			name, description, startTime, endTime, Integer.toBinaryString(daysOfWeek));
	}

	public static void main(String[] args) {
		// Example usage
		Activity activity = new Activity("Morning Run", "A refreshing morning run.", System.currentTimeMillis(), System.currentTimeMillis() + 3600000, (byte) 0b1111110);
		System.out.println(activity);
	}
	
	public int compareTo(Activity o) {
		if (this.startTime < o.startTime) {
			return -1;
		} else if (this.startTime > o.startTime) {
			return 1;
		} else {
			return name.compareTo(o.name);
		}
	}
}
