import java.time.Instant;
import java.time.LocalDateTime;
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
        System.out.println(new Time(System.currentTimeMillis()));
    }

    public String toString() {
        return localDateTime.format(formatter);
    }
}
