package interview.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 列出所有组合
 */
public class Combinations {

    /**
     * Generates all combinations and output them.
     * selecting n elements from data.
     * @param data
     * @param n
     */
    public void combinations(List<Integer> selected, List<Integer> data, int n) {
        if(n == 0){
            System.out.println(selected.toString());
            return;
        }

        if(data.isEmpty()){
            return;
        }

        // 选择第0个元素
        selected.add(data.get(0));
        combinations(selected, data.subList(1, data.size()), n - 1);

        // 不选择第0个元素
        selected.remove(selected.size() - 1);
        combinations(selected, data.subList(1, data.size()), n);
    }

    public static void main(String[] args) {
        Combinations comb = new Combinations();
        comb.combinations(new ArrayList<>(), Arrays.asList(1, 2, 3, 4), 3);
//        comb.combinations(new ArrayList<>(), Arrays.asList(1, 2, 3, 4), 2);
//        System.out.println("==========");
//        comb.combinations(new ArrayList<>(), new ArrayList<>(), 2);
//        System.out.println("==========");
//        comb.combinations(new ArrayList<>(), new ArrayList<>(), 0);
//        System.out.println("==========");
//        comb.combinations(new ArrayList<>(), Arrays.asList(1, 2, 3, 4), 1);
//        System.out.println("==========");
//        comb.combinations(new ArrayList<>(), Arrays.asList(1, 2, 3, 4), 0);
//        System.out.println("==========");
//        comb.combinations(new ArrayList<>(), Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 4);
    }

}
