package interview.leetcode.medium;

public class Solution91 {

    public static void main(String[] args) {
        String s = "226";
        Solution91 lc = new Solution91();
        int res = lc.numDecodings(s);
        System.out.println(res);
    }

    private int numDecodings(String s) {
        char[] chars = s.toCharArray();
        return decode(chars, chars.length - 1);
    }

    /**
     * 从最后一个字符开始，不断地向前递归
     * 对于226而言
     *     当前字符6的count = 当前字符的前一个字符2的count
     *         如果前一个字符是1 或者 前一个字符是2且当前字符小于6，当前字符6的count = 当前字符的前一个字符2的count + 当前字符的前一个字符的前一个字符2的count
     */
    private int decode(char[] chars, int index) {
        if (index <= 0) {
            return 1;
        }
        int count = 0;
        char curr = chars[index];
        char prev = chars[index - 1];
        if (curr > '0') {
            count = decode(chars, index - 1);
        }
        if (prev == '1' || (prev == '2' && curr <= '6')) {
//            count = decode(chars, index - 1) + decode(chars, index - 2);
            count += decode(chars, index - 2);
        }
        return count;
    }

}
