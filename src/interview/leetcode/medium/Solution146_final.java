package interview.leetcode.medium;

import java.util.*;

/**
 * LRU Cache
 * LRU缓存机制
 */
public class Solution146_final {

    public static void main(String[] args) {
        Solution146_final lc = new Solution146_final();
        int capacity = 3;
        LRUCache<Integer, Integer> obj = lc.new LRUCache<>(capacity);
        int param = obj.get(1);
        System.out.println(param);
        obj.put(1, 1);
        int param2 = obj.get(1);
        System.out.println(param2);
    }

    class LRUCache<K, V> extends LinkedHashMap<K, V> {

        private final int CACHE_SIZE;

        public LRUCache(int capacity) {
            // true 表示让 linkedHashMap 按照访问顺序来进行排序，最近访问的放在头部，最老访问的放在尾部。
            super((int) Math.ceil(capacity / 0.75) + 1, 0.75f, true);
            CACHE_SIZE = capacity;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            // 当 map中的数据量大于指定的缓存个数的时候，就自动删除最老的数据。
            return size() > CACHE_SIZE;
        }
    }
}
