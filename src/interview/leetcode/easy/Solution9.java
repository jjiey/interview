package interview.leetcode.easy;

/**
 * Palindrome Number
 * 回文数
 */
public class Solution9 {

    public static void main(String[] args) {
        Solution9 lc = new Solution9();
        int x = 123454321;
        System.out.println(lc.isPalindrome(x));
    }

    /**
     * 将数字转换为字符串，检查字符串是否为回文
     * 但是需要额外的非常量空间来创建问题描述中所不允许的字符串
     */
    private boolean isPalindrome(int x) {
        // 两种情况直接返回
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        String str = String.valueOf(x);
        int n = str.length();
        for (int i = 0; i < n / 2; i++) {
            // 对比字符串前后对称位置的字符是否相等
            if (str.charAt(i) != str.charAt(n - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 第二个想法是将数字本身反转，然后将反转后的数字与原始数字进行比较，如果它们是相同的，那么这个数字就是回文。
     * 但是，如果反转后的数字大于 Integer.MAX_VALUE，将遇到整数溢出问题。但是可以用 long 来处理
     */
    private boolean isPalindrome2(int x) {
        // 两种情况直接返回
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        int copyx = x;
        // 翻转之后的数字可能超过整型的范围，用 long 来处理
        long y = 0;
        while (copyx != 0) {
            y = y * 10 + copyx % 10;
            copyx /= 10;
        }
        return x == y;
    }

    /**
     * 反转一半数字
     * 按照第二个想法，为了避免数字反转可能导致的溢出问题，可以考虑只反转 int 数字的一半。毕竟，如果该数字是回文，其后半部分反转后应该与原始数字的前半部分相同
     */
    private boolean isPalindrome3(int x) {
        // 特殊情况：
        // (1) 当 x < 0 时，x 不是回文数
        // (2) 如果数字的最后一位是 0，为了使该数字为回文，则其第一位数字也应该是 0，只有 0 满足这一属性
        // 两种情况直接返回
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }

        // 当数字长度为奇数时，我们可以通过 revertedNumber / 10 去除处于中位的数字。
        // 例如，当输入为 12321 时，在 while 循环的末尾我们可以得到 x = 12，revertedNumber = 123，
        // 由于处于中位的数字不影响回文（它总是与自己相等），所以我们可以简单地将其去除。
        return x == revertedNumber || x == revertedNumber / 10;
    }
}
