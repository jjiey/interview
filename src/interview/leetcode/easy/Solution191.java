package interview.leetcode.easy;

/**
 * Number of 1 Bits
 * 位1的个数
 */
public class Solution191 {

    public static void main(String[] args) {
        Solution191 lc = new Solution191();
        int n = 8;
        int res = lc.hammingWeight3(n);
        System.out.println(res);
    }

    // 超出时间限制
    private int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            if (n % 2 == 1) {
                count ++;
            }
            n = n >> 1;
        }
        return count;
    }

    /**
     * 使用java类库, 遍历结果
     */
    private int hammingWeight2(int n) {
        int count = 0;
        String binaryString = Integer.toBinaryString(n);
        for (int i = 0; i < binaryString.length(); i++) {
            if (binaryString.charAt(i) == '1') {
                count ++;
            }
        }
        return count;
    }

    /**
     * 位运算, x & (x - 1) 清零最低位的1
     */
    private int hammingWeight3(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count ++;
        }
        return count;
    }

}
