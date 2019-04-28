package interview.my;

import java.util.ArrayList;
import java.util.List;

public class JDKBugs {

    private static void bug1() {
        String[] paths = "object1.object2.object3".split(".");
        System.out.println(paths.length); // 0
    }

    private static void bug2() {
        int x = 3;
        int y = 4;
        x = 4;
        y = 3;
        int a = (x < y) ? null : 0;
        System.out.println(a);
    }

    private static void bug3() {
        List<String> arr = new ArrayList<>();
        arr.add("a");
        List<Long> list = new ArrayList(arr);
        System.out.println(list.get(0));
    }

    public static void main(String[] args) {
        bug1();
        bug2();
        bug3();
    }
}
