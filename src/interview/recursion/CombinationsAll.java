package interview.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 列出所有组合
 */
public class CombinationsAll {

    public static void main(String[] args) {
        Combinations comb = new Combinations();
        List<Integer> datas = Arrays.asList(1, 2, 3, 4);
        for (int i = 0; i <= datas.size(); i++) {
            comb.combinations(new ArrayList<>(), datas, i);
        }
    }
}
