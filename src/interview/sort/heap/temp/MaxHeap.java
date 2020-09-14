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
            System.out.print(heap.getMax() + ", ");
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
        this(data, data.length);
    }

    public MaxHeap(int[] data, int len) {
        this.data = new int[len];
        this.capacity = len;
        this.count = len;
        System.arraycopy(data, 0, this.data, 0, data.length);
        // 从最后一个非叶子节点开始 shiftDown
        for (int i = (len - 2) / 2; i >= 0; i--) {
            shiftDown2(i);
        }
    }

    /**
     * 返回堆中的元素个数
     * @return 堆中的元素个数
     */
    public int size() {
        return count;
    }

    /**
     * 返回一个布尔值, 表示堆中是否为空
     * @return true 表示堆为空
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * 堆中插入一个元素
     * @param i 插入的元素
     */
    public void insert(int i) {
        // todo 此处可以实现为动态扩容，会更友好些，大家可以自己去实现
        assert this.count < this.capacity;
        // 插入位置为堆的最后一个位置
        this.data[this.count] = i;
        // 对新插入节点做 shiftUp
        shiftUp2(this.count);
        this.count++;
    }

    private void shiftUp(int i) {
        // i 父节点的索引
        int ip;
        // i 不是根节点 && i 的父节点的值 < i 节点的值
        while (i > 0 && this.data[ip = (i - 1) / 2] < this.data[i]) {
            swap(i, ip);
            i = ip;
        }
    }

    // 优化 shiftUp，不一直swap
    private void shiftUp2(int i) {
        int e = this.data[i];
        // i 父节点的索引
        int ip;
        // i 不是根节点 && i 的父节点的值 < i 节点的值
        while (i > 0 && this.data[ip = (i - 1) / 2] < e) {
            this.data[ip] = this.data[i];
            i = ip;
        }
        this.data[i] = e;
    }

    /**
     * 获取堆中的最大值（从堆中删除一个元素）
     * @return 堆中最大值
     */
    public int getMax() {
        // todo 此处可以实现为动态扩容，会更友好些，大家可以自己去实现
        assert this.count >= 0;
        int ret = this.data[0];
        // 把堆中的最后一个节点临时补到堆顶
        swap(0, this.count - 1);
        this.count --;
        // 对新的堆顶节点做 shiftDown
        shiftDown2(0);
        return ret;
    }

    private void shiftDown(int i) {
        // il 代表 i 节点的左孩子节点索引；ir 代表 i 节点的右孩子节点索引
        int il, ir;
        // while (i 节点有左孩子)
        while ((il = 2 * i + 1) < this.count) {
            // j 是 i 节点的两个孩子节点中最大值的索引，初始值为左孩子的索引
            int j = il;
            // 如果 i 节点还有右孩子 且 右孩子的值大于左孩子的值，j 更新为右孩子的索引
            if ((ir = j + 1) < this.count && this.data[ir] > this.data[j]) {
                j = ir;
            }
            // 此时 data[j] 是 i 节点的两个孩子节点中的最大值
            // 如果 i 节点的值大于等于 data[j]，结束循环
            if(this.data[i] >= this.data[j]) {
                break;
            }
            swap(i, j);
            // 为下次循环做准备
            i = j;
        }
    }

    // 优化 shiftDown，不一直swap
    private void shiftDown2(int i) {
        int e = this.data[i];
        int il, ir;
        while ((il = 2 * i + 1) < this.count) {
            int j = il;
            if ((ir = j + 1) < this.count && this.data[ir] > this.data[j]) {
                j = ir;
            }
            if(e >= this.data[j]) {
                break;
            }
            this.data[i] = this.data[j];
            i = j;
        }
        this.data[i] = e;
    }

    /**
     * 交换堆数组中 i 和 j 索引位置的值
     * @param i 待交换索引
     * @param j 待交换索引
     */
    private void swap(int i, int j) {
        int temp = this.data[i];
        this.data[i] = this.data[j];
        this.data[j] = temp;
    }
}
