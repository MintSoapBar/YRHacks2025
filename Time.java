import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Time {
    final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    long time; //in milliseconds since epoch

    ZonedDateTime localDateTime;

    public void setTime(long t) {
        time = t;
        
        localDateTime = Instant.ofEpochMilli(time).atZone(ZoneId.systemDefault());
    }

    //constructor
    public Time(long t) {
        setTime(t);
    }

    public static void main(String[] args) {
        Time t = new Time(System.currentTimeMillis());
        System.out.println(t);
        System.out.println(t.localDateTime.getMonth());
        System.out.println(t.localDateTime.getDayOfMonth());
        System.out.println(t.localDateTime.getYear());
    }

    public String toString() {
        return localDateTime.format(formatter);
    }
}
