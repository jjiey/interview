package interview.leetcode.medium;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LRU Cache
 * LRU缓存机制
 */
public class Solution146_3 {

    public static void main(String[] args) {
        Solution146_3 lc = new Solution146_3();
        int capacity = 3;
        LRUCache obj = lc.new LRUCache(capacity);
        int param = obj.get(1);
        System.out.println(param);
        obj.put(1, 1);
        int param2 = obj.get(1);
        System.out.println(param2);
    }

    class LRUCache extends LinkedHashMap<Integer, Integer> {

        private final int CACHE_SIZE;

        public LRUCache(int capacity) {
            super((int) Math.ceil(capacity / 0.75) + 1, 0.75f, true);
            CACHE_SIZE = capacity;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return size() > CACHE_SIZE;
        }

        // 重写get方法, 不重写默认返回的是null, 重写是为了根据题目要求返回-1
        public int get(int key) {
            if (super.get(key) == null) {
                return -1;
            }
            return super.get(key);
        }
    }
}
