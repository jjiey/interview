package interview.leetcode.hard;

/**
 * Edit Distance
 * 编辑距离
 */
public class Solution72 {

    public static void main(String[] args) {
        Solution72 lc = new Solution72();
        String word1 = "horse";
        String word2 = "ros";
        word1 = "intention";
        word2 = "execution";
        int res = lc.minDistance(word1, word2);
        System.out.println(res);
    }

    private int minDistance(String word1, String word2) {
        int w1Length = word1.length();
        int w2Length = word2.length();
        // if one of the strings is empty
        if (w1Length * w2Length == 0) {
            return w1Length + w2Length;
        }
        // init 因为单词的所有元素要放在a[m]里, 所以要+ 1 dp[0][0] - dp[w1Length][0]
        int[][] dp = new int[w1Length + 1][w2Length + 1];
        for (int i = 0; i < w1Length + 1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j < w2Length + 1; j++) {
            dp[0][j] = j;
        }
        // dp
        for (int i = 1; i < w1Length + 1; i++) {
            for (int j = 1; j < w2Length + 1; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                }
            }
        }
        return dp[w1Length][w2Length];
    }

}
