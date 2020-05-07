package interview.sort;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

public class SortTestHelper {

    private SortTestHelper() {

    }

    /**
     * 生成有n个元素的随机数组,每个元素的随机范围为[rangeL, rangeR]
     * @param n 生成的随机数组元素数量
     * @param rangeL 生成的随机数组随机范围
     * @param rangeR 生成的随机数组随机范围
     * @return 生成的随机数组
     */
    public static int[] generateRandomArray(int n, int rangeL, int rangeR) {
        if (rangeL > rangeR) {
            throw new IllegalArgumentException("rangeL must <= rangeR");
        }
        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = (int) (Math.random() * (rangeR - rangeL + 1) + rangeL);
        }
        return data;
    }

    /**
     * 判断数组是否有序
     * @param data 待判断数组
     * @return true代表有序，false代表无序
     */
    public static boolean isSorted(int[] data) {
        int n = data.length;
        for (int i = 0; i < n - 1; i++) {
            if (data[i] > data[i + 1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 测试sort排序算法排序数组所得到结果的正确性和算法运行时间
     * @param sortClassName 排序算法类名称
     * @param data 测试用的数据
     *
     * 通过Java的反射机制，通过排序的类名，运行排序函数
     *
     * todo
     */
    public static void testSort(String sortClassName, int[] data) {
        try {
            // 通过sortClassName获得排序函数的Class对象
            Class sortClass = Class.forName(sortClassName);
            // 通过排序函数的Class对象获得排序方法
//            Method sortMethod = sortClass.getMethod("sort", new Class[]{Integer[].class});
            Method sortMethod = null;
//            for (Method method : sortClass.getDeclaredMethods()) {
//                if (method.getParameterTypes().equals(new Class[]{Integer[].class}) && method.getReturnType().equals(Integer[].class)) {
//                    sortMethod = method;
//                    break;
//                }
//            }
            if (Objects.isNull(sortMethod)) {
                throw new RuntimeException("没有找到排序算法");
            }
            // 排序参数只有一个，是可比较数组arr
            long startTime = System.currentTimeMillis();
            // 调用排序函数
            sortMethod.invoke(null, new Object[]{data});
            long endTime = System.currentTimeMillis();
            if (!isSorted(data)) {
                throw new RuntimeException("排序后不是有序数组");
            }
            System.out.println(sortClass.getSimpleName() + " : " + (endTime - startTime) + "ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成一个近乎有序的数组
     * 首先生成一个含有[0...n-1]的完全有序数组, 之后随机交换swapTimes对数据
     * @param n
     * @param swapTimes 定义了数组的无序程度：swapTimes == 0 时, 数组完全有序；swapTimes 越大, 数组越趋向于无序
     * @return
     */
    public static int[] generateNearlyOrderedArray(int n, int swapTimes){
        int[] arr = new int[n];
        for ( int i = 0 ; i < n ; i ++ ) {
            arr[i] = i;
        }
        int temp;
        for( int i = 0 ; i < swapTimes ; i ++ ){
            int a = (int)(Math.random() * n);
            int b = (int)(Math.random() * n);
            temp = arr[a];
            arr[a] = arr[b];
            arr[b] = temp;
        }
        return arr;
    }

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(generateRandomArray(10, 10, 20)));
        testSort("interview.sort.SelectionSort", generateRandomArray(10, 10, 20));
    }
}
