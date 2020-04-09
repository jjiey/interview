package interview.loadBalancing.util;

public class Sequence {

    private static Integer num = 0;

    public static Integer getAndIncrement() {
        return num++;
    }

    public static Integer incrementAndGet() {
        return ++num;
    }
}
