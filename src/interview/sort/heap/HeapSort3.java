package interview.sort.heap;

import interview.sort.SortTestHelper;

/**
 * 优化
 */
public class HeapSort3 {

    private int[] sort(int[] data){
        int n = data.length;

        // heapify
        // 注意，此时我们的堆是从0开始索引的
        // 从(最后一个元素的索引-1)/2开始
        // 最后一个元素的索引 = n-1
        for( int i = (n-1-1)/2 ; i >= 0 ; i -- )
            shiftDown2(data, n, i);

        for( int i = n-1; i > 0 ; i-- ){
            swap(data, 0, i);
            shiftDown2(data, i, 0);
        }
        return data;
    }

    // 原始的shiftDown过程
    private static void shiftDown(int[] arr, int n, int k){
        // while (k节点有左孩子)
        while(2 * k + 1 < n){
            // 在此轮循环中，data[k]和data[j]交换位置
            int j = 2 * k + 1;
            // 有右孩子且 右孩子大于左孩子
            if (j + 1 < n && arr[j + 1] > arr[j]) {
                j ++;
            }
            // 父节点比孩子节点都大了
            if (arr[k] >= arr[j]) {
                break;
            }
            swap(arr, k, j);
            k = j;
        }
    }

    // 优化的shiftDown过程, 使用赋值的方式取代不断的swap,
    // 该优化思想和我们之前对插入排序进行优化的思路是一致的
    private static void shiftDown2(int[] arr, int n, int k){
        int e = arr[k];
        while(2 * k + 1 < n){
            int j = 2 * k + 1;
            if (j + 1 < n && arr[j + 1] > arr[j]) {
                j ++;
            }
            if (e >= arr[j]) {
                break;
            }
            arr[k] = arr[j];
            k = j;
        }

        arr[k] = e;
    }

    // 交换堆中索引为i和j的两个元素
    private static void swap(int[] arr, int i, int j){
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    // 测试 HeapSort
    public static void main(String[] args) {
        int[] data = SortTestHelper.generateRandomArray(1000000, 0, 100000);
        HeapSort3 sort = new HeapSort3();
        System.out.println(SortTestHelper.isSorted(sort.sort(data)));
    }
}
