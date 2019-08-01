package interview.sort;

import java.util.Arrays;

/**
 * 选择排序
 * 时间复杂度：
 * 最好：O(N)
 * 最坏：O(N^2)
 * 平均：O(N^2)
 * 空间复杂度：O(1)
 * 稳定性：不稳定
 */
public class SelectionSort {

    private static void selectionSort(int[] data) {
        int len = data.length;
        for (int i = 0; i < len; i++) {
            int minIndex = i; // 记录最小值的下标
            /* 找出最小值的下标 */
            for (int j = i + 1; j < len; j ++) {
                if (data[j] < data[minIndex]) {
                    minIndex = j;
                }
            }
            /* 如果最小值下标不是i，将最小值和下标为i的位置进行交换 */
            if (minIndex > i) {
                // swap
                int temp = data[i];
                data[i] = data[minIndex];
                data[minIndex] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] data = {4, 2, 3, 1};
        selectionSort(data);
        System.out.println(Arrays.toString(data));
    }

}
