package interview.leetcode.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Valid Anagram
 * 有效的字母异位词
 * 思路: sort
 * 优化: HashMap
 */
public class Solution242 {

    public static void main(String[] args) {
        Solution242 lc = new Solution242();
        String s = "anagram";
        String t = "nagaram";
//        boolean res = lc.isAnagram(s, t);
//        boolean res = lc.isAnagram2(s, t);
        boolean res = lc.isAnagram3(s, t);
        System.out.println(res);
    }

    /**
     * sort
     */
    private boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char c1[] = s.toCharArray();
        char c2[] = t.toCharArray();
        Arrays.sort(c1);
        Arrays.sort(c2);
        return String.copyValueOf(c1).equals(String.copyValueOf(c2));
    }

    /**
     * HashMap
     */
    private boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char c1[] = s.toCharArray();
        char c2[] = t.toCharArray();
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map1.get(c1[i]) == null) {
                map1.put(c1[i], 0);
            } else {
                map1.put(c1[i], map1.get(c1[i]) + 1);
            }

            if (map2.get(c2[i]) == null) {
                map2.put(c2[i], 0);
            } else {
                map2.put(c2[i], map2.get(c2[i]) + 1);
            }
        }
        return map1.equals(map2);
    }

    /**
     * 优化HashMap
     * 思路: 题目字符串只包含小写字母a - z一共26个不同的字母,
     *     把每个字母映射到数组里, 用数组的值进行计数, 也属于自己实现的哈希表思路
     * 操作数组比操作map会快一点
     */
    private boolean isAnagram3(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char c1[] = s.toCharArray();
        char c2[] = t.toCharArray();
        int[] dic1 = new int[26];
        int[] dic2 = new int[26];
        Integer aASC = Integer.valueOf('a');
        for (int i = 0; i < s.length(); i++) {
            dic1[Integer.valueOf(c1[i]) - aASC] += 1;
            dic2[Integer.valueOf(c2[i]) - aASC] += 1;
        }
        return Arrays.equals(dic1, dic2);
    }

}
