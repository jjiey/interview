package interview.leetcode.easy;

/**
 * 整数反转
 * Reverse Integer
 */
public class Solution7 {

    public static void main(String[] args) {
        Solution7 lc = new Solution7();
        int x = 2147483647;
        System.out.println(lc.reverse(x));
    }

    private int reverse(int x) {
        // 防止下面 -x 溢出报错
        if (x == Integer.MIN_VALUE || x == 0) {
            return 0;
        }
        // 负数
        if (x < 0) {
            // 将最后结果转为负数
            return -reverse(-x);
        }
        int res = 0;
        // 每次得到 x 的最后一位数字，并将其作为结果 res 中的当前最高位
        while (x != 0) {
            // Integer.MAX_VALUE / 10 是214748364，因为假如 res 大于 214748364，那么 res = res * 10 + x % 10 这一行就会出现异常，因为 res * 10 的结果一定是大于 2147483647 的，程序执行不完就出错了
            if (res > Integer.MAX_VALUE / 10) {
                return 0;
            }
            res = res * 10 + x % 10;
            x /= 10;
        }
        return res;
    }

    // 骚操作判断溢出
    // 骚操作2：转成字符串处理
    // 骚操作3：溢出直接交给try/catch处理
    // 骚操作4：参数x肯定是有效的，所以用long来接没问题

    /**
     * 使用数学方法，在没有辅助堆栈 / 数组的帮助下 pop 和 push 数字：
     * 1.pop x 的最后一个数字
     * pop = x % 10;
     * x /= 10;
     * 2.将 x 的最后一个数字 push 到 res 的后面
     * temp = res * 10 + pop;
     * res = temp;
     * 3.temp = res * 10 + pop 会不会溢出？
     * temp = res * 10 + pop => res = (temp - pop) / 10
     * 假设 res 是正数：
     * 如果 temp = res * 10 + pop 会溢出，那么 res >= (temp - pop) / 10
     * 如果 res > (temp - pop) / 10，那么 temp = res * 10 + pop 必定会溢出；
     * 如果 res = (temp - pop) / 10，那么当 pop > 7（其实就是 Integer.MAX_VALUE 的个位数） 时 temp = res * 10 + pop 才会溢出
     * 假设 res 是负数：同理得当 pop < -8（其实就是 Integer.MIN_VALUE 的个位数） 时才会溢出
     */
    private int reverse2(int x) {
        int res = 0;
        while (x != 0) {
            int pop = x % 10;
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && pop > Integer.MAX_VALUE % 10)) {
                return 0;
            }
            if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && pop < Integer.MIN_VALUE % 10)) {
                return 0;
            }
            res = res * 10 + pop;
            x /= 10;
        }
        return res;
    }

    /**
     * 优化reverse2：Integer最大值和最小值开头的数字只能是1或2，而给定的参数x本身是合法的，所以如果有最后一次循环，pop的值一定为1或2，所以(rev == INT_MAX / 10 && pop > 7)和(rev == INT_MIN / 10 && pop < -8)判断可以省略
     */
    private int reverse100(int x) {
        int res = 0;
        while (x != 0) {
            int pop = x % 10;
            if (res > Integer.MAX_VALUE / 10 || res < Integer.MIN_VALUE / 10) {
                return 0;
            }
            res = res * 10 + pop;
            x /= 10;
        }
        return res;
    }

    private int reverse3(int x) {
        int ans = 0;
        while (x != 0) {
            // 判断是否溢出
            if ((ans * 10) / 10 != ans) {
                return 0;
            }
            // 当ans * 10未溢出时, ans * 10结果个位为0, x % 10的取值范围是0~9, 所以 ans * 10 + x % 10 一定不会溢出
            ans = ans * 10 + x % 10;
            x /= 10;
        }
        return ans;
    }

    private int reverse4(int x) {
        long result = 0;
        while (x != 0) {
            int pop = x % 10;
            result = result * 10 + pop;
            if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
                return 0;
            }
            x /= 10;
        }
        return (int) result;
    }

    private int reverse5(int x) {
        // Integer.MIN_VALUE的绝对值越界了，而且其翻转的结果超过了int范围，直接处理
        if (x == Integer.MIN_VALUE || x == 0) {
            return 0;
        }
        String strReverse = new StringBuilder(Integer.toString(Math.abs(x))).reverse().toString();
        long result = Long.parseLong(strReverse);
        // 负数转回负数
        if(x < 0) {
            result = -result;
        }
        // 溢出返回0
        if(result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
            return 0;
        }
        return (int) result;
    }

    /**
     * java没有做数字溢出检测，但是可以用jdk或者第三方提供的工具类，比如：Math.addExact(1, 2) 或 org.apache.commons.math3.util.ArithmeticUtils.addAndCheck(1, 2)
     */
    private int reverse6(int x) {
        int res = 0;
        while (x != 0) {
            try {
                res = Math.addExact(Math.multiplyExact(res, 10), x % 10);
                // 不能像下面这样写，因为 res * 10 已经溢出了
                // res = Math.addExact(res * 10, x % 10);
            } catch (ArithmeticException e) {
                return 0;
            }
            x /= 10;
        }
        return res;
    }
}
