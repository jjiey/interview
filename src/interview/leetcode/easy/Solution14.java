package interview.leetcode.easy;

/**
 * Longest Common Prefix
 * 最长公共前缀
 */
public class Solution14 {

    public static void main(String[] args) {
        Solution14 lc = new Solution14();
        String[] strs = new String[]{"flower","flow","flight"};
        System.out.println(lc.longestCommonPrefix(strs));
    }

    private String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        // 最长子串的长度一定不会超过第 0 个字符串的长度
        int end = strs[0].length();
        for (int i = 1; i < strs.length; i++) {
            int j = 0;
            while (j < end && j < strs[i].length() && strs[0].charAt(j) == strs[i].charAt(j)) {
                j++;
            }
            end = j;
        }
        return strs[0].substring(0,end);
    }

    private String longestCommonPrefix2(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        for (int i = 0; i < strs[0].length(); i++) {
            // 第 0 个字符不需要比较，可以直接从第 1 个字符开始进比较
            for (int j = 1; j < strs.length; j++) {
                // 只要strs中存在比当前长度i更短的string，立刻返回上一轮LCP，即strs[0][:i]
                // 只要strs中存在当前index字符与LCP该index不相同的字符串，立刻返回上一轮LCP，即strs[0][:i]
                if (i >= strs[j].length() || strs[0].charAt(i) != strs[j].charAt(i)) {
                    return strs[0].substring(0, i);
                }
            }
        }
        // 如果一直没返回，说明strs[0]本身就是LCP，返回它
        return strs[0];
    }
}
