import java.time.*;
import java.time.format.*;

public class Time {
    final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    long time; //in milliseconds since epoch

    ZonedDateTime date;

    public void setTime(long t) {
        time = t;
        
        date = Instant.ofEpochMilli(time).atZone(ZoneId.systemDefault());
    }

    public Time add(Time o) {
        return new Time(time + o.time);
    }

    //constructor
    public Time(long t) {
        setTime(t);
    }

    public static void main(String[] args) {
        Time t = new Time(System.currentTimeMillis());
        System.out.println(t);
        System.out.println(t.date.getMonth());
        System.out.println(t.date.getDayOfMonth());
        System.out.println(t.date.getYear());
    }

    public String toString() {
        return date.format(FORMATTER);
    }
}
