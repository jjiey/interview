package interview.sort;

import java.util.Arrays;

/**
 * 快速排序
 * 概念：基准数
 * 核心是：分组
 * 时间复杂度：
 * 最好：O(N*logN)
 * 最坏：O(N^2)
 * 平均：O(N*logN)
 * 空间复杂度：O(logn)~O(n)
 * 稳定性：不稳定
 * TODO 优化：随机选择一个元素作为基准元素，并且让基准元素和数列首元素交换位置
 */
public class QuickSortRecursive {

	/**
	 * 分治：双边循环法
	 * @param data 待排序的数组
	 * @param left 起始下标
	 * @param right 结束下标
	 */
	private static void quickSortDoubleSideLoop(int[] data, int left, int right) {
		// 从左边找的位置
		int ll = left;
		// 从右边找的位置
		int rr = right;
		// 基准数，取第一个作为基准数
		int pivot = data[left];
		// 终止条件
		while (ll != rr) {
			// 控制rr指针比较并左移
			while (ll < rr && data[rr] > pivot) {
				rr --;
			}
			// 控制ll指针比较并右移
			while (ll < rr && data[ll] <= pivot) {
				ll ++;
			}
			// 找到需要交换的
			if (ll < rr) {
				// swap data[rr] and data[ll]
				int temp = data[rr];
				data[rr] = data[ll];
				data[ll] = temp;
			}
		}
		// 基准数pivot和指针重合点进行交换
		data[left] = data[ll];
		data[ll] = pivot;
		System.out.print("以pivot = " + pivot + " 的排序结果：");
		System.out.println(Arrays.toString(data));
		// 基准数左部分
		if (ll > left) {
			quickSortDoubleSideLoop(data, left, ll - 1);
		}
		// 基准数右部分
		if (rr < right) {
			quickSortDoubleSideLoop(data, ll + 1, right);
		}
	}

	/**
	 * 分治：单边循环法（左边）
	 * @param data 待排序的数组
	 * @param left 起始下标
	 * @param right 结束下标
	 */
	private static void quickSortLeftSideLoop(int[] data, int left, int right) {
		// 基准数，取第一个作为基准数
		int pivot = data[left];
		// 代表小于基准元素pivot的区域边界
		int mark = left;
		for (int i = left + 1; i <= right; i++) {
			if (data[i] < pivot) {
				mark ++;
				// swap data[mark] and data[i]
				int temp = data[mark];
				data[mark] = data[i];
				data[i] = temp;
			}
		}
		// 基准数pivot和mark进行交换
		data[left] = data[mark];
		data[mark] = pivot;
		System.out.print("以pivot = " + pivot + " 的排序结果：");
		System.out.println(Arrays.toString(data));
		// 基准数左部分
		if (mark > left) {
			quickSortLeftSideLoop(data, left, mark - 1);
		}
		// 基准数右部分
		if (mark < right) {
			quickSortLeftSideLoop(data, mark + 1, right);
		}
	}

	/**
	 * 分治：单边循环法（右边）
	 * @param data 待排序的数组
	 * @param left 起始下标
	 * @param right 结束下标
	 */
	private static void quickSortRightSideLoop(int[] data, int left, int right) {
		// 基准数，取第一个作为基准数
		int pivot = data[left];
		// 代表大于基准元素pivot的区域边界
		int mark = right;
		for (int i = right; i > left; i--) {
			if (data[i] > pivot) {
				// swap data[mark] and data[i]
				int temp = data[mark];
				data[mark] = data[i];
				data[i] = temp;
				mark --;
			}
		}
		// 基准数pivot和mark进行交换
		data[left] = data[mark];
		data[mark] = pivot;
		System.out.print("以pivot = " + pivot + " 的排序结果：");
		System.out.println(Arrays.toString(data));
		// 基准数左部分
		if (mark > left) {
			quickSortRightSideLoop(data, left, mark - 1);
		}
		// 基准数右部分
		if (mark < right) {
			quickSortRightSideLoop(data, mark + 1, right);
		}
	}

	public static void main(String[] args) {
		int[] data = {45, 28, 80, 90, 50, 16, 100, 10};
		quickSortDoubleSideLoop(data, 0, data.length - 1);
		System.out.println("==========");
		int[] data2 = {45, 28, 80, 90, 50, 16, 100, 10};
		quickSortLeftSideLoop(data2, 0, data2.length - 1);
		System.out.println("==========");
		int[] data3 = {45, 28, 80, 90, 50, 16, 100, 10};
		quickSortRightSideLoop(data3, 0, data3.length - 1);
	}

}
