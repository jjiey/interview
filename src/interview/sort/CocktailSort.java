package interview.sort;

import java.util.Arrays;

/**
 * 鸡尾酒排序
 */
public class CocktailSort {

    /**
     * cocktailSort
     * @param data 待排序数组
     * 第1轮从左到右，第二轮从右到左，第三轮从左到右... ...
     */
    private static void cocktailSort(int[] data) {
        int temp;
        // 因为一大轮要进行正反两小轮排序，所以大论数要除以2
        for (int i = 0; i < data.length / 2; i++) {
            // 有序标记，每一轮的初始值都是true
            boolean isSorted = true;
            // 奇数轮，从左到右
            for (int j = i; j < data.length - 1 - i; j ++) {
                if (data[j] > data[j + 1]) {
                    // swap
                    temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                    // 因为有元素进行交换，所以不是有序的，标记变为false
                    isSorted = false;
                }
            }
            System.out.println("第 " + (i + 1) + " 轮【奇】数轮结束：" + Arrays.toString(data));
            if (isSorted) {
                break;
            }
            // 在偶数轮前，将isSorted重新标记为true
            isSorted = true;
            for (int j = data.length - 1 - i; j > i; j --) {
                if (data[j] < data[j - 1]) {
                    // swap
                    temp = data[j];
                    data[j] = data[j - 1];
                    data[j - 1] = temp;
                    // 因为有元素进行交换，所以不是有序的，标记变为false
                    isSorted = false;
                }
            }
            System.out.println("第 " + (i + 1) + " 轮【偶】数轮结束：" + Arrays.toString(data));
            if (isSorted) {
                break;
            }
        }
    }

    /**
     * cocktailSort优化
     * @param data 待排序数组
     * 记录有序区，思路参见BubbleSort.bubbleSort2注释
     */
    private static void cocktailSort2(int[] data) {
        int temp;
        // 奇数轮最后一次交换位置
        int oddLastChangeIndex = 0;
        // 偶数轮最后一次交换位置
        int evenLastChangeIndex = 0;
        // 奇数轮无序数组的边界，每次比较只需要比到这里为止，第一次边界为最后一个值
        int oddSortBorder = data.length - 1;
        // 偶数轮无序数组的边界，每次比较只需要比到这里为止，第一次边界为第一个值
        int evenSortBorder = 0;
        for (int i = 0; i < data.length / 2; i++) {
            // 有序标记，每一轮的初始值都是true
            boolean isSorted = true;
            // 奇数轮，从左到右
            for (int j = i; j < oddSortBorder; j ++) {
                if (data[j] > data[j + 1]) {
                    // swap
                    temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                    // 因为有元素进行交换，所以不是有序的，标记变为false
                    isSorted = false;
                    // 把无序数组的边界更新为最后一次交换元素的位置
                    oddLastChangeIndex = j;
                }
            }
            System.out.println("第 " + (i + 1) + " 轮【奇】数轮结束：" + Arrays.toString(data));
            oddSortBorder = oddLastChangeIndex;
            if (isSorted) {
                break;
            }
            // 在偶数轮前，将isSorted重新标记为true
            isSorted = true;
            for (int j = data.length - 1 - i; j > evenSortBorder; j --) {
                if (data[j] < data[j - 1]) {
                    // swap
                    temp = data[j];
                    data[j] = data[j - 1];
                    data[j - 1] = temp;
                    // 因为有元素进行交换，所以不是有序的，标记变为false
                    isSorted = false;
                    // 把无序数组的边界更新为最后一次交换元素的位置
                    evenLastChangeIndex = j;
                }
            }
            System.out.println("第 " + (i + 1) + " 轮【偶】数轮结束：" + Arrays.toString(data));
            evenSortBorder = evenLastChangeIndex;
            if (isSorted) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] data = {2, 3, 4, 5, 6, 7, 8, 1};
//        cocktailSort(data);
        cocktailSort2(data);
        System.out.println(Arrays.toString(data));
    }

}
