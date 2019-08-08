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

        private int cap;
        private Map<Integer, Integer> map = new HashMap<>();
        private List<Integer> list = new LinkedList<>();

        public LRUCache(int capacity) {
            this.cap = capacity;
        }

        public int get(int key) {
            Integer temp = map.get(key);
            if (temp == null) {
                return -1;
            }
            list.remove((Integer) key);
            list.add(key);
            return temp;
        }

        public void put(int key, int value) {
            if (map.get(key) != null) {
                map.put(key, value);
                list.remove((Integer) key);
                list.add(key);
            } else {
                if (cap != map.size()) {
                    list.add(key);
                    map.put(key, value);
                } else {
                    Integer x = list.get(0);
                    map.remove(x);
                    list.remove(0);
                    map.put(key, value);
                    list.add(key);
                }
            }
        }
    }

}
