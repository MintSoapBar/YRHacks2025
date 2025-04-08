public class Activity {
    String name;
    int id;
    
    Time start;
    long length;
    Time end;

    public Activity(String name, Time start, long length) {
        this.name = name;
        this.start = start;
        this.length = length;
        this.end = new Time(start.time + length);
    }
}