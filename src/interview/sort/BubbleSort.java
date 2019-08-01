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
	 */
	private static void bubbleSort(int[] data) {
		int temp;
		for (int i = 0; i < data.length - 1; i++) {
			for (int j = 0; j < data.length - 1 - i; j ++) {
				if (data[j] > data[j + 1]) {
					// swap
					temp = data[j];
					data[j] = data[j + 1];
					data[j + 1] = temp;
				}
			}
			System.out.println("第 " + (i + 1) + " 轮结束：" + Arrays.toString(data));
		}
	}

	/**
	 * bubbleSort优化
	 * @param data 待排序数组
	 * 如果某一轮已经有序，则提前结束
	 */
	private static void bubbleSort1(int[] data) {
		int temp;
		for (int i = 0; i < data.length - 1; i++) {
			// 有序标记，每一轮的初始值都是true
			boolean isSorted = true;
			for (int j = 0; j < data.length - 1 - i; j ++) {
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
	}

	/**
	 * bubbleSort优化
	 * @param data 待排序数组
	 * 在每一轮排序后，记录下最后一次元素交换的位置，该位置即为无序数列的边界，再往后就是有序区了
	 */
	private static void bubbleSort2(int[] data) {
		int temp;
		// 最后一次交换位置
		int lastChangeIndex = 0;
		// 无序数组的边界，每次比较只需要比到这里为止，第一次边界为最后一个值
		int sortBorder = data.length - 1;
		for (int i = 0; i < data.length - 1; i++) {
			// 有序标记，每一轮的初始值都是true
			boolean isSorted = true;
			for (int j = 0; j < sortBorder; j ++) {
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
	}

	public static void main(String[] args) {
		int[] data = {4, 2, 3, 1};
//		bubbleSort(data);
//		bubbleSort1(data);
		bubbleSort2(data);
		System.out.println(Arrays.toString(data));
	}

}
