package interview.my;

import java.util.HashMap;
import java.util.stream.Stream;

/**
 * 斐波那契数列
 */
public class FibonacciSeq {

    private static void printFib1(int num) {
        if (num == 1) System.out.print(1 + "\t");
        else if (num == 2) System.out.print(1 + "\t" + 1 + "\t");
        else if (num >= 3) {
            System.out.print(1 + "\t" + 1 + "\t");
            int a = 1, b = 1, c;
            for (int i = 3; i <= num; i ++) {
                c = a + b;
                a = b;
                b = c;
                System.out.print(c + "\t");
            }
        }
    }

    private static void printFib1_1(int num) {
        for(int i = 1; i <= num; i ++) {
            System.out.print(getFib1(i) + "\t");
        }
    }

    private static void printFib2(int num) {
        if (num == 1) System.out.print(1 + "\t");
        else if (num == 2) System.out.print(1 + "\t" + 1 + "\t");
        else if (num >= 3) {
            int[] arr = new int[num];
            arr[0] = 1;
            arr[1] = 1;
            for (int i = 0; i < arr.length; i++) {
                if (i > 1) {
                    arr[i] = arr[i - 2] + arr[i - 1];
                }
                System.out.print(arr[i] + "\t");
            }
        }
    }

    private static void printFib3(int num) {
        for(int i = 1; i <= num; i ++) {
            System.out.print(getFib1(i) + "\t");
        }
    }

    /**
     * getFib1：递归
     * @param num
     * @return
     */
    public static long getFib1(int num) {
        if (num <= 0) throw new RuntimeException("The input parameter is less than 1");
        if(num == 1 || num == 2) return 1;
        return getFib1(num - 2) + getFib1(num - 1);
    }

    /**
     * getFib2优化getFib1：递归+HashMap缓存
     * @param num
     * @return
     */
    public static long getFib2(int num) {
        if (num <= 0) throw new RuntimeException("The input parameter is less than 1");
        if(num == 1 || num == 2) return 1;
        HashMap<Integer, Long> map = new HashMap<>();
        if (!map.containsKey(num)) map.put(num, getFib2(num - 2) + getFib2(num - 1));
        return map.get(num);
    }

    /**
     * getFib3优化getFib1：递归+数组缓存
     * 递归+HashMap缓存方法里面的map的key和value分别是Integer和Long对象，这时候计算会有一个自动装拆箱的性能问题。如果在一个循环体自动装拆箱，会创建大量无用的中间对象，这样会增加GC压力，拉低程序的性能。而且HashMap的存取虽然效率都很高，然而还会有自动扩容、取hashCode、hash冲突之后可能最坏O(logn)时间复杂度等等的因素
     * @param num
     * @return
     */
    public static long getFib3(int num) {
        if (num <= 0) throw new RuntimeException("The input parameter is less than 1");
        if(num == 1 || num == 2) return 1;
        long[] arr = new long[8000 + 1];
        if (arr[num] == 0) arr[num] = getFib3(num - 1) + getFib3(num - 2);
        return arr[num];
    }

    /**
     * getFib4优化getFib3：数组缓存+顺序递推，前边都是从后往前推所以需要不停的递归，这里从前往后推
     * 当n很大时，避免报StackOverflowError栈溢出的错误
     * @param num
     * @return
     */
    public static long getFib4(int num) {
        if (num <= 0) throw new RuntimeException("The input parameter is less than 1");
        if(num == 1 || num == 2) return 1;
        long arr[] = new long[num + 1];
        arr[0] = 0;
        arr[1] = 1;
        arr[2] = 1;
        for (int i = 3; i <= num; ++i) arr[i] = arr[i - 1] + arr[i - 2];
        return arr[num];
    }

    /**
     * getFib5优化getFib4：去掉数组缓存
     * @param num
     * @return
     */
    public static long getFib5(int num) {
        if (num <= 0) throw new RuntimeException("The input parameter is less than 1");
        if(num == 1 || num == 2) return 1;
        long a = 1, b = 1, c = 0;
        for (int i = 3; i <= num; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }

    /**
     * getFib6：公式解法
     * 忽略任何逻辑，直接套公式，时间复杂度为O(1)
     * @param num
     */
    public static long getFib6(int num) {
        double temp = Math.sqrt(5.0);
        double result = (Math.pow((1 + temp) / 2, num) - Math.pow((1 - temp) / 2, num)) / temp;
        return (long) result;
    }

    /**
     * getFib7：矩阵解法
     * 没有顺序递推法快
     * @param num
     */
    public static long getFib7(int num) {
        if (num <= 0) throw new RuntimeException("The input parameter is less than 1");
        if(num == 1 || num == 2) return 1;
        long[][] initMatirx = {{1, 1}, {1, 0}};
        long[][] tem = initMatirx;
        for (int i = 1; i < num - 2; i++) {
            tem = matirxMulti(tem, initMatirx);
        }
        return tem[0][0] + tem[1][0];
    }
    private static long[][] matirxMulti(long[][] a, long[][] b) {
        long[][] temp = new long[2][2];
        temp[0][0] = a[0][0] * b[0][0] + a[0][1] * b[1][0];
        temp[0][1] = a[0][0] * b[0][1] + a[0][1] * b[1][1];
        temp[1][0] = a[1][0] * b[0][0] + a[1][1] * b[1][0];
        temp[1][1] = a[1][0] * b[0][1] + a[1][1] * b[1][1];
        return temp;
    }

    /**
     * getFib8：矩阵解法+快速幂
     * 最优，时间复杂度O(logn)
     * @param num
     */
    public static long getFib8(int num) {
        if (num <= 0) throw new RuntimeException("The input parameter is less than 1");
        if(num == 1 || num == 2) return 1;
        long[][] initMatirx = {{1, 1}, {1, 0}};
        long[][] unitMatrix = {{1, 0}, {0, 1}};//单位矩阵
        long[][] result = unitMatrix;
        long[][] tem = initMatirx;
        int m = num - 2;
        while (m != 0) {
            if ((m & 1) == 1) {
                result = matirxMulti(tem, result);
            }
            tem = matirxMulti(tem, tem);
            m >>= 1;
        }
        return result[0][0] + result[1][0];
    }

    private static void printFibFast(int num) {
        for(int i = 1; i <= num; i ++) System.out.print(getFib8(i) + "\t");
    }

    private static void java8lambda(int num) {
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(num)
                .map(t -> t[1])
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
//        printFib1(10);
//        System.out.println();
//        printFib1_1(10);
//        System.out.println();
//        printFib2(10);
//        System.out.println();
//        printFib3(10);
//        System.out.println();
        System.out.println(getFib8(2100000000));

        java8lambda(30);
    }

}
