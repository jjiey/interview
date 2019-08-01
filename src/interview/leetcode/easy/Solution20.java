package interview.leetcode.easy;

import java.util.*;

/**
 * Valid Parentheses
 * 有效的括号
 */
public class Solution20 {

    public static void main(String[] args) {
        Solution20 lc = new Solution20();
        String s = "{{{}}}";
        boolean res = lc.isValid(s);
        System.out.println(res);
    }

    private boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> parenMap = new HashMap<Character, Character>() {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};
        Set<Character> parenMapKeys = parenMap.keySet();
        for(int i = 0 ; i < s.length() ; i++){
            char c = s.charAt(i);
            // 说明是左半部分直接入栈, 否则进行判断
            if (!parenMapKeys.contains(c)) {
                stack.push(c);
            } else if (stack.isEmpty() || parenMap.get(c) != stack.pop()) {
                return false;
            }
        }
        // 如果s是"["
        return stack.empty();
    }

    /**
     * 时间复杂度不好判断，平均情况下是 (n * n) / 2, 时间复杂度比用栈差一点
     */
    private boolean isValid2(String s) {
        int length;
        do {
            length = s.length();
            s = s.replace("()", "").replace("{}", "").replace("[]", "");
        } while (length != s.length());
        return s.length() == 0;
    }

}
