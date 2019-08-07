package interview.leetcode.common;

public class TreeNodeUtil {

    public static ListNode createTree(int[] num) {

        return null;
    }

    public static void main(String[] args) {
        int[] num = new int[]{1,2,3,4};
        int level = 0;
        int temp = 1;
        int len = num.length;
        while (len > 0) {
            level ++;
            len = len - temp;
            temp <<= 1;
        }
        for (int i = 2; i <= level; i++) {
            int left = 2 ^ (i - 1);
            int right = 2 * left;

        }
    }

}
