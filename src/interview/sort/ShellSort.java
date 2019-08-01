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

	private static void shellSort(int[] data) {
		int len = data.length;
		int step = len;
		while(step >= 1) {
			step = step / 2;
			for(int i = step; i < len; i++) {
				for(int j = i; j - step >= 0; j -= step) {
					if(data[j] < data[j - step]) {
						// swap
						int temp = data[j];
						data[j] = data[j - step];
						data[j - step] = temp;
					} else break;
				}
			}
		}
	}

	public static void main(String[] args) {
		// 4 1 2 3 5
		// 第一步:
		// len = 5 表示有5个数字
		// 步长: step = len/2 => 5/2 = 2;
		// 第一组: 4 2 => 分出来的还是进行一个插入排序 2 4
		// 第二组: 1 3 => 1 3
		// 第三组: 5 => 5
		// 2 4 1 3 5
		// 第二步:
		// 步长: step = step/2 => 1
		// ... ...
		// 直到：step = 1排完
		int[] data = {4, 2, 3, 1};
		shellSort(data);
		System.out.println(Arrays.toString(data));
	}

}
