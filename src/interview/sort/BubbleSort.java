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

	private static void bubbleSort(int[] data) {
		for (int i = 0, len = data.length; i < len - 1; i ++) {
			for (int j = 0; j < len - 1 - i; j ++) {
				if (data[j] > data[j + 1]) {
					// swap
					int temp = data[j];
					data[j] = data[j + 1];
					data[j + 1] = temp;
				}
			}
		}
	}

	public static void main(String[] args) {
		// 4 2 5 1
		// 4
		// 4 2 => 2 4
		// 2 4 5 => 2 4 5
		// 2 4 5 1 => 1 2 4 5
		int[] data = {4, 2, 3, 1};
		bubbleSort(data);
		System.out.println(Arrays.toString(data));
	}

}
