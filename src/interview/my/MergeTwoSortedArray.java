package interview.my;

import java.util.Arrays;

/**
 * 合并两个排好序的数组
 */
public class MergeTwoSortedArray {

    public static void main(String[] args) {
        int[] array1 = {1, 3, 5, 7, 9};
        int[] array2 = {2, 4, 6, 8, 10, 12, 15, 16};
        System.out.println(Arrays.toString(mergeTwoSortedArray(array1, array2)));
    }

    private static int[] mergeTwoSortedArray(int[] array1, int[] array2) {
        if (array1.length == 0) {
            return array2;
        }
        if (array2.length == 0) {
            return array1;
        }
        int[] result = new int[array1.length + array2.length];
        for (int i = 0, j = 0, k = 0; i < result.length; i++) {
            if (j == array1.length) {
                while (k < array2.length) {
                    result[i++] = array2[k++];
                }
                break;
            } else if (k == array2.length) {
                while (j < array1.length) {
                    result[i++] = array1[j++];
                }
                break;
            } else {
                if (array1[j] < array2[k]) {
                    result[i] = array1[j];
                    j++;
                } else {
                    result[i] = array2[k];
                    k++;
                }
            }
        }
        return result;
    }
}
