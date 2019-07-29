package interview.my;

/**
 *
 */
public class Example3 {

    /**
     * 判断一个整数是否为奇数
     * @param num
     */
    public static boolean isOdd(int num) {
        // 一个数&1的结果就是取二进制的最末位，二进制的最末位为0表示该数为偶数，最末位为1表示该数为奇数
        return (num & 1) == 1;
    }

    /**
     * 判断一个整数是否是2的n次方幂
     * @param num
     * @return
     */
    public static boolean powerOf2(int num) {
        // 2的n次方变成二进制后首位为1，其余位都为0
        return (num & (num-1)) == 0;
    }

    public static void main(String[] args) {
        // &:通常用于二进制的取位操作，例如一个数 and 1的结果就是取二进制的最末位。相同位的两个数字都为1，则为1；若有一个不为1，则为0
        // |:通常用于二进制特定位上的无条件赋值，例如一个数or 1的结果就是把二进制最末位强行变成1。相同位只要一个为1即为1
        // >>>:无符号右移，忽略符号位，空位都以0补齐
        System.out.println(isOdd(3));
        System.out.println(isOdd(4));
        System.out.println(powerOf2(3));
        System.out.println(powerOf2(4));
        System.out.println("======测试移位运算符");
        int number = 10;
        // 原始数二进制。输出一个整数的二进制数
        System.out.println(number + " 的二进制数: " + Integer.toBinaryString(number));
        System.out.println("======左移");
        number = number << 1;
        // 左移一位。<<:左移运算符，num << 1,相当于num乘以2
        System.out.println(number);
        System.out.println(Integer.toBinaryString(number));
        System.out.println("======右移");
        number = number >> 1;
        // 右移一位。>>:右移运算符，num >> 1,相当于num除以2
        System.out.println(number);
        System.out.println(Integer.toBinaryString(number));
        System.out.println("===左移是给右边补0，右移是给右边减0");
    }

}
