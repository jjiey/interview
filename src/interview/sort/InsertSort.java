package interview.sort;

import java.util.Arrays;

/**
 * 插入排序
 * 时间复杂度O(n^2)
 */
public class InsertSort {

	// 插入排序就是step=1的希尔排序
	private static void insertSort(int[] data) {
		// 这样写是对for循环的一个小优化，只运行一次，不用每次都取，尤其是对Map.size()或list.size()等;（很多源码都是这样写）
		for(int i = 1, len = data.length; i < len; i ++) { // 每次加的数进来，从第二个值开始
			for(int j = i; j > 0; j --) { // 这个j表示的是已经排好序的序列（这里j>0可以写成j-1>=0就和希尔排序写法一致了）
				if(data[j] < data[j - 1]) {
					// swap
					int temp = data[j];
					data[j] = data[j - 1];
					data[j - 1] = temp;
				} else break;
			}
		}
	}

	public static void main(String[] args) {
		// 4 2 3 1
		// 第一步: 4
		// 第二步: 2 4
		// 第三步: 2 3 4
		// 第四步: 1 2 3 4
		//String a = "1";
		//String b = "2";
		//a.compareTo(b);
		int[] data = {4, 2, 3, 1};
		insertSort(data);
		System.out.println(Arrays.toString(data));
	}

}
// 如何不引入第三个变量去交换ab的值
// 思路：: 只要用到加减就可以。也是一个同花顺的面试考题
// a = 2, b = 3
// a = a + b; => a = 5
// b = a - b; => b = 5 - 3 = 2
// a = a - b; => a = 5 - 2 = 3
