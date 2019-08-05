package interview.leetcode.easy;

/**
 * Power of Two
 * 2的幂
 * 给定一个整数，判断它是否是 2 的幂次方。
 */
public class Solution231 {

    public static void main(String[] args) {
        Solution231 lc = new Solution231();
        int n = 536870912;
        boolean res = lc.isPowerOfTwo2(n);
        System.out.println(res);
    }

    /**
     * 思路: 位运算
     * 其他思路: 1.不断mod 2, 肯定会超出时间限制
     */
    private boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    /**
     * 内置库函数log2, 然后判断是否是整数 TODO 越写越恶心,不太靠谱吧,下边这个不对
     */
    private boolean isPowerOfTwo2(int n) {
        String str = new Double(Math.log(n) / Math.log(2)).toString();
        str = str.substring(str.indexOf(".") + 1);
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != '0') {
                return false;
            }
        }
        return true;
    }

}
