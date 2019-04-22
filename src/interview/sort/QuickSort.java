package interview.sort;

import java.util.Arrays;

/**
 * 快速排序
 * 概念：基准数
 * 核心是：分组
 */
public class QuickSort {

	public static void quickSort(int data[], int left, int right) {
		int ll = left; // 从左边找的位置
		int rr = right; // 从右边找的位置
		int base = data[left]; // 基准数，取第一个作为基准数

		while (ll < rr) { // 排完的终止条件
			// 从后面往前找到比基准数小的数进行对换
			while (ll < rr && data[rr] >= base) { // ll<rr一定要加，否则可能出现死循环或数组越界
				rr --;
			}
			/* 此时会有两种情况：一个是没找到（比如1, 2, 3, 4, 5），一个是找到了 */
			if (ll < rr) { // 表示找到了
				// swap
				int temp = data[rr];
				data[rr] = data[ll];
				data[ll] = temp;
				ll ++;
			}
			// 从前面往后面找比基准数大的进行对换，和上边类似
			while (ll < rr && data[ll] <= base) {
				ll ++;
			}
			if (ll < rr) {
				int temp = data[rr];
				data[rr] = data[ll];
				data[ll] = temp;
				rr --;
			}
		}
		/* 此时ll == rr */
		System.out.print("以Base = " +base+ "的排序结果：");
		System.out.println(Arrays.toString(data));
		// 以基准数分为3部分，左边的比基准数小，右边比基准数大。我们要做的就是一把这左边和右边分别进行快速排序
		if (ll > left) { // 左部分
			quickSort(data, left, ll - 1);
		}
		if (rr < right) { // 右部分
			quickSort(data, ll + 1, right);
		}
	}

	public static void main(String[] args) {
		int[] data = {45, 28, 80, 90, 50, 16, 100, 10};
		quickSort(data, 0, data.length - 1);
	}

}
