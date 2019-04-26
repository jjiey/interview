package interview.sort;

import java.util.Arrays;

/**
 * 归并排序
 * 时间复杂度：
 * 最好：O(N*logN)
 * 最坏：O(N*logN)
 * 平均：O(N*logN)
 * 空间复杂度：O(n)
 * 稳定性：稳定
 */
public class MergeSort {

	public static void main(String[] args) {
		int[] data = {9, 5, 6, 8, 0, 3, 7, 1, 20, 1};
		mergeSort(data, 0, data.length - 1);
		System.out.println(Arrays.toString(data));
	}
	
	public static void mergeSort(int[] data, int left, int right) {
		if(left <  right) {
			int mid = left + (right - left) / 2; // int mid = (left + right) / 2;
			mergeSort(data, left, mid);
			mergeSort(data, mid + 1, right);
			/* 以上就分完了，下边开始合并 */
			merge(data, left, mid, right);
		}
	}
	
	public static void merge(int[] data, int left, int mid, int right) {
		int[] temp = new int[data.length]; // 用来保存合并后的序列，辅助排序
		int point1 = left; // 表示左边的第一个数的位置
		int point2 = mid + 1; // 表示右边的第一个数的位置
		int loc = left; // 用来保存当前填了哪个数字的索引到temp里面去
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
		/* 同上，检查：左边全部存进去，右边还没有存完 */
		while(point2 <= right) {
			temp[loc ++] = data[point2 ++];
		}
		/* 复制回原来的数组 */
		for(int i = left; i <= right; i ++) {
			data[i] = temp[i];
		}
	}
	
}
