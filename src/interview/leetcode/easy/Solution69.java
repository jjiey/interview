package interview.leetcode.easy;

/**
 * Sqrt(x)
 * x 的平方根
 */
public class Solution69 {

    public static void main(String[] args) {
        Solution69 lc = new Solution69();
        int x = 8;
//        int res = lc.mySqrt(x);
        float res = lc.mySqrt2(x);
        System.out.println(res);
    }

    /**
     * 调用库函数
     */
    private int mySqrt(int x) {
        return (int) Math.sqrt(x);
    }

    /**
     * 二分法
     */
    private float mySqrt2(int x) {
        if (x < 2) {
            return x;
        }
        long y;
        int left = 2, right = x / 2, mid;
        // 跳出循环条件是 left > right，即返回的是相邻两个数中较小的那一个，就是right
        while (left <= right) {
            mid = left + (right - left) / 2;
            y = (long) mid * mid;
            if (y > x) {
                // -1 防止死循环
                right = mid - 1;
            } else if (y < x) {
                // +1 防止死循环
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return right;
    }

    /**
     * 扩展
     * @param n 保留指定小数
     */
    private float mySqrt3(int x, int n) {
        if (x < 2) {
            return x;
        }
        float left = 0, right = n, mid;
        while (right - left > Math.pow(10, -n)){
            mid = (right + left) / 2;
            float r = mid * mid;
            if(r > n){
                right = mid;
            }else {
                left = mid;
            }
        }
        return (right + left) / 2;
    }

    /**
     * 利用牛顿迭代公式
     */
    public float sqrt2(int x, int n){
        if (x < 2) {
            return x;
        }
        float guess = x;
        while (Math.abs(guess - x / guess) > Math.pow(10, -n)) {
            guess = (guess + x / guess) / 2;
        }
        return guess;
    }

//    private float getLiFangGen(int x, int n){
//        if(x < 2){
//            return 0;
//        }
//        float guess = x;
//        double num2  = ((2*num1)+(num/(num1*num1)))/3;
//        while(Math.abs() > Math.pow(10, -n)){
//            num1 = num2;
//            num2  = ((2*num1)+(num/(num1*num1)))/3;
//            System.out.println(num2);
//        }
//        return num2;
//    }
}
