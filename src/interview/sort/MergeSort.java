package interview.sort;

import java.util.Arrays;

/**
 * 归并排序
 * 时间复杂度：都是O(N*logN)
 * 空间复杂度：O(n)，因为并的时候需要开辟同样大小的空间来辅助合并
 * 稳定性：稳定
 */
public class MergeSort {

	/**
	 * mergeSort，自顶向下
	 * @param data 待排序数组
	 */
	private void mergeSort(int[] data){
		sort1(data, 0, data.length - 1);
	}

	/**
	 * sort
	 * @param data 待排序数组
	 * @param left 起始index
	 * @param right 结尾index
	 */
	private void sort1(int[] data, int left, int right) {
		if (left >= right) {
			return;
		}
		int mid = left + ((right - left) >> 1);
		sort1(data, left, mid);
		sort1(data, mid + 1, right);
		// 以上就归完了，下边开始并
		merge(data, left, mid, right);
	}

	/**
	 * 将arr[l...mid]和arr[mid+1...r]两部分进行归并
	 * @param data 待排序数组
	 * @param left 起始index
	 * @param mid 中间index
	 * @param right 结尾index
	 */
	private static void merge(int[] data, int left, int mid, int right) {
		// 用来保存合并后的序列，辅助排序
		int[] temp = new int[right - left + 1];
		// i 表示左边的第一个数的索引；j 表示右边的第一个数的索引；k 表示填到temp里的当前数字的索引
		int i = left, j = mid + 1, k = 0;
		// 左右部分比较
		while (i <= mid && j <= right) {
			temp[k++] = data[i] <= data[j] ? data[i++] : data[j++];
		}
		// 检查：右边全部存进去，左边还没有存完
		while (i <= mid) {
			temp[k++] = data[i++];
		}
		// 同上检查：左边全部存进去，右边还没有存完
		while (j <= right) {
			temp[k++] = data[j++];
		}
		// 复制回原来的数组
		System.arraycopy(temp, 0, data, left, temp.length);
	}

	// 和 merge 的区别是：temp 的大小和 merge1 的不同，k 的初始值也不同，思想是一样的，中间代码部分也是一样的。。
	private static void merge2(int[] data, int left, int mid, int right) {
		// 用来保存合并后的序列，辅助排序
		int[] temp = new int[data.length];
		// i 表示左边的第一个数的索引；j 表示右边的第一个数的索引；k 表示填到temp里的当前数字的索引
		int i = left, j = mid + 1, k = left;
		// 左右部分比较
		while (i <= mid && j <= right) {
			temp[k++] = data[i] <= data[j] ? data[i++] : data[j++];
		}
		// 检查：右边全部存进去，左边还没有存完
		while (i <= mid) {
			temp[k++] = data[i++];
		}
		// 同上检查：左边全部存进去，右边还没有存完
		while (j <= right) {
			temp[k++] = data[j++];
		}
		// 复制回原来的数组
		System.arraycopy(temp, left, data, left, right - left + 1);
	}

	/**
	 * 将arr[l...mid]和arr[mid+1...r]两部分进行归并
	 * @param data 待排序数组
	 * @param left 起始index
	 * @param mid 中间index
	 * @param right 结尾index
	 */
	private static void merge1(int[] data, int left, int mid, int right) {
		int[] aux = Arrays.copyOfRange(data, left, right + 1);
		// 初始化，i指向左半部分的起始索引位置l；j指向右半部分起始索引位置mid+1
		int i = left, j = mid + 1;
		for (int k = left ; k <= right; k ++) {
			/*前两个if是为了确保索引合法性*/
			// 如果左半部分元素已经全部处理完毕
			if (i > mid) {
				data[k] = aux[j - left];
				j ++;
			}
			// 如果右半部分元素已经全部处理完毕
			else if (j > right) {
				data[k] = aux[i - left];
				i ++;
			}
			// 左半部分所指元素 < 右半部分所指元素
			else if (aux[i - left] < aux[j - left]) {
				data[k] = aux[i - left];
				i ++;
			}
			// 左半部分所指元素 >= 右半部分所指元素
			else {
				data[k] = aux[j - left];
				j ++;
			}
		}
	}

	/*==========优化*/

	/**
	 * mergeSort，自顶向下
	 * 两个优化点，虽然两个优化点对时间复杂度没有影响，但是确实会让程序快一些
	 * @param data 待排序数组
	 */
	private void mergeSort2(int[] data){
		sort2(data, 0, data.length - 1);
	}

	// 递归使用归并排序,对arr[l...r]的范围进行排序
	private static void sort2(int[] data, int l, int r) {
		// 优化2: 对于小规模数组, 使用插入排序
		// 原因：对于所有的高级排序算法，都存在一种优化情况，那就是递归到数据量比较小的时候，可以转而使用插入排序来提高性能。
		// 因为当数据量比较小的时候，整个数组近乎有序的概率会比较大；因为对于两个排序算法的时间复杂度来说，前面都有一个常数系数，而插入排序比归并排序小。换句话说，当n比较小时，插入排序会比归并排序快一些
		// 处理16个元素时，用插入排序法
		if (r - l <= 15) {
			InsertionSort.insertSort(data, l, r);
			return;
		}
		int mid = l + ((r - l) >> 1);
		sort2(data, l, mid);
		sort2(data, mid + 1, r);
		// 优化1: 对于arr[mid] <= arr[mid+1]的情况,不进行merge（说明已经有序）
		// 对于近乎有序的数组非常有效,但是对于一般情况,有一定的性能损失
		// 场景：如果要处理的数据有可能会出现近乎有序的情况，可以加上
		if (data[mid] > data[mid+1]) {
			merge1(data, l, mid, r);
		}
	}

	/*==========自顶向下 比 自底向上 要快一些，为什么？
	* 自底向上 的归并排序有个非常重要的作用，这个算法没有使用数组“通过索引直接获取元素”这个特性，正因为如此，这样一个自底向上的归并排序算法，可以非常好的使用 O(n logn) 的时间对链表进行排序*/

	/**
	 * Merge Sort Bottom Up，自底向上，无优化版本
	 * 不需要递归，只需要迭代即可完成。名字也可以叫mergeSortBU（Bottom-Up）
	 * @param data 待排序数组
	 */
	private void mergeSort3(int[] data){
		int n = data.length;
		// 第一层循环对 merge 的元素个数进行遍历（1，2，4，8，16）。边界：当 sz = n 时，内循环 n - sz = 0，也意味着内循环根本不会走，所以外循环中 sz < n 即可
		// 第二层循环中 i 为每一轮要进行归并的两个有序数组中左边数组的起始位置。边界：mid 和 right 的计算方式有可能会出现数组越界；；；所以 i + sz < n，保证了 mid 的取值不会越界，也保证了至少要对两部分进行归并这个前提条件（否则没有意义）；Math.min(i + 2 * sz - 1, n - 1)，保证了 right 的取值不会越界
        for (int sz = 1; sz < n; sz *= 2) {
			for (int i = 0; i < n - sz; i += 2 * sz) {
				// 对 data[i...i + sz - 1] 和 data[i + sz...i + 2*sz - 1] 进行归并
				merge1(data, i, i + sz - 1, Math.min(i + 2 * sz - 1, n - 1));
			}
		}
	}

	/*==========优化*/

	/**
	 * Merge Sort Bottom Up，自底向上，优化版本
	 * @param data 待排序数组
	 */
	private void mergeSort4(int[] data){
		int n = data.length;
		// 对于小数组, 使用插入排序优化
		for (int i = 0; i < n; i += 16) {
			InsertionSort.insertSort(data, i, Math.min(i + 15, n - 1));
		}
		for (int sz = 16; sz < n; sz *= 2) {
			for (int i = 0; i < n - sz; i += 2 * sz) {
				// 对于data[mid] <= data[mid+1]的情况,不进行 merge
				if (data[i + sz - 1] > data[i + sz]) {
					merge1(data, i, i + sz - 1, Math.min(i + 2 * sz - 1, n - 1));
				}
			}
		}
	}

	/*==========Wikipedia*/

	private void mergeSort10(int[] arr) {
		int len = arr.length;
		mergeSortRecursive(arr, new int[len], 0, len - 1);
	}

	private static void mergeSortRecursive(int[] arr, int[] result, int start, int end) {
		if (start >= end) {
			return;
		}
		int mid = ((end - start) >> 1) + start;
		int start1 = start, end1 = mid, start2 = mid + 1, end2 = end;
		mergeSortRecursive(arr, result, start1, end1);
		mergeSortRecursive(arr, result, start2, end2);
		int k = start;
		// 左右部分比较
		while (start1 <= end1 && start2 <= end2) {
			result[k++] = arr[start1] < arr[start2] ? arr[start1++] : arr[start2++];
		}
		// 检查：右边全部存进去，左边还没有存完
		while (start1 <= end1) {
			result[k++] = arr[start1++];
		}
		// 同上检查：左边全部存进去，右边还没有存完
		while (start2 <= end2) {
			result[k++] = arr[start2++];
		}
		// 复制回原来的数组
		for (k = start; k <= end; k++) {
			arr[k] = result[k];
		}
	}

	// 这里以上边的 mid + 1 作为 mid，骚操作让我看了半天。。。
	private void mergeSort11(int[] arr) {
		int[] result = new int[arr.length];
		for (int i = 2; i < arr.length * 2; i *= 2) {
			for (int j = 0; j < (arr.length + i - 1) / i; j++) {
				int left = i * j;
				int mid = left + i / 2 >= arr.length ? (arr.length - 1) : (left + i / 2);
				int right = i * (j + 1) - 1 >= arr.length ? (arr.length - 1) : (i * (j + 1) - 1);
				int start = left, l = left, m = mid;
				// int start1 = left, end1 = mid, start2 = mid + 1, end2 = right;
				// 左右部分比较
				while (l < mid && m <= right) {
					result[start++] = arr[l] < arr[m] ? arr[l++] : arr[m++];
				}
				// 检查：右边全部存进去，左边还没有存完
				while (l < mid) {
					result[start++] = arr[l++];
				}
				// 同上检查：左边全部存进去，右边还没有存完
				while (m <= right) {
					result[start++] = arr[m++];
				}
				// 复制回原来的数组
				System.arraycopy(result, left, arr, left, right - left + 1);
			}
		}
	}

	public static void main(String[] args) {
		int[] data = SortTestHelper.generateRandomArray(20, 10, 50);
		System.out.println("待排序数组：" + Arrays.toString(data));
		MergeSort sort = new MergeSort();
//		sort.bu(data);
		sort.mergeSort(data);
		System.out.println(Arrays.toString(data));
	}

	private void bu(int[] arr) {
		int n = arr.length;
		for (int i = 1; i < n; i *= 2) {
			System.out.println(i + "==========");
			for (int j = 0; j < n - i; j += 2 * i) {
				int left = j;
				int mid = j + i - 1;
				int right = Math.min(j + 2 * i - 1, n - 1);
				System.out.println(left + " , " + mid + " , " + right);
			}
		}
		System.out.println("---------------------------------------------------------------------------------------------------");
		for (int i = 2; i < n * 2; i *= 2) {
			System.out.println(i + "==========");
			for (int j = 0; j < (n + i - 1) / i; j++) {
				int left = i * j;
				int mid = left + i / 2 >= n ? (n - 1) : (left + i / 2);
				int right = i * (j + 1) - 1 >= n ? (n - 1) : (i * (j + 1) - 1);
				System.out.println(left + " , " + mid + " , " + right);
			}
		}
	}
}
