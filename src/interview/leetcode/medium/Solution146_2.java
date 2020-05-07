package interview.leetcode.medium;

import java.util.*;

/**
 * LRU Cache
 * LRU缓存机制
 */
public class Solution146_2 {

    public static void main(String[] args) {
        Solution146_2 lc = new Solution146_2();
        int capacity = 3;
        LRUCache obj = lc.new LRUCache(capacity);
        int param = obj.get(1);
        System.out.println(param);
        obj.put(1, 1);
        int param2 = obj.get(1);
        System.out.println(param2);
    }

    class LRUCache {

        private int capacity;
        // 维护缓存内容
        private Map<Integer, Integer> map = new HashMap<>();
        // 维护key顺序
        private List<Integer> list = new LinkedList<>();

        public LRUCache(int capacity) {
            this.capacity = capacity;
        }

        public int get(int key) {
            Integer value = map.get(key);
            if (value == null) {
                return -1;
            }
            list.remove(key);
            list.add(key);
            return value;
        }

        public void put(int key, int value) {
            if (map.get(key) != null) {
                list.remove((Integer) key);
            } else if (capacity == map.size()) {
                map.remove(list.get(0));
                list.remove(0);
            }
            map.put(key, value);
            list.add(key);
        }
    }
}
