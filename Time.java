import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Time {
    long time;

    public Time(long t) {
        time = t;
    }

    public static void main(String[] args) {
        System.out.println(new Time(System.currentTimeMillis()));
    }

    public String toString() {
        Instant instant = Instant.ofEpochMilli(time);
        ZonedDateTime localDateTime = instant.atZone(ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDateTime.format(formatter);
    }
}
