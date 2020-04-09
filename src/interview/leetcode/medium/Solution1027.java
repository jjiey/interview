package interview.leetcode.medium;

public class Solution1027 {

    public static void main(String[] args) {
        //int A[] = new int[]{3,6,9,12};
        //int A[] = new int[]{9,4,7,2,10};
        int A[] = new int[]{20,1,15,3,10,5,8};
        Solution1027 lc = new Solution1027();
        System.out.println(lc.longestArithSeqLength(A));
    }

    /*
    dp[i][diff] 表示【以第 i 个数开始，它前边的所有数以 diff 为偏移值构成的等差数列】的长度
     */
    private int longestArithSeqLength(int[] A) {
        int len = A.length;
        if (len <= 2) {
            return len;
        }
        int res = 0, diff;
        // 初始值
        int[][] dp = new int[len][20000];
        // 递推
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                // + 10000为了防止A[i] - A[j]为负数
                diff = A[i] - A[j] + 10000;
                // 如果dp[j][diff]已经有以diff为偏移值构成的等差数列存在
                if (dp[j][diff] > 0) {
                    dp[i][diff] = dp[j][diff] + 1;
                } else {
                    dp[i][diff] = 2;
                }
                res = Math.max(res, dp[i][diff]);
            }
        }
        return res;
    }
}
