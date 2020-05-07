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
 * 3 4 5 5 4 3
 */
public class QuickSortRecursive {

	/**
	 * 分治：双边循环法
	 * @param data 待排序的数组
	 * @param left 起始下标
	 * @param right 结束下标
	 */
	private static void quickSortDoubleSideLoop(int[] data, int left, int right) {
		// 递归结束条件
		if (left >= right) {
			return;
		}
		// 从左边找的位置
		int ll = left;
		// 从右边找的位置
		int rr = right;
		// 基准数，每次取第一个作为基准数
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
		// 递归结束条件
		if (left >= right) {
			return;
		}
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
	 * 没什么意义
	 * @param data 待排序的数组
	 * @param left 起始下标
	 * @param right 结束下标
	 */
	@Deprecated
	private static void quickSortRightSideLoop(int[] data, int left, int right) {
		// 递归结束条件
		if (left >= right) {
			return;
		}
		// 基准数，取第一个作为基准数
		int pivot = data[left];
		// 代表大于基准元素pivot的区域边界
		int mark = right;
		for (int i = right; i >= left; i--) {
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
//		int[] data = {45, 28, 80, 90, 50, 16, 100, 10};
//		quickSortDoubleSideLoop(data, 0, data.length - 1);
//		System.out.println("==========");
//		int[] data2 = {45, 28, 80, 90, 50, 16, 100, 10};
//		quickSortLeftSideLoop(data2, 0, data2.length - 1);
//		System.out.println("==========");
//		int[] data3 = {45, 28, 80, 90, 50, 16, 100, 10};
//		quickSortRightSideLoop(data3, 0, data3.length - 1);
		int[] data = SortTestHelper.generateRandomArray(20, 10, 50);
		System.out.println("待排序数组：" + Arrays.toString(data));
		QuickSortRecursive sort = new QuickSortRecursive();
		sort.sortbb1(data);
		System.out.println(Arrays.toString(data));
	}

	/*==========bb*/

	private static void swap(int[] arr, int i, int j) {
		int t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}

	private void sortbb1(int[] arr) {
		sort(arr, 0, arr.length - 1);
	}

	// 递归使用快速排序,对arr[l...r]的范围进行排序
	private static void sort(int[] arr, int l, int r) {
		if (l >= r) {
			return;
		}
		int p = partition(arr, l, r);
		System.out.println("以pivot = " + arr[p] + " 的排序结果：" + Arrays.toString(arr) + "，pivot = " + p);
		sort(arr, l, p - 1);
		sort(arr, p + 1, r);
	}

	// 对arr[l...r]部分进行partition操作
	// 返回p, 使得arr[l...p-1] < arr[p] ; arr[p+1...r] > arr[p]
	private static int partition(int[] arr, int l, int r){
		int v = arr[l];
		// arr[l+1...j] < v ; arr[j+1...i) > v     i 是当前考察的元素
		int j = l;
		for (int i = l + 1 ; i <= r ; i ++) {
			if (arr[i] < v) {
//				j++;
//				swap(arr, j, i);
				// 可简写为
				swap(arr, ++j, i);
			}
		}
		swap(arr, l, j);
		return j;
	}

	/*==========优化*/
	/*
	1.小规模数组, 使用插入排序
	2.近乎有序的数组，这个快排版本特别慢，因为
	归并排序是把数组一分为二，其实快排也是，只不过分法不一样，快排要找基准值。这样两者就产生一个非常大的不同，归并排序可以保证每次都是平均的讲整个数组一分为二，而快排没有这个保证，所以分出来的子数组可能是一大一小的，所以快速排序递归生成的递归树平衡度就比归并排序差，并且不能保证高度为logn，很有可能比logn还要高，最差的情况就是整个数组近乎有序时，退化为O(n^2)。想想如果数组完全有序会怎样？
	怎么改变这种情况？可以看出选择基准值很重要，不能简单粗暴的用第一个值作为基准值了，我们希望能够选择数组中间的元素作为基准值。不能准确的定位中间元素怎么办？其实只要随机选择一个元素即可。
	此时，我们可以用数学的方法证明出来此时快排的时间复杂度它的期望值是O(n * logn)，（这个期望值的证明是非常复杂的），不代表每次都是O(n * logn)，但是在这种情况下，退化成O(n^2)的可能性是非常非常低的。可以证明：此时如果还要让快排退化到O(n^2)，第一次要选到最小的元素，概率 1/n，第二次要选到次小的元素，概率 1/(n-1)，以此类推，当n非常大的时候，这些概率乘起来，得到的概率值近乎为0

	在这里，实际上相当于编写了一个随机算法，随机算法就是，我不能保证我的算法是一定非常快或者一定是正确的，但是我可以保证我们的算法在非常高的概率下，都能非常快并且非常准确的得到结果。
	此时快排最坏的时间复杂度仍然是O(n^2)，但是要退化到O(n^2)的概率是及其低的，近乎为0。
	 */

	// 递归使用快速排序,对arr[l...r]的范围进行排序
	private static void sort2(int[] arr, int l, int r){
		// 对于小规模数组, 使用插入排序
		if(r - l <= 15){
			InsertionSort.insertSort(arr, l, r);
			return;
		}
		int p = partition2(arr, l, r);
		sort2(arr, l, p-1);
		sort2(arr, p+1, r);
	}

	// 对arr[l...r]部分进行partition操作
	// 返回p, 使得arr[l...p-1] < arr[p] ; arr[p+1...r] > arr[p]
	private static int partition2(int[] arr, int l, int r){
		// 随机在arr[l...r]的范围中, 选择一个数值作为标定点pivot，下面逻辑完全不变
		swap(arr, l , (int) (Math.random() * (r - l + 1)) + l);
		int v = arr[l];
		// arr[l+1...j] < v ; arr[j+1...i) > v
		int j = l;
		for ( int i = l + 1 ; i <= r ; i ++ ) {
			if (arr[i] < v) {
				swap(arr, ++j, i);
			}
		}
		swap(arr, l, j);
		return j;
	}

	/*==========优化*/
	/*
	前面解决了随机数组和几乎有序数组的问题
	新的数组为 拥有非常多的重复值的数组，优化后的快排版本也特别慢，此时又退化到了O(n^2)，因为
	当数组包含大量重复值的时候，partition操作都非常有可能把整个数组分成极度不平衡的两部分，因为对于每个值来说重复值太多，如果选的基准值如果有一点不平衡，那么两部分的差距就会非常大，即使基准值选在了一个平衡的位置，但是由于等于基准值的重复值也非常多，也会导致整个数组被分成极度不平衡的两部分。这种情况下，快排就会退化到O(n^2)
	解决方案1：重写partition，双边。
	这种partition的方式所分成的两部分和之前最大的区别就是：把等于基准值的元素分散到了左右两部分。换句话说，如果i和j指向的都是等于基准值的元素，在我们的逻辑里两个元素仍然要交换位置，这样就不会有存在大量等于基准值的元素都集中在左边或者右边一部分的情况，因此这种partition的结果当面临大量重复值的情况下，也能非常好的将他们近乎平分开来，防止退化到O(n^2)。
	 */

	// 双路快速排序的partition
	// 返回p, 使得arr[l...p-1] < arr[p] ; arr[p+1...r] > arr[p]
	private static int partition3(int[] arr, int l, int r){
		// 随机在arr[l...r]的范围中, 选择一个数值作为标定点pivot
		swap(arr, l , (int) (Math.random() * ( r - l + 1)) + l);
		int v = arr[l];
		// arr[l+1...i) <= v; arr(j...r] >= v
		int i = l + 1, j = r;
		while (true) {
			// 注意这里的边界, arr[i].compareTo(v) < 0, 不能是arr[i].compareTo(v) <= 0
			// 思考一下为什么?
			while (i <= r && arr[i] < v) {
				i++;
			}
			// 注意这里的边界, arr[j].compareTo(v) > 0, 不能是arr[j].compareTo(v) >= 0
			// 思考一下为什么?
			while (j >= l + 1 && arr[j] > v) {
				j--;
			}
			// 对于上面的两个边界的设定, 有的同学在课程的问答区有很好的回答:)
			// 大家可以参考: http://coding.imooc.com/learn/questiondetail/4920.html
			if (i > j) {
				break;
			}
			swap( arr, i, j );
			i ++;
			j --;
		}
		swap(arr, l, j);
		return j;
	}

	/*==========优化*/
	/*
	// 三路快速排序算法也是一个O(nlogn)复杂度的算法，可以在1秒之内轻松处理100万数量级的数据
	二路和路各有优劣，一般系统级别的快排都会按照三路方案，就是因为它在处理重复值的情况优势非常大；在处理没有重复值的情况效率也能得到保证。可以根据实际情况来选择
	 */

	// 三路快速排序处理 arr[l...r]
	// 将arr[l...r]分为 < v ; == v ;> v 三部分
	// 之后递归对 < v ; > v 两部分继续进行三路快速排序
	private static void sort3(int[] arr, int l, int r){
		// 对于小规模数组, 使用插入排序
		if ( r - l <= 15 ) {
			InsertionSort.insertSort(arr, l, r);
			return;
		}
		/* partition */
		// 随机在arr[l...r]的范围中, 选择一个数值作为标定点pivot
		swap(arr, l, (int) (Math.random() * ( r - l + 1 )) + l);
		int v = arr[l];
		// arr[l+1...lt] < v，初始这部分为空，相当于[l+1...l]
		int lt = l;
		// arr[gt...r] > v，初始这部分为空，相当于[r + 1...r]
		int gt = r + 1;
		// arr[lt+1...i) == v，初始这部分为空，相当于[l + 1...l + 1)
		int i = l + 1;
		while (i < gt) {
			if (arr[i] < v) {
				swap(arr, i, lt + 1);
				i ++;
				lt ++;
			}
			else if (arr[i] > v) {
				swap(arr, i, gt - 1);
				gt --;
			}
			// arr[i] == v
			else {
				i ++;
			}
		}
		swap(arr, l, lt);
		sort3(arr, l, lt - 1);
		sort3(arr, gt, r);
	}

	/*=========================================================xh=========================================================*/
	// 这种双边循环取第一个元素，一定要先从右边开始！！！如果从左边开始，比如 [③，1，4，2]，第一轮结束是 [4，1，2，③]，显然不对！
	private void quickSortxh(int[] data, int left, int right) {
		if (left >= right) {
			return;
		}
		int pivotIndex = partitionxh(data, left, right);
		quickSortxh(data, left, pivotIndex - 1);
		quickSortxh(data, pivotIndex + 1, right);
	}
	private static int partitionxh(int[] data, int left, int right) {
		int pivot = data[left], i = left, j = right, temp;
		while (i != j) {
			while (i < j && data[j] > pivot) {
				j--;
			}
			while( i<j && data[i] <= pivot) {
				i++;
			}
			if (i < j) {
				temp = data[i];
				data[i] = data[j];
				data[j] = temp;
			}
		}
		data[left] = data[i];
		data[i] = pivot;
		return i;
	}
}
