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
	 * mergeSort
	 * @param data 待排序数组
	 * @param left 起始index
	 * @param right 结尾index
	 */
	private static void mergeSort(int[] data, int left, int right) {
		if (left >= right) {
			return;
		}
		int mid = left + (right - left) / 2; // int mid = (left + right) / 2;
		mergeSort(data, left, mid);
		mergeSort(data, mid + 1, right);
		System.out.println(Arrays.toString(data) + " , " + left + " , " + mid + " , " + right);
		/* 以上就归完了，下边开始并 */
		merge(data, left, mid, right);
	}

	/**
	 * 并
	 * @param data 待排序数组
	 * @param left 起始index
	 * @param mid 中间index
	 * @param right 结尾index
	 */
	private static void merge(int[] data, int left, int mid, int right) {
		// 用来保存合并后的序列，辅助排序
		int[] temp = new int[data.length];
		// 表示左边的第一个数的索引
		int point1 = left;
		// 表示右边的第一个数的索引
		int point2 = mid + 1;
		// 表示填到temp里的当前数字的索引
		int loc = left;
		/* 左右部分比较 */
		while(point1 <= mid && point2 <= right) {
			if(data[point1] <= data[point2]) {
				temp[loc] = data[point1];
				point1 ++;
			} else {
				temp[loc] = data[point2];
				point2 ++;
			}
			loc ++;
		}
		/* 检查：右边全部存进去，左边还没有存完 */
		while(point1 <= mid) {
			temp[loc ++] = data[point1 ++];
		}
		/* 同上检查：左边全部存进去，右边还没有存完 */
		while(point2 <= right) {
			temp[loc ++] = data[point2 ++];
		}
		/* 复制回原来的数组 */
		for(int i = left; i <= right; i++) {
			data[i] = temp[i];
		}
	}

	public static void main(String[] args) {
		//int[] data = {9, 5, 6, 8, 0, 3, 7, 1, 20, 1};
		int[] data = {2, 1, 7, 9, 5, 8};
		mergeSort(data, 0, data.length - 1);
		System.out.println(Arrays.toString(data));
	}
	
}
