package classes;

public class Activity extends TimeBlock {
	long startTime; // in milliseconds
	long endTime; // in milliseconds
	byte daysOfWeek; // 0-6 for Sun-Sat, 7 for all days

	// Constructor
	public Activity(String name, String description, long startTime, long endTime, byte daysOfWeek) {
		super(name, description);
		this.startTime = startTime;
		this.endTime = endTime;
		this.daysOfWeek = daysOfWeek;
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
	
}
