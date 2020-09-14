package interview.sort.heap.temp;

import interview.sort.SortTestHelper;

public class HeapSort {

    /**
     * 堆排序
     * @param data 待排序数组
     * @return 有序数组
     */
    private int[] heapSort(int[] data) {
        int len = data.length;
        // 构建一个最大堆
        MaxHeap heap = new MaxHeap(len);
        for (int d : data) {
            heap.insert(d);
        }
        // 按顺序复制回原数组
        for (int i = len - 1; i >= 0; i--) {
            data[i] = heap.getMax();
        }
        return data;
    }

    /**
     * 堆排序
     * @param data 待排序数组
     * @return 有序数组
     */
    private int[] heapSort2(int[] data) {
        int len = data.length;
        // 构建一个最大堆
        MaxHeap heap = new MaxHeap(data, len);
        // 按顺序复制回原数组
        for (int i = len - 1; i >= 0; i--) {
            data[i] = heap.getMax();
        }
        return data;
    }

//    private int[] heapSort3(int[] data) {
//        int len = data.length;
//        for (int i = (len - 2) / 2; i >= 0; i--) {
//            shiftDown2(data, len, i);
//        }
//        for (int i = len - 1; i > 0; i--) {
//            swap(data, 0, i);
//            shiftDown2(data, i, 0);
//        }
//        return data;
//    }
    /**
     * 堆排序
     * @param data 待排序数组
     * @return 有序数组
     */
    private int[] heapSort3(int[] data) {
        int len = data.length, temp;
        for (int i = (len - 2) / 2; i >= 0; i--) {
            shiftDown2(data, len, i);
        }
        for (int i = len - 1; i > 0; i--) {
            // swap 0 and i
            temp = data[0];
            data[0] = data[i];
            data[i] = temp;
            shiftDown2(data, i, 0);
        }
        return data;
    }

//    private void shiftDown(int[] data, int len, int i) {
//        int il, ir;
//        while ((il = 2 * i + 1) < len) {
//            int j = il;
//            if ((ir = j + 1) < len && data[ir] > data[j]) {
//                j = ir;
//            }
//            if (data[i] >= data[j]) {
//                break;
//            }
//            swap(data, i, j);
//            i = j;
//        }
//    }
    private void shiftDown(int[] data, int len, int i) {
        int il, ir, temp;
        while ((il = 2 * i + 1) < len) {
            int j = il;
            if ((ir = j + 1) < len && data[ir] > data[j]) {
                j = ir;
            }
            if (data[i] >= data[j]) {
                break;
            }
            // swap i and j
            temp = data[i];
            data[i] = data[j];
            data[j] = temp;
            i = j;
        }
    }

    private void shiftDown2(int[] data, int len, int i) {
        int e = data[i];
        int il, ir;
        while ((il = 2 * i + 1) < len) {
            int j = il;
            if ((ir = j + 1) < len && data[ir] > data[j]) {
                j = ir;
            }
            if (e >= data[j]) {
                break;
            }
            data[i] = data[j];
            i = j;
        }
        data[i] = e;
    }

    public static void main(String[] args) {
        int[] data = SortTestHelper.generateRandomArray(10000, 0, 100000);
        HeapSort sort = new HeapSort();
        System.out.println(SortTestHelper.isSorted(sort.heapSort3(data)));
    }

    private void swap(int[] data, int i, int j) {
        int t = data[i];
        data[i] = data[j];
        data[j] = t;
    }
}
