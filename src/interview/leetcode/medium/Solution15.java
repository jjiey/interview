package interview.leetcode.medium;

import java.util.*;

/**
 * 3Sum
 * 三数之和
 * TODO
 */
public class Solution15 {

    public static void main(String[] args) {
        Solution15 lc = new Solution15();
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        List<List<Integer>> res = lc.threeSum(nums);
        for (List<Integer> re : res) {
            System.out.println(re.toString());
        }
    }

//    private List<List<Integer>> threeSum(int[] nums) {
//
//    }

    public List<List<Integer>> threeSum(int[] nums) {
        if(nums == null || nums.length == 0){
            return new ArrayList();
        }
        List<List<Integer>> resultList = new ArrayList();
        // 要先对数组进行排序
        Arrays.sort(nums);
        for(int i = 0; i < nums.length - 1; i++){
            // 重复的过滤掉
            if(i > 0 && nums[i] == nums[i-1])
                continue;
            Map<Integer,Integer> targetMap = new HashMap<>();
            for(int j = i + 1; j < nums.length; j++){
                if(targetMap.containsKey(nums[j])){
                    if(targetMap.get(nums[j]) == 0){
                        resultList.add(Arrays.asList(nums[i], nums[j], -nums[i] - nums[j]));
                        targetMap.put(nums[j], 1);
                    }
                }else{
                    targetMap.put(-nums[i] - nums[j], 0);
                }
            }
        }
        return resultList;
    }

}
