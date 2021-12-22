package interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

    private static Integer getContinuousFinishCount(List<Integer> ids) {
        List<Integer> countList = new ArrayList<>();
        int size = ids.size();
        int count = 0;
        int index = ids.get(0);
        for (int i = 0; i < ids.size(); i++) {
            Integer integer = ids.get(i);
            if (index == integer) {
                index++;
                count++;
                if (i == size - 1) {
                    countList.add(count);
                }
            } else {
                countList.add(count);
                count = 0;
                index = integer;
                i--;
            }
        }
        return countList.stream().mapToInt(s -> s).max().orElse(0);
    }

    private static Integer getContinuousFinishCount2(List<Integer> ids) {
        int max = 0, curMax = 0;
        for (int i = 0; i < ids.size() - 1; i++) {
            if (1 == ids.get(i + 1) - ids.get(i)) {
                curMax++;
            } else {
                max = Math.max(max, curMax);
                curMax = 0;
            }
        }
        return Math.max(max, curMax) + 1;
    }

    private static Integer getContinuousFinishCount3(List<Integer> ids) {
        int max = 1, temp = 0;
        for (int i = 1; i < ids.size(); i++) {
            if (1 != ids.get(i) - ids.get(i - 1)) {
                temp = i;
            } else {
                max = Math.max(max, i - temp + 1);
            }
        }
        return max;
    }

    // 674
    private static Integer getContinuousFinishCount4(int[] nums) {
        int ans = 0, anchor = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i - 1] >= nums[i]) {
                anchor = i;
            }
            ans = Math.max(ans, i - anchor + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(getContinuousFinishCount(Arrays.asList(1,2,3,4,5,6,7,8,9)));
        System.out.println(getContinuousFinishCount(Arrays.asList(9,8,7,6,5,4,3,2,1)));
        System.out.println(getContinuousFinishCount(Arrays.asList(11,22,33,44,55,66,77,88,99)));
        System.out.println(getContinuousFinishCount(Arrays.asList(11,12,33,44,55,56,57,88,99)));
        System.out.println("====================");
        System.out.println(getContinuousFinishCount2(Arrays.asList(1,2,3,4,5,6,7,8,9)));
        System.out.println(getContinuousFinishCount2(Arrays.asList(9,8,7,6,5,4,3,2,1)));
        System.out.println(getContinuousFinishCount2(Arrays.asList(11,22,33,44,55,66,77,88,99)));
        System.out.println(getContinuousFinishCount2(Arrays.asList(11,12,33,44,55,56,57,88,99)));
        System.out.println("====================");
        System.out.println(getContinuousFinishCount3(Arrays.asList(1,2,3,4,5,6,7,8,9)));
        System.out.println(getContinuousFinishCount3(Arrays.asList(9,8,7,6,5,4,3,2,1)));
        System.out.println(getContinuousFinishCount3(Arrays.asList(11,22,33,44,55,66,77,88,99)));
        System.out.println(getContinuousFinishCount3(Arrays.asList(11,12,33,44,55,56,57,88,99)));
        System.out.println("====================");
        System.out.println(getContinuousFinishCount4(new int[]{1,2,3,4,5,6,7,8,9}));
        System.out.println(getContinuousFinishCount4(new int[]{9,8,7,6,5,4,3,2,1}));
        System.out.println(getContinuousFinishCount4(new int[]{11,22,33,44,55,66,77,88,99}));
        System.out.println(getContinuousFinishCount4(new int[]{11,12,33,44,55,56,57,88,99}));
    }
}
