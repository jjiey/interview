package interview.leetcode.medium;

import java.util.*;

/**
 * Letter Combinations of a Phone Number
 * 电话号码的字母组合
 *
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 */
public class Solution17 {

    private final List<String> output = new ArrayList<>();

    private final Map<Integer, String> phoneMap = new HashMap<Integer, String>() {{
        put(2, "abc");
        put(3, "def");
        put(4, "ghi");
        put(5, "jkl");
        put(6, "mno");
        put(7, "pqrs");
        put(8, "tuv");
        put(9, "wxyz");
    }};

    public static void main(String[] args) {
        Solution17 lc = new Solution17();
        String digits = "23";
        System.out.println(lc.letterCombinations(digits));
    }

    private List<String> letterCombinations(String digits) {
        if (Objects.nonNull(digits) && digits.length() > 0) {
            letterCombinations2(digits.toCharArray(), 0, "");
        }
        return output;
    }

    /**
     * 寻找和digits[index]匹配的字母，获得digits[0 - index]翻译得到的解
     * @param s 从digits[0 - (index - 1)]翻译得到的一个字母字符串
     */
    private void letterCombinations(char[] digits, int index, String s) {
        if (index == digits.length) {
            output.add(s);
            return;
        }
        for (char c : phoneMap.get(digits[index] - '0').toCharArray()) {
            letterCombinations(digits, index + 1, s + Character.toString(c));
        }
    }

    private void letterCombinations2(char[] digits, int index, String s) {
        System.out.println(index + " : " + s);
        if (index == digits.length) {
            output.add(s);
            System.out.println("get " + s + " , return");
            return;
        }
        for (char c : phoneMap.get(digits[index] - '0').toCharArray()) {
            System.out.println("digits[" + index + "] = " + digits[index] + " ，use " + c);
            letterCombinations2(digits, index + 1, s + Character.toString(c));
        }
        System.out.println("digits[" + index + "] = " + digits[index] + " complete, return");
    }
}
