package interview.knapsack01;

/**
 * 0-1背包问题
 */
public class Knapsack01 {

    public static void main(String[] args) {
        Knapsack01 k = new Knapsack01();

    }

    /**
     * @param weights 每一件物品的重量
     * @param values 每一件物品的价值
     * @param capacity 背包容量
     */
    private int knapsack01(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        if (n == 0) {
            return n;
        }
        return knapsack01Helper(weights, values, n - 1, capacity);
    }

    /**
     * 用[0 - index]的物品填充容积为c的背包的最大价值
     * @param index 考虑用到的那个物品的序列号
     *
     * 两种情况：
     * 1.不要第index个物品，用[0 - (index - 1)]的物品填充容积为c的背包的最大价值
     * 2.要第index个物品，用[0 - (index - 1)]的物品填充容积为c - weights[index]的背包的最大价值
     * 返回两者的最大值
     */
    private int knapsack01Helper(int[] weights, int[] values, int index, int capacity) {
        // 物品结合为空集 或 背包容量满了
        if (index < 0 || capacity <= 0) {
            return 0;
        }
        // 用[0 - (index - 1)]的物品填充容积为c的背包的最大价值
        int bestValue = knapsack01Helper(weights, values, capacity, index - 1);
        // 如果背包里还装的下第index的物品时
        if (capacity >= weights[index]) {
            bestValue = Math.max(bestValue, values[index] + knapsack01Helper(weights, values, capacity - weights[index], index - 1));
        }
        return bestValue;
    }

    /* ==========记忆化搜索 */

    /**
     * @param weights 每一件物品的重量
     * @param values 每一件物品的价值
     * @param capacity 背包容量
     */
    private int knapsack012(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        if (n == 0) {
            return n;
        }
        // memo[i, j]用[0 - j]个物品填充容积为i的背包的最大价值，初始值都为-1
        int[][] memo = new int[n][];
        for (int i = 0; i < memo.length; i++) {
            memo[i] = new int[capacity + 1];
            for (int j = 0; j < memo[i].length; j++) {
                memo[i][j] = -1;
            }
        }
        return knapsack01Helper2(weights, values, n - 1, capacity, memo);
    }

    /**
     * 用[0 - index]的物品填充容积为c的背包的最大价值
     * @param index 考虑用到的那个物品的序列号
     *
     * 两种情况：
     * 1.不要第index个物品，用[0 - (index - 1)]的物品填充容积为c的背包的最大价值
     * 2.要第index个物品，用[0 - (index - 1)]的物品填充容积为c - weights[index]的背包的最大价值
     * 返回两者的最大值
     */
    private int knapsack01Helper2(int[] weights, int[] values, int index, int capacity, int[][] memo) {
        // 物品结合为空集 或 背包容量满了
        if (index < 0 || capacity <= 0) {
            return 0;
        }
        if (memo[capacity][index] != -1) {
            return memo[index][capacity];
        }
        // 用[0 - (index - 1)]的物品填充容积为c的背包的最大价值
        int bestValue = knapsack01Helper2(weights, values, capacity, index - 1, memo);
        // 如果背包里还装的下第index的物品时
        if (capacity >= weights[index]) {
            bestValue = Math.max(bestValue, values[index] + knapsack01Helper2(weights, values, capacity - weights[index], index - 1, memo));
        }
        memo[index][capacity] = bestValue;
        return bestValue;
    }

    /* ==========动态规划 */

    /**
     * memo[i][j]为考虑[0 - i]个物品，容积为j的背包，获得到最大值
     */
    private int knapsack013(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        if (n == 0) {
            return n;
        }
        // memo[i, j]用[0 - j]个物品填充容积为i的背包的最大价值
        int[][] memo = new int[n][capacity + 1];
        // base case
        for (int i = 1; i <= capacity; i++) {
            memo[0][i] = values[0];
        }
        // 递推
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= capacity; j++) {
                // 情况一：不要第index个物品
                memo[i][j] = memo[i - 1][j];
                // 情况二：要第index个物品，先判断背包容量是否足够
                if (j >= weights[i]) {
                    memo[i][j] = Math.max(memo[i][j], values[i] + memo[i - 1][j - weights[i]]);
                }
            }
        }
        return memo[n - 1][capacity];
    }

    /* ========== */

    /**
     * 优化knapsack013空间
     */
    private int knapsack014(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        if (n == 0) {
            return n;
        }
        // memo[i, j]用[0 - j]个物品填充容积为i的背包的最大价值
        int[][] memo = new int[2][capacity + 1];
        // base case
        for (int i = 1; i <= capacity; i++) {
            memo[0][i] = values[0];
        }
        // 递推
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= capacity; j++) {
                // 情况一：不要第index个物品
                memo[i % 2][j] = memo[(i - 1) % 2][j];
                // 情况二：要第index个物品，先判断背包容量是否足够
                if (j >= weights[i]) {
                    memo[i % 2][j] = Math.max(memo[i % 2][j], values[i] + memo[(i - 1) % 2][j - weights[i]]);
                }
            }
        }
        return memo[(n - 1) % 2][capacity];
    }

    /* ========== */

    /**
     * 优化knapsack014空间
     */
    private int knapsack015(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        if (n == 0) {
            return n;
        }
        // memo[i, j]用[0 - j]个物品填充容积为i的背包的最大价值
        int[] memo = new int[capacity + 1];
        // base case
        for (int i = 1; i <= capacity; i++) {
            memo[i] = values[0];
        }
        // 递推
        for (int i = 1; i < n; i++) {
            for (int j = capacity; j >= weights[i]; j--) {
                memo[j] = Math.max(memo[j], values[i] + memo[j - weights[i]]);
            }
        }
        return memo[capacity];
    }
}
