package interview.loadBalancing.consistentHash;

import java.util.SortedMap;
import java.util.TreeMap;

public class TreeMapTest {

    public static void main(String[] args) {
        SortedMap<String, Integer> sortedMap = new TreeMap<String, Integer>(){{
            put("1", 1);
            put("2", 2);
            put("3", 3);
            put("4", 4);
            put("5", 5);
            put("6", 6);
            put("7", 7);
        }};
        /*
        sortedMap:
            2
          /  \
        1    4
           /  \
         3     6
             /  \
           5    7
         */
        // 获取大于等于4节点的子树
        SortedMap<String, Integer> subMap = sortedMap.tailMap("4");
        /*
        subMap:
            4
          /  \
        3     6
            /  \
          5    7
         */
        // firstKey()获取树的第一个元素（最小元素）
        System.out.println(subMap.firstKey());
        System.out.println(sortedMap.firstKey());
    }
}
