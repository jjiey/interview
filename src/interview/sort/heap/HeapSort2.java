package interview.sort.heap;

import interview.sort.SortTestHelper;

import java.util.Arrays;

public class HeapSort2 {

    // 对整个arr数组使用HeapSort2排序
    // HeapSort2, 借助我们的heapify过程创建堆
    // 此时, 创建堆的过程时间复杂度为O(n), 将所有元素依次从堆中取出来, 实践复杂度为O(nlogn)
    // 堆排序的总体时间复杂度依然是O(nlogn), 但是比HeapSort1性能更优, 因为创建堆的性能更优
    private Integer[] sort(Integer[] data){
        int n = data.length;
        MaxHeap<Integer> maxHeap = new MaxHeap<>(data, n);
        for ( int i = n-1 ; i >= 0 ; i -- ) {
            data[i] = maxHeap.extractMax();
        }
        return data;
    }

    // 测试 HeapSort2
    public static void main(String[] args) {
//        int[] data = SortTestHelper.generateRandomArray(1000000, 0, 100000);
        Integer[] data = new Integer[]{5,9,4,1,6,4,8};
        new HeapSort2().sort(data);
//        System.out.println(SortTestHelper.isSorted(data));
        System.out.println(Arrays.toString(data));
    }
}
