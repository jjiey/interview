package interview.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 * 时间复杂度：
 * 最好：O(N)
 * 最坏：O(N^2)
 * 平均：O(N^2)
 * 空间复杂度：O(1)
 * 稳定性：稳定
 */
public class BubbleSort {

	/**
	 * bubbleSort
	 * @param data 待排序数组
	 * @return 排序后的数组
	 */
	private int[] bubbleSort(int[] data) {
		int temp;
		for (int i = 0; i < data.length - 1; i++) {
			for (int j = 0; j < data.length - 1 - i; j++) {
				if (data[j] > data[j + 1]) {
					// swap
					temp = data[j];
					data[j] = data[j + 1];
					data[j + 1] = temp;
				}
			}
			System.out.println("第 " + (i + 1) + " 轮结束：" + Arrays.toString(data));
		}
		return data;
	}

	/**
	 * bubbleSort优化
	 * @param data 待排序数组
	 * @return 排序后的数组
	 * 如果某一轮已经有序，则提前结束
	 */
	private int[] bubbleSort1(int[] data) {
		int temp;
		boolean isSorted;
		for (int i = 0; i < data.length - 1; i++) {
			// 有序标记，每一轮的初始值都是true
			isSorted = true;
			for (int j = 0; j < data.length - 1 - i; j++) {
				if (data[j] > data[j + 1]) {
					// swap
					temp = data[j];
					data[j] = data[j + 1];
					data[j + 1] = temp;
					// 因为有元素进行交换，所以不是有序的，标记变为false
					isSorted = false;
				}
			}
			System.out.println("第 " + (i + 1) + " 轮结束：" + Arrays.toString(data));
			if (isSorted) {
				break;
			}
		}
		return data;
	}

	/**
	 * bubbleSort优化
	 * @param data 待排序数组
	 * @return 排序后的数组
	 * 在每一轮排序后，记录下最后一次元素交换的位置，该位置即为无序数列的边界，再往后就是有序区了
	 */
	private int[] bubbleSort2(int[] data) {
		int temp;
		// 最后一次交换位置
		int lastChangeIndex = 0;
		// 无序数组的边界，每次比较只需要比到这里为止，第一次边界为最后一个值
		int sortBorder = data.length - 1;
		for (int i = 0; i < data.length - 1; i++) {
			// 有序标记，每一轮的初始值都是true
			boolean isSorted = true;
			for (int j = 0; j < sortBorder; j++) {
				if (data[j] > data[j + 1]) {
					// swap
					temp = data[j];
					data[j] = data[j + 1];
					data[j + 1] = temp;
					// 因为有元素进行交换，所以不是有序的，标记变为false
					isSorted = false;
					// 把无序数组的边界更新为最后一次交换元素的位置
					lastChangeIndex = j;
				}
			}
			System.out.println("第 " + (i + 1) + " 轮结束：" + Arrays.toString(data));
			sortBorder = lastChangeIndex;
			if (isSorted) {
				break;
			}
		}
		return data;
	}

	public static void main(String[] args) {
		BubbleSort sort = new BubbleSort();
//		int[] data1 = {2, 5, 4, 8, 7, 9, 1, 6, 3};
//		System.out.println(Arrays.toString(sort.bubbleSort(data)));
//		int[] data2 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
//		System.out.println(Arrays.toString(sort.bubbleSort1(data2)));
		int[] data2 = {4, 3, 2, 1, 5, 6, 7, 8, 9};
		System.out.println(Arrays.toString(sort.bubbleSort2(data2)));
	}

	/*==========其他写法*/

	private void sort(Comparable[] arr){
		int n = arr.length;
		boolean swapped;
		do {
			swapped = false;
			for (int i = 1 ; i < n ; i ++) {
				if (arr[i - 1].compareTo(arr[i]) > 0) {
					swap(arr, i - 1, i);
					swapped = true;
				}
			}

			// 优化, 每一趟Bubble Sort都将最大的元素放在了最后的位置
			// 所以下一次排序, 最后的元素可以不再考虑
			n --;
		} while (swapped);
	}

	private void sort2(Comparable[] arr){
		int n = arr.length;
		// 使用newn进行优化
		int newn;
		do {
			newn = 0;
			for (int i = 1 ; i < n ; i ++) {
				if (arr[i - 1].compareTo(arr[i]) > 0) {
					swap(arr, i - 1, i);
					// 记录最后一次的交换位置,在此之后的元素在下一轮扫描中均不考虑
					newn = i;
				}
			}
			n = newn;
		} while (newn > 0);
	}

	private void swap(Object[] arr, int i, int j) {
		Object t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}
}
