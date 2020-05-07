package interview.sort.heap;

import interview.sort.SortTestHelper;

/**
 * nlogn
 * 因为shift up和shift down每次都是logn
 */
public class HeapSort1 {

    // 对整个arr数组使用HeapSort1排序
    // HeapSort1, 将所有的元素依次添加到堆中, 在将所有元素从堆中依次取出来, 即完成了排序
    // 无论是创建堆的过程, 还是从堆中依次取出元素的过程, 时间复杂度均为O(nlogn)
    // 整个堆排序的整体时间复杂度为O(nlogn)
    private int[] sort(int[] data){
        int n = data.length;
        MaxHeap<Integer> maxHeap = new MaxHeap<>(n);
        for (int d : data) {
            maxHeap.insert(d);
        }
        for (int i = n-1 ; i >= 0 ; i --) {
            data[i] = maxHeap.extractMax();
        }
        return data;
    }

    // 测试 HeapSort1
    public static void main(String[] args) {
        int[] data = SortTestHelper.generateRandomArray(1000000, 0, 100000);
        new HeapSort1().sort(data);
        System.out.println(SortTestHelper.isSorted(data));
    }
}
