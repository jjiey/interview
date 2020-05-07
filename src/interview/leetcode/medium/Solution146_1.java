package interview.leetcode.medium;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LRU Cache
 * LRU缓存机制
 */
public class Solution146_1 {

    public static void main(String[] args) {
        Solution146_1 lc = new Solution146_1();
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
        // 保持插入顺序
        private Map<Integer, Integer> map = new LinkedHashMap<>();

        public LRUCache(int capacity) {
            this.capacity = capacity;
        }

        public int get(int key) {
            if (map.keySet().contains(key)) {
                int value = map.get(key);
                map.remove(key);
                // 保证每次查询后，都在链表末尾
                map.put(key, value);
                return value;
            }
            return -1;
        }

        public void put(int key, int value) {
            if (map.keySet().contains(key)) {
                map.remove(key);
            } else if (capacity == map.size()) {
                // map.remove(map.entrySet().iterator().next().getKey());
                Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
                iterator.next();
                iterator.remove();
            }
            // 保证每次查询后，都在链表末尾
            map.put(key, value);
        }
    }
}
