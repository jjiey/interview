package interview.leetcode.medium;

import java.util.Arrays;

/**
 * Counting Bits
 * 比特位计数
 */
public class Solution338 {

    public static void main(String[] args) {
        Solution338 lc = new Solution338();
        int num = 8;
        int[] res = lc.countBits2(num);
        System.out.println(Arrays.toString(res));
    }

    /**
     * 思路: 循环调用Solution191
     */
    private int[] countBits(int num) {
        int[] res = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            res[i] = hammingWeight(i);
        }
        return res;
    }

    /**
     * 位运算, x & (x - 1) 清零最低位的1
     */
    private int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count ++;
        }
        return count;
    }

    /**
     * 思路: 位运算
     * 去除最低位的数有多少个1
     * 1 & 0 = 0
     */
    private int[] countBits2(int num) {
        int[] res = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            res[i] = res[i & (i - 1)] + 1;
        }
        return res;
    }

}
