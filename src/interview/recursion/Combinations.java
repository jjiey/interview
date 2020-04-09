package interview.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 列出任取其中n个元素的所有组合
 */
public class Combinations {

    /**
     * Generates all combinations and output them.
     * selecting n elements from data.
     * @param data
     * @param n 从data里选择n个元素
     */
    public void combinations(List<Integer> selected, List<Integer> data, int n) {
        // n 什么时候 == 0，选光了就等于0了，此时应该输出已选择的组合
        if(n == 0){
            System.out.println(selected.toString());
            return;
        }

        if(data.isEmpty()){
            return;
        }

        // 选择第0个元素，那就需要从剩下的元素中再选择 n - 1 个元素
        selected.add(data.get(0));
        combinations(selected, data.subList(1, data.size()), n - 1);

        // 不选择第0个元素，那就需要从剩下的元素中选择 n 个元素
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
