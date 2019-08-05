package interview.leetcode.medium;

import java.util.*;

/**
 * 3Sum
 * 三数之和
 */
public class Solution15 {

    public static void main(String[] args) {
        Solution15 lc = new Solution15();
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        List<List<Integer>> res = lc.threeSum(nums);
        System.out.println(res.toString());
    }

    /**
     * 双指针法
     */
    private List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList();
        if(nums == null || nums.length < 3) {
            return res;
        }
        // 先对数组进行排序, 为了方便的过滤掉重复元素
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            // 如果当前数字大于0，因为已经排序, 所以三数之和一定大于0，所以提前结束循环
            if (nums[i] > 0) {
                break;
            }
            // 过滤掉重复元素
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            int L = i + 1;
            int R = nums.length - 1;
            while (L < R) {
                int sum = nums[i] + nums[L] + nums[R];
                if (sum < 0) {
                    L ++;
                } else if (sum > 0) {
                    R --;
                } else {
                    res.add(Arrays.asList(nums[i], nums[L], nums[R]));
                    // 去重
                    while (L < R && nums[L] == nums[L+1]) {
                        L ++;
                    }
                    // 去重
                    while (L < R && nums[R] == nums[R-1]) {
                        R --;
                    }
                    // 接着找
                    L ++;
                    R --;
                }
            }
        }
        return res;
    }

    /**
     * todo 没看懂map的骚操作
     */
    private List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> res = new ArrayList();
        if(nums == null || nums.length < 3){
            return res;
        }
        // 先对数组进行排序, 为了方便的过滤掉重复元素
        Arrays.sort(nums);
        Map<Integer,Integer> targetMap = new HashMap<>();
        for(int i = 0; i < nums.length - 1; i++){
            // 过滤掉重复元素
            if(i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            targetMap.clear();
            for (int j = i + 1; j < nums.length; j++) {
                if (targetMap.containsKey(nums[j])) {
                    if (targetMap.get(nums[j]) == 0) {
                        res.add(Arrays.asList(nums[i], nums[j], -nums[i] - nums[j]));
                        targetMap.put(nums[j], 1);
                    }
                } else {
                    targetMap.put(-nums[i] - nums[j], 0);
                }
            }
        }
        return res;
    }

}
