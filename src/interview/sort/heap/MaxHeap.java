package interview.sort.heap;

public class MaxHeap<T extends Comparable> {

    public static void main(String[] args) {
        MaxHeap<Integer> maxHeap = new MaxHeap<>(100);
        int N = 100; // 堆中元素个数
        int M = 100; // 堆中元素取值范围[0, M)
        for( int i = 0 ; i < N ; i ++ )
            maxHeap.insert( new Integer((int)(Math.random() * M)) );

        Integer[] arr = new Integer[N];
        // 将maxheap中的数据逐渐使用extractMax取出来
        // 取出来的顺序应该是按照从大到小的顺序取出来的
        for( int i = 0 ; i < N ; i ++ ){
            arr[i] = maxHeap.extractMax();
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        // 确保arr数组是从大到小排列的
        for( int i = 1 ; i < N ; i ++ )
            assert arr[i-1] >= arr[i];
    }

    private T[] data;

    private int count;

    private int capacity;

    // 构造函数, 构造一个空堆, 可容纳capacity个元素
    public MaxHeap(int capacity){
        this.data = (T[]) new Comparable[capacity + 1];
        this.count = 0;
        this.capacity = capacity;
    }

    // 比较不同的建堆效果引起的性能差异
    // 这个过程叫做Heapify。叶子节点本身就是最大堆，第一个非叶子节点索引是 n / 2
    // 构造函数, 通过一个给定数组创建一个最大堆
    // 该构造堆的过程, 时间复杂度为O(n)
    public MaxHeap(T arr[]){
        this(arr, arr.length);
    }

    public MaxHeap(T arr[], int n){
        this.data = (T[]) new Comparable[n+1];
        this.capacity = n;

        // 0索引没有值
        System.arraycopy(arr, 0, data, 1, n);
        this.count = n;

        // 从非叶子节点开始，shiftDown
        for (int i = count/2 ; i >= 1 ; i --) {
            shiftDown(i);
        }
    }

    // 返回堆中的元素个数
    public int size(){
        return count;
    }

    // 返回一个布尔值, 表示堆中是否为空
    public boolean isEmpty(){
        return count == 0;
    }

    // 获取最大堆中的堆顶元素
    public T getMax(){
        assert( count > 0 );
        return data[1];
    }

    // shift up（）上浮 往最大堆中添加一个元素
    // 思路：就是往数组中最后一个位置添加了一个元素。将新加入的元素调整到一个合适的位置，使二叉树依然保持最大堆的性质。和父亲节点比，如果比父亲几点大，交换位置，以此类推
    // 像最大堆中插入一个新的元素 item
    public void insert(T t){
        // todo 动态扩容
        assert count + 1 <= capacity;
        data[count + 1] = t;
        count ++;
        shiftUp(count);
    }

    // todo 优化 不一直swap
    //********************
    //* 最大堆核心辅助函数
    //********************
    // 向堆中插入一个元素
    private void shiftUp(int k){
        // k > 1 表示k不是根节点
        while(k > 1 && data[k / 2].compareTo(data[k]) < 0){
            swap(k, k / 2);
            k /= 2;
        }
    }

    // 从最大堆中取出堆顶元素, 即堆中所存储的最大数据
    public T extractMax(){
        assert count > 0;
        T ret = data[1];

        swap(1 , count);
        count --;
        shiftDown(1);

        return ret;
    }

    // todo 优化 不一直swap
    // 只能取出根节点（堆顶）元素，然后把堆中的最后一个元素放到堆顶，然后shift Down，左右孩子比较，谁大就跟谁换，以此类推
    // 从堆中取出一个元素
    private void shiftDown(int k) {
        // while (k节点有左孩子)
        while (2 * k <= count) {
            // 在此轮循环中，data[k]和data[j]交换位置
            int j = 2 * k;
            // 有右孩子且 右孩子大于左孩子
            if (j + 1 <= count && data[j + 1].compareTo(data[j]) > 0) {
                j++;
            }
            // data[j] 是 data[2*k]和data[2*k+1]中的最大值，即父节点比孩子节点都大了
            if(data[k].compareTo(data[j]) >= 0) {
                break;
            }
            swap(k, j);
            // 为下次循环做准备
            k = j;
        }
    }

    // 交换堆中索引为i和j的两个元素
    private void swap(int i, int j){
        T t = data[i];
        data[i] = data[j];
        data[j] = t;
    }
}
