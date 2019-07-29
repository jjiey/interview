package interview.sort;

import java.util.Arrays;

/**
 * 堆排序
 * 步骤：
 *     （1）把无需数组构建成二叉堆
 *         需要从小到大排序，则构建成最大堆
 *         需要从大到小排序，则构建成最小堆
 *     （2）循环删除堆顶元素，替换到二叉堆的末尾，调整堆产生新的堆顶
 * 空间复杂度：O(1)
 * 时间复杂度：O(N*logN)
 * 平均时间复杂度：O(N*logN)
 * 最坏时间复杂度：O(N*logN)
 *     堆的上浮和下沉操作时间复杂度O(logn)
 * 稳定性：不稳定
 */
public class HeapSort {

    /**
     * 堆排序
     * @param data 待排序数组
     */
    private static void heapSort(int[] data) {
        // 无序数组构建最大堆
        for (int i = (data.length - 2) / 2; i >= 0 ; i --) {
            downAdjust(data, i, data.length - 1);
        }
        System.out.println("最大堆为：" + Arrays.toString(data));
        // 循环删除堆顶元素，移到尾部，调整新的堆顶
        // 从最后一个节点开始，和堆顶元素交换，一直到第二个节点（第一个节点不需要交换）
        for (int i = data.length - 1; i > 0; i --) {
            // swap
            int temp = data[i];
            data[i] = data[0];
            data[0] = temp;
            // 调整新的堆顶
            downAdjust(data, 0, i);
        }
    }

    /**
     * 最大堆下沉调整
     * @param data 待调整的堆
     * @param parentIndex 要下沉的父节点
     * @param length 堆的有效大小
     */
    private static void downAdjust(int[] data, int parentIndex, int length) {
        // 保存父节点，用于最后赋值
        int temp = data[parentIndex];
        // 计算左孩子index
        int childIndex = 2 * parentIndex + 1;
        // 如果左孩子存在
        while (childIndex < length) {
            // 如果有右孩子，且右孩子大于左孩子的值，则定位到右孩子
            if (childIndex + 1 < length && data[childIndex + 1] > data[childIndex]) {
                childIndex ++;
            }
            // 如果父节点大于左右孩子中最大的值，则直接跳出
            if (temp >= data[childIndex]) {
                break;
            }
            // swap
            data[parentIndex] = data[childIndex];
            parentIndex = childIndex;
            childIndex = 2 * parentIndex + 1;
        }
        data[parentIndex] = temp;
    }

    public static void main(String[] args) {
        int[] data = {45, 28, 80, 90, 50, 16, 100, 10};
        heapSort(data);
        System.out.println(Arrays.toString(data));
    }

}
