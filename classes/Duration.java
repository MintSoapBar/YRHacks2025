package classes;

//class to convert milliseconds time into human readable time
//eg displays 12000ms as 12 seconds
public class Duration {
    final static long second = 1000;
    final static long minute = second*60;
    final static long hour = minute*60;
    final static long day = hour*24;
    final static long week = day*7;
    final static long month = day*30;
    final static long year = (long) (day*365.25);

    long time; //in milliseconds
    String textLong;
    String textShort;

    public void setTime(long t) {
        time = Math.abs(t);

        if (time < minute) {
            textLong = time/second + " second" + (time >= 2*second || time < second? "s": "");
            textShort = time/second + "s";
        } else if (time < hour) {
            textLong = time/minute + " minute" + (time >= 2*minute? "s": "");
            textShort = time/minute + "min.";
        } else if (time < day) {
            textLong = time/hour + " hour" + (time >= 2*hour? "s": "");
            textShort = time/hour + "hr.";
        } else if (time < week) {
            textLong = time/day + " day" + (time >= 2*day? "s": "");
            textShort = time/day + "d";
        } else if (time < month) {
            textLong = time/week + " week" + (time >= 2*week? "s": "");
            textShort = time/week + "w";
        } else if (time < year) {
            textLong = time/month + " month" + (time >= 2*month? "s": "");
            textShort = time/month + "mnth.";
        } else {
            textLong = time/year + " year" + (time >= 2*year? "s": "");
            textShort = time/year + "yr.";
        }

        if (t < 0) textLong += " ago";
    }

    public Duration(long t) {
        setTime(t);
    }

    public static void main(String[] args) {
        System.out.println(new Duration(-123));
        System.out.println(new Duration(-1234));
        System.out.println(new Duration(-12345));

        System.out.println(new Duration(123));
        System.out.println(new Duration(1234));
        System.out.println(new Duration(12345));
        System.out.println(new Duration(123456));
        System.out.println(new Duration(1234567));
        System.out.println(new Duration(12345678));
        System.out.println(new Duration(123456789));
        System.out.println(new Duration(1234567890));
        System.out.println(new Duration(12345678901l));
        System.out.println(new Duration(123456789012l));
    }

    public String toString() {
        return textLong;
    }
}
