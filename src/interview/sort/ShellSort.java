package interview.sort;

import java.util.Arrays;

/**
 * 希尔排序
 * 概念：步长
 * 时间复杂度：
 * 最好：O(N)
 * 最坏：O(N^2)
 * 平均：O(N^1.3)
 * 空间复杂度：O(1)
 * 稳定性：不稳定
 */
public class ShellSort {

	/**
	 * @param data 待排序数组
	 * @return 排序后的数组
	 */
	private int[] shellSort(int[] data) {
		int len = data.length, step = len, temp;
		while (step >= 1) {
			step = step / 2;
			for(int i = step; i < len; i++) {
				for(int j = i; j - step >= 0; j -= step) {
					if (data[j] < data[j - step]) {
						// swap
						temp = data[j];
						data[j] = data[j - step];
						data[j - step] = temp;
					} else {
						break;
					}
				}
			}
		}
		return data;
	}

	/**
	 * 简化1的写法
	 */
	private int[] shellSort2(int[] data) {
		int len = data.length, step = len, before, temp;
		while (step >= 1) {
			// 计算步长
			step = step / 2;
			for (int i = step; i < len; i++) {
				for (int j = i; (before = j - step) >= 0 && data[j] < data[before]; j = before) {
					// swap
					temp = data[j];
					data[j] = data[before];
					data[before] = temp;
				}
			}
		}
		return data;
	}

	/**
	 * 其他写法
	 * @param data 待排序数组
	 * @return 排序后的数组
	 */
	private int[] shellSort3(int[] data) {
		int len = data.length, before, temp, old;
		for (int step = len / 2; step >= 1; step /= 2) {
			for (int i = step; i < len; i++) {
				temp = data[i];
				before = i - step;
				old = before;
				while (before >= 0 && data[before] > temp) {
					data[before + step] = data[before];
					before -= step;
				}
				// 如果进了 while 循环，才有必要赋值
				if (before != old) {
					data[before + step] = temp;
				}
			}
		}
		return data;
	}

	/**
	 * 糅合2和3
	 */
	private int[] shellSort4(int[] data) {
		int len = data.length, before, temp;
		for (int step = len / 2; step >= 1; step /= 2) {
			for (int i = step; i < len; i++) {
				for(int j = i; (before = j - step) >= 0 && data[j] < data[before]; j = before) {
					// swap
					temp = data[j];
					data[j] = data[before];
					data[before] = temp;
				}
			}
		}
		return data;
	}

	/**
	 * 用插入排序的代码优化4
	 */
	private int[] shellSort5(int[] data) {
		int len = data.length, e, j, before;
		for (int step = len / 2; step >= 1; step /= 2) {
			for (int i = step; i < len; i++) {
				e = data[i];
				for(j = i; (before = j - step) >= 0 && data[before] > e; j = before) {
					data[j] = data[before];
				}
				data[j] = e;
			}
		}
		return data;
	}

	/**
	 * 用二分查找插入的代码优化5
	 */
	private int[] shellSort6(int[] data) {
		int len = data.length, e, left, right, mid;
		for (int step = len / 2; step >= 1; step /= 2) {
			for (int i = step; i < len; i++) {
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
				System.arraycopy(data, left, data, left + 1, i - left);
				// 插入元素
				data[left] = e;
			}
		}
		return data;
	}

	public static void main(String[] args) {
		int[] data = SortTestHelper.generateRandomArray(10, 10, 30);
		System.out.println("待排序数组：" + Arrays.toString(data));
		ShellSort sort = new ShellSort();
		System.out.println(Arrays.toString(sort.shellSort2(data)));

//		System.out.println(Arrays.toString(new ShellSort().shellSort(new int[]{2,1,4,3,6,5,8,7})));

//		int i = 5;
//		while (true) {
//			System.out.println("==========");
//			System.out.println(a(i));
//			System.out.println(b(i));
//			System.out.println(c(i));
//			i ++;
//			if (i == 10) {
//				break;
//			}
//		}
	}

	/*==========*/
	// 时间复杂度 O(n ^ (3 / 2))
	private int[] shellSort7(int[] data) {
		int n = data.length;
		// 计算 increment sequence: 1, 4, 13, 40, 121, 364, 1093...
		int h = 1;
		while (h < n / 3) {
			h = 3 * h + 1;
		}
		while (h >= 1) {
			// h-sort the array
			for (int i = h; i < n; i++) {
				// 对 arr[i], arr[i-h], arr[i-2*h], arr[i-3*h]... 使用插入排序
				int e = data[i];
				int j = i;
				for ( ; j >= h && e <= data[j - h] ; j -= h) {
					data[j] = data[j - h];
				}
				data[j] = e;
			}

			h /= 3;
		}
		return data;
	}

	private static double a(int i) {
		double v = 9 * Math.pow(4, i) - 9 * Math.pow(2, i) + 1;
		return v;
	}

	private static double b(int i) {
		double v = Math.pow(2, i + 2) * (Math.pow(2, i + 2) - 3) + 1;
		return v;
	}

	private static double c(int i) {
		double v = Math.pow(4, i) - 3 * Math.pow(2, i) + 1;
		return v;
	}
}
