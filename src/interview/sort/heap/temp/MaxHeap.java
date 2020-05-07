package interview.sort.heap.temp;

import interview.sort.SortTestHelper;

import java.util.Arrays;

/**
 * 二叉堆
 */
public class MaxHeap {

    public static void main(String[] args) {
        int[] data = SortTestHelper.generateRandomArray(20, 0, 100);
        System.out.println(Arrays.toString(data));
        MaxHeap heap = new MaxHeap(data.length);
        for (int i : data) {
            heap.insert(i);
        }
        /**/
//        MaxHeap heap = new MaxHeap(data);
        for (int i : data) {
            System.out.print(heap.delete() + ", ");
        }
    }

    /**
     * 二叉堆中存储的元素
     */
    private int[] data;

    /**
     * 二叉堆中存储的元素数量
     */
    private int count;

    /**
     * 二叉堆容量
     */
    private int capacity;

    public MaxHeap(int capacity) {
        this.data = new int[capacity];
        this.count = 0;
        this.capacity = capacity;
    }

    public MaxHeap(int[] data) {
        int len = data.length;
        this.data = new int[len];
        System.arraycopy(data, 0, this.data, 0, data.length);
        this.capacity = len;
        this.count = len;
        // 从最后一个非叶子节点开始 shiftDown
        for (int i = (len - 2) / 2; i >= 0; i--) {
            shiftDown(i);
        }
    }

    public void insert(int t) {
        // todo 此处可以实现为动态扩容，会更友好些，大家可以自己去实现
        assert count <= capacity;
        data[count] = t;
        shiftUp(count);
        count++;
    }

    // todo 优化 不一直swap
    private void shiftUp(int i) {
        // i 父节点的索引
        int ip;
        // i > 0 表示 i 不是根节点 && i 的父节点的值 < i 的值
        while (i > 0 && data[ip = (i - 1) / 2] < data[i]) {
            swap(ip, i);
            i = ip;
        }
    }

    public int delete(){
        // todo 此处可以实现为动态扩容，会更友好些，大家可以自己去实现
        assert count >= 0;
        int ret = data[0];
        swap(0, count - 1);
        count --;
        shiftDown(0);
        return ret;
    }

    // todo 优化 不一直swap
    private void shiftDown(int i) {
        // while (k节点有左孩子)
        while (2 * i + 1 <= count) {
            // 在此轮循环中，data[k]和data[j]交换位置
            int j = 2 * i + 1;
            // 有右孩子且 右孩子大于左孩子
            if (j + 1 < count && data[j + 1] > data[j]) {
                j++;
            }
            // data[j] 是孩子节点中的最大值，即父节点比孩子节点都大了
            if(data[i] > data[j]) {
                break;
            }
            swap(i, j);
            // 为下次循环做准备
            i = j;
        }
    }

    private void swap(int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }
}
