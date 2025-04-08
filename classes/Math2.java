package classes;

public class Math2 {
    public static int lerp(int a, int b, double alpha) {
        return (int) (a + (b - a) * alpha);
    }

    public static double lerp(double a, double b, double alpha) {
        return a + (b - a) * alpha;
    }

    public static int clamp(int x, int min, int max) {
        return Math.max(min, Math.min(max, x));
    }
}
