package interview.sort;

import java.util.Arrays;

/**
 * 插入排序
 * 时间复杂度：
 * 最好：O(N)
 * 最坏：O(N^2)
 * 平均：O(N^2)
 * 空间复杂度：O(1)
 * 稳定性：稳定
 */
public class InsertionSort {

	/**
	 * 插入排序就是step = 1的希尔排序
	 * @param data 待排序数组
	 * @return 排序后的数组
	 */
	private int[] insertionSort(int[] data) {
		int temp;
		// 从第二个值开始一直到最后一个值，每次插入新的数进来
		for (int i = 1; i < data.length; i++) {
			// j表示的是已经排好序的序列，从后往前遍历寻找插入位置（这里j > 0写成j-1 >= 0就和希尔排序写法一致了）
			for (int j = i; j > 0; j--) {
				if(data[j] < data[j - 1]) {
					// swap
					temp = data[j];
					data[j] = data[j - 1];
					data[j - 1] = temp;
				} else {
					break;
				}
			}
		}
		return data;
	}

	/**
	 * 简化1的写法
	 */
	private int[] insertionSort2(int[] data) {
		int temp;
		// 从第二个值开始一直到最后一个值，每次插入新的数进来
		for (int i = 1; i < data.length; i++) {
			// j表示的是已经排好序的序列，从后往前遍历寻找插入位置（这里j > 0写成j-1 >= 0就和希尔排序写法一致了）
			for (int j = i; j > 0 && data[j] < data[j - 1]; j--) {
				// swap
				temp = data[j];
				data[j] = data[j - 1];
				data[j - 1] = temp;
			}
		}
		return data;
	}

	/**
	 * 优化
	 * @param data 待排序数组
	 * @return 排序后的数组
	 */
	private int[] insertionSort3(int[] data) {
		// j为元素e应该插入的位置
		int e, j;
		for (int i = 1; i < data.length; i++) {
			// 寻找e合适的插入位置
			e = data[i];
			for (j = i; j > 0 && data[j - 1] > e; j--) {
				data[j] = data[j - 1];
			}
			data[j] = e;
		}
		return data;
	}

	/**
	 * 二分查找插入优化
	 * @param data 待排序数组
	 * @return 排序后的数组
	 */
	private int[] insertionSort4(int[] data) {
		int e, left, right, mid;
		for (int i = 1; i < data.length; i++) {
			e = data[i];
			left = 0;
			right = i - 1;
			while (left <= right) {
				// 这里计算 mid 没有用 (left + right) / 2 是为了防止溢出，这里也是以前jdk底层实现二分查找的一个bug，读者可以体会一下
				mid = left + ((right - left) >> 1);
				if (data[mid] > e) {
					right = mid - 1;
				} else {
					// 元素相同时，也插入在后面的位置
					left = mid + 1;
				}
			}
			// 将插入点以后的所有元素都后移一位
//			for (int j = i - 1; j >= left; j--) {
//				data[j + 1] = data[j];
//			}
			System.arraycopy(data, left, data, left + 1, i - left);
			// 插入元素
			data[left] = e;
		}
		return data;
	}

	public static void main(String[] args) {
		int[] data = SortTestHelper.generateRandomArray(10, 10, 20);
		System.out.println("待排序数组：" + Arrays.toString(data));
		InsertionSort sort = new InsertionSort();
		System.out.println(Arrays.toString(sort.insertionSort4(data)));
	}

	// 对arr[l...r]的区间使用InsertionSort排序
	public static void insertSort(int[] data, int l, int r){
		for( int i = l + 1 ; i <= r ; i ++ ){
			int e = data[i];
			int j = i;
			for (; j > l && data[j-1] > e; j--) {
				data[j] = data[j - 1];
			}
			data[j] = e;
		}
	}
}
// 如何不引入第三个变量去交换ab的值
// 思路：: 只要用到加减就可以。也是一个同花顺的面试考题
// a = 2, b = 3
// a = a + b; => a = 5
// b = a - b; => b = 5 - 3 = 2
// a = a - b; => a = 5 - 2 = 3
