package interview.leetcode.easy;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Longest Common Prefix
 * 最长公共前缀
 */
public class Solution14 {

    public static void main(String[] args) {
        Solution14 lc = new Solution14();
        String[] strs = new String[]{"flower", "flow", "flight"};
        System.out.println(lc.longestCommonPrefix3(strs));
    }

    /**
     * 横向扫描
     */
    private String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0 || "".equals(strs[0])) {
            return "";
        }
        // 最长公共子串的长度，初始值为第 0 个字符串的长度
        int end = strs[0].length();
        for (int i = 1; i < strs.length; i++) {
            if ("".equals(strs[i])) {
                return "";
            }
            int j = 0;
            while (j < end && j < strs[i].length() && strs[0].charAt(j) == strs[i].charAt(j)) {
                j++;
            }
            end = j;
        }
        return strs[0].substring(0, end);
    }

    /**
     * 横向扫描 2
     */
    private String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0 || "".equals(strs[0])) {
            return "";
        }
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            if ("".equals(strs[i])) {
                return "";
            }
            prefix = commonPrefix(prefix, strs[i]);
        }
        return prefix;
    }

    private String commonPrefix(String str1, String str2) {
        int minLength = Math.min(str1.length(), str2.length());
        int endIndex = 0;
        while (endIndex < minLength && str1.charAt(endIndex) == str2.charAt(endIndex)) {
            endIndex++;
        }
        return str1.substring(0, endIndex);
    }

    /**
     * 纵向扫描
     */
    private String longestCommonPrefix3(String[] strs) {
        if (strs == null || strs.length == 0 || "".equals(strs[0])) {
            return "";
        }
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            // 从第一个字符串开始进行比较
            for (int j = 1; j < strs.length; j++) {
                if ("".equals(strs[j])) {
                    return "";
                }
                // 当前 i 已经比 strs 中某个字符串的长度还要大(比完了某个字符串) 或 strs 中某个字符串当前字符和 strs[0] 的当前字符不相等
                if (i == strs[j].length() || c != strs[j].charAt(i)) {
                    // substring 范围: [beginIndex, endIndex)
                    return strs[0].substring(0, i);
                }
            }
        }
        // 如果一直没返回，说明 strs[0] 本身就是 LCP
        return strs[0];
    }

    /**
     * 分治
     */
    private String longestCommonPrefix4(String[] strs) {
        if (strs == null || strs.length == 0 || "".equals(strs[0])) {
            return "";
        }
        return longestCommonPrefix(strs, 0, strs.length - 1);
    }

    private String longestCommonPrefix(String[] strs, int start, int end) {
        if (start == end) {
            return strs[start];
        } else {
            int mid = (end - start) / 2 + start;
            String lcpLeft = longestCommonPrefix(strs, start, mid);
            String lcpRight = longestCommonPrefix(strs, mid + 1, end);
            return commonPrefix2(lcpLeft, lcpRight);
        }
    }

    private String commonPrefix2(String lcpLeft, String lcpRight) {
        int minLength = Math.min(lcpLeft.length(), lcpRight.length());
        for (int i = 0; i < minLength; i++) {
            if (lcpLeft.charAt(i) != lcpRight.charAt(i)) {
                return lcpLeft.substring(0, i);
            }
        }
        return lcpLeft.substring(0, minLength);
    }

    /**
     * 二分查找
     */
    private String longestCommonPrefix5(String[] strs) {
        if (strs == null || strs.length == 0 || "".equals(strs[0])) {
            return "";
        }
        // high is minLength
        int high = Arrays.stream(strs).mapToInt(String::length).min().getAsInt(), low = 0;
        while (low < high) {
            int mid = (high - low + 1) / 2 + low;
            if (isCommonPrefix(strs, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return strs[0].substring(0, low);
    }

    private boolean isCommonPrefix(String[] strs, int length) {
        String str0 = strs[0].substring(0, length);
        for (int i = 1; i < strs.length; i++) {
            String str = strs[i];
            for (int j = 0; j < length; j++) {
                if (str0.charAt(j) != str.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 先排序，后比较头尾
     */
    private String longestCommonPrefix6(String[] strs) {
        if (strs == null || strs.length == 0 || "".equals(strs[0])) {
            return "";
        }
        Arrays.sort(strs);
        if ("".equals(strs[0])) {
            return "";
        }
        String firstStr = strs[0], lastStr = strs[strs.length - 1];
        int length = Math.min(firstStr.length(), lastStr.length()), endIndex;
        for (endIndex = 0; endIndex < length && firstStr.charAt(endIndex) == lastStr.charAt(endIndex); endIndex++) ;
        return firstStr.substring(0, endIndex);
    }

    /**
     * 先排序，后比较头尾 2
     */
    private String longestCommonPrefix7(String[] strs) {
        if (strs == null || strs.length == 0 || "".equals(strs[0])) {
            return "";
        }
        Arrays.sort(strs);
        if ("".equals(strs[0])) {
            return "";
        }
        String firstStr = strs[0], lastStr = strs[strs.length - 1];
        int length = Math.min(firstStr.length(), lastStr.length()), endIndex = 0;
        while (endIndex < length && firstStr.charAt(endIndex) == lastStr.charAt(endIndex)) {
            endIndex++;
        }
        return firstStr.substring(0, endIndex);
    }

    /**
     * 字典树
     */
    private String longestCommonPrefix8(String[] strs) {
        if (strs == null || strs.length == 0 || "".equals(strs[0])) {
            return "";
        }
        Node root = new Node();
        int end = strs[0].length();
        for (int i = 0; i < strs.length; i++) {
            Node cur = root;
            int n = 0;
            for (int j = 0; j < strs[i].length(); j++) {
                char c;
                if (i == 0) {
                    c = strs[0].charAt(j);
                    cur.next.put(c, new Node());
                    n++;
                } else if ("".equals(strs[i])) {
                    return "";
                } else {
                    c = strs[i].charAt(j);
                    if (!cur.next.containsKey(c)) {
                        break;
                    } else {
                        n++;
                    }
                }
                cur = cur.next.get(c);
            }
            end = Math.min(end, n);
        }
        return strs[0].substring(0, end);
    }

    private class Node {

        public HashMap<Character, Node> next;

        public Node() {
            next = new HashMap<>();
        }
    }
}
