package interview.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        List<Integer> a = Arrays.asList(1, 2, 3, 4);
        ArrayList<Object> b = new ArrayList<>();
//        a.add(6); // UnsupportedOperationException
        b.add(6);
        a.remove(6);
        b.remove(6);
    }
    
}
