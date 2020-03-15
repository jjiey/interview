package interview.sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

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
public class QuickSortBak {

	/**
	 * quickSort
	 * 该方法每次交换的是基准数，会增加交换次数，可以优化为先交换其他的，最后只交换一次基准数
	 */
	private static void quickSort(int[] data, int left, int right) {
		// 从左边找的位置
		int ll = left;
		// 从右边找的位置
		int rr = right;
		// 基准数，取第一个作为基准数
		int base = data[left];

		// 排完的终止条件
		while (ll < rr) {
			// 从后面往前找到比基准数小的数进行对换
			while (ll < rr && data[rr] >= base) { // ll<rr一定要加，否则可能出现死循环或数组越界
				rr --;
			}
			/* 此时会有两种情况：一个是没找到（比如1, 2, 3, 4, 5）比基准数小的数，一个是找到了比基准数小的数 */
			// 表示找到了比基准数小的数
			if (ll < rr) {
				// swap
				int temp = data[rr];
				data[rr] = data[ll];
				data[ll] = temp;
				ll ++;
			}
			// 同上，从前面往后面找比基准数大的进行对换
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
		System.out.print("以Base = " + base + " 的排序结果：");
		System.out.println(Arrays.toString(data));
		/* 此时以基准数分为3部分，左边的比基准数小，右边比基准数大。我们要做的就是继续把基准数左边和右边分别进行快速排序 */
		// 基准数左部分
		if (ll > left) {
			quickSort(data, left, ll - 1);
		}
		// 基准数右部分
		if (rr < right) {
			quickSort(data, ll + 1, right);
		}
	}

	/**
	 * quickSort
	 */
	private static void quickSort2(int[] data, int left, int right) {
		// 递归结束条件
		if (left >= right) {
			return;
		}
		// 得到基准元素位置
		/* 双边循环法 */
//		int pivotIndex = partition1(data, left, right);
		/* 单边循环法 */
		int pivotIndex = partition2(data, left, right);
		// 基准数左部分
		quickSort2(data, left, pivotIndex - 1);
		// 基准数右部分
		quickSort2(data, pivotIndex + 1, right);
	}

	/**
	 * quickSort非递归实现
	 */
	private static void quickSortNoRecursive(int[] data, int left, int right) {
		String startIndexKey = "startIndex";
		String endIndexKey = "endIndex";
		// 用一个集合栈来代替递归的函数栈
		Stack<Map<String, Integer>> quickSortStack = new Stack<>();
		// 整个数列的起止下标，以哈希的形式入栈
		Map<String, Integer> rootParam = new HashMap<>();
		rootParam.put(startIndexKey, left);
		rootParam.put(endIndexKey, right);
		quickSortStack.push(rootParam);
		// 循环结束条件：栈为空时
		while (!quickSortStack.empty()) {
			// 栈顶元素出栈，得到起止下标
			Map<String, Integer> param = quickSortStack.pop();
			// 得到基准元素位置
			/* 双边循环法 */
			int pivotIndex = partition1(data, param.get(startIndexKey), param.get(endIndexKey));
			/* 单边循环法 */
//			int pivotIndex = partition2(data, param.get(startIndex), param.get(endIndex));
			// 根据基准元素分为两部分，把每一部分的起止下标入栈
			if (param.get(startIndexKey) < pivotIndex - 1) {
				Map<String, Integer> leftParam = new HashMap<>();
				leftParam.put(startIndexKey, param.get(startIndexKey));
				leftParam.put(endIndexKey, pivotIndex - 1);
				quickSortStack.push(leftParam);
			}
			if (pivotIndex + 1 < param.get(endIndexKey)) {
				Map<String, Integer> rightParam = new HashMap<>();
				rightParam.put(startIndexKey, pivotIndex + 1);
				rightParam.put(endIndexKey, param.get(endIndexKey));
				quickSortStack.push(rightParam);
			}
		}
	}

	/**
	 * 分治：双边循环法
	 * @param data 待交换的数组
	 * @param left 起始下标
	 * @param right 结束下标
	 * @return 基准数位置
	 */
	private static int partition1(int[] data, int left, int right) {
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
		return ll;
	}

	/**
	 * 分治：单边循环法
	 * @param data 待交换的数组
	 * @param left 起始下标
	 * @param right 结束下标
	 * @return 基准数位置
	 */
	private static int partition2(int[] data, int left, int right) {
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
		return mark;
	}

	public static void main(String[] args) {
		int[] data = {45, 28, 80, 90, 50, 16, 100, 10};
		quickSort(data, 0, data.length - 1);
//		quickSort2(data, 0, data.length - 1);
//		quickSortNoRecursive(data, 0, data.length - 1);
	}

}
