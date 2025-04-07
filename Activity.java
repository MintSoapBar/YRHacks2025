
public class Activity {
    String name;
    Time start;
    int length;
    Time end;

    public Activity(String name, Time start, int length) {
        this.name = name;
        this.start = start;
        this.length = length;
        // this.end = new Time(start.getHour(), start.getMinute() + length);
    }
}
