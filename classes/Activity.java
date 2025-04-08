package classes;

import java.awt.*;

public class Activity extends TimeBlock implements Comparable<Activity> {
	byte daysOfWeek; // 0-6 for Sun-Sat, 7 for all days
	
    Button activityListButton;

	static String[] days = new String[] {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

	public String byteToDays(byte b) {
		String s = "";

		for (int i = 0; i < 7; i++) {
			if (((1 << (7 - i)) & b) > 0) {
				if (s.length() > 0) s += "/";
				s += days[i];
			}
		}

		return s;
	}

	public void createButtons() {
        scheduleButton = new Button(Driver.timeBlockLeft, Driver.activityListGap, Driver.timeBlockWidth, Driver.timeBlockHeight, new Color(255, 255, 255), name);
        Driver.scheduleScrollingFrame.addChild(scheduleButton);

        activityListButton = new Button(
			Driver.activityListLeft, 
			Driver.activityListGap, 
			Driver.activityListWidth, 
			Driver.activityListHeight, 
			new Color(255, 255, 255), 
			name + " - " + byteToDays(daysOfWeek) + " - " + startTime + " " + endTime
			);
    }

	// Constructor
	public Activity(String name, String description, long startTime, long endTime, byte daysOfWeek) {
		super(name, description, startTime, endTime);
		this.daysOfWeek = daysOfWeek;

		createButtons();
	}

	@Override
	public String toString() {
		return String.format("Name: %s%nDescription: %s%nStart Time: %02d:%02d%nEnd Time: %02d:%02d%nDays of Week: %s%n", 
			name, description, startTime / 3600000, startTime / 60000 % 60, endTime / 3600000, endTime / 60000 % 60, Integer.toBinaryString(daysOfWeek));
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
