package interview.my;

/**
 * 字符串反转
 */
public class Example4 {

    /**
     * StringBuilder的reverse方法
     * @param originStr
     * @return
     */
    public static String reverse1(String originStr) {
        return new StringBuilder(originStr).reverse().toString();
    }

    /**
     * StringBuffer的reverse方法
     * @param originStr
     * @return
     */
    public static String reverse2(String originStr) {
        return new StringBuffer(originStr).reverse().toString();
    }

    /**
     * 将字符串转化为char类型数组，循环将各个字符重新拼接
     * @param originStr
     * @return
     */
    public static String reverse3(String originStr) {
        char[] chars = originStr.toCharArray();
        String reverse = "";
        for (int i = chars.length - 1; i >= 0; i--) {
            reverse += chars[i];
        }
        return reverse;
    }

    /**
     * String的CharAt方法取出各个字符重新拼接
     * @param originStr
     * @return
     */
    public static String reverse4(String originStr) {
        String reverse = "";
        int length = originStr.length();
        for (int i = 0; i < length; i++) {
            reverse = originStr.charAt(i) + reverse;
        }
        return reverse;
    }

    /**
     * 递归实现字符串反转
     * @param originStr
     * @return
     */
    public static String reverse5(String originStr) {
        if(originStr == null || originStr.length() <= 1)
            return originStr;
        return reverse5(originStr.substring(1)) + originStr.charAt(0);
    }

    public static void main(String[] args) {
        String originStr = "abcdefg";
        System.out.println(originStr);
        System.out.println(reverse1(originStr));
        System.out.println(reverse2(originStr));
        System.out.println(reverse3(originStr));
        System.out.println(reverse4(originStr));
        System.out.println(reverse5(originStr));
    }

}
