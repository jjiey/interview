package interview.sort;

import java.util.Arrays;

/**
 * 选择排序
 * 时间复杂度：
 * 最好：O(N^2)
 * 最坏：O(N^2)
 * 平均：O(N^2)
 * 空间复杂度：O(1)
 * 稳定性：不稳定
 */
public class SelectionSort {

    /**
     * selectionSort每次选择最小值
     * @param data 待排序数组
     * @return 排序后的数组
     */
    private int[] selectionSort(int[] data) {
        int minIndex, temp;
        for (int i = 0; i < data.length - 1; i++) {
            // 记录最小值的下标
            minIndex = i;
            // 找出最小值的下标
            for (int j = i + 1; j < data.length; j ++) {
                if (data[j] < data[minIndex]) {
                    minIndex = j;
                }
            }
            // 如果最小值下标不是i，将最小值和下标为i的位置进行交换
            if (minIndex > i) {
                // swap
                temp = data[i];
                data[i] = data[minIndex];
                data[minIndex] = temp;
            }
        }
        return data;
    }

    /**
     * selectionSort每次选择最大值
     * @param data 待排序数组
     * @return 排序后的数组
     */
    private int[] selectionSort2(int[] data) {
        int maxIndex, temp;
        for (int i = data.length - 1; i >= 0; i--) {
            maxIndex = i;
            for (int j = 0; j < i; j++) {
                if (data[j] > data[maxIndex]) {
                    maxIndex = j;
                }
            }
            if (maxIndex < i) {
                // swap
                temp = data[i];
                data[i] = data[maxIndex];
                data[maxIndex] = temp;
            }
        }
        return data;
    }

    /**
     * selectionSort每次选择最大值和最小值
     * @param data 待排序数组
     * @return 排序后的数组
     */
    private int[] selectionSort3(int[] data) {
        int minIndex, maxIndex, temp;
        for (int left = 0, right = data.length - 1; left < right; left++, right--) {
            minIndex = left;
            maxIndex = right;
            for (int i = left; i <= right; i++) {
                if (data[i] < data[minIndex]) {
                    minIndex = i;
                }
                if (data[i] > data[maxIndex]) {
                    maxIndex = i;
                }
            }
            if (minIndex > left) {
                // swap left and minIndex first
                temp = data[left];
                data[left] = data[minIndex];
                data[minIndex] = temp;
                // 因为先交换最小值的位置，所以需要考虑最大值在 left 位置的情况
                // 如果最大值在 left 位置，被交换后，最大值的位置为 minIndex
                if (left == maxIndex) {
                    maxIndex = minIndex;
                }
            }
            if (maxIndex < right) {
                // swap right and maxIndex
                temp = data[right];
                data[right] = data[maxIndex];
                data[maxIndex] = temp;
            }
        }
        return data;
    }

    public static void main(String[] args) {
        int[] data = SortTestHelper.generateRandomArray(10, 10, 20);
        System.out.println("待排序数组：" + Arrays.toString(data));
        SelectionSort sort = new SelectionSort();
//        System.out.println(Arrays.toString(sort.selectionSort(data)));
//        System.out.println(Arrays.toString(sort.selectionSort2(data)));
        System.out.println(Arrays.toString(sort.selectionSort3(data)));
    }

    /*==========其他写法*/

    private void sort(Comparable[] arr){
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int minIndex = left;
            int maxIndex = right;

            // 在每一轮查找时, 要保证arr[minIndex] <= arr[maxIndex]
            if (arr[minIndex].compareTo(arr[maxIndex]) > 0) {
                swap(arr, minIndex, maxIndex);
            }

            for (int i = left + 1 ; i < right; i ++) {
                if (arr[i].compareTo(arr[minIndex]) < 0) {
                    minIndex = i;
                } else if (arr[i].compareTo(arr[maxIndex]) > 0) {
                    maxIndex = i;
                }
            }

            swap(arr, left, minIndex);
            swap(arr, right, maxIndex);

            left ++;
            right --;
        }
    }

    private void swap(Object[] arr, int i, int j) {
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
