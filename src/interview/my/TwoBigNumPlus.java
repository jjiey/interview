package interview.my;

import java.util.Arrays;

/**
 * 两个大整数相乘
 */
public class TwoBigNumPlus {

    public static void main(String[] args) {

    }

    private String bigNumberPlus(String bigNumberA, String bigNumberB) {
        if (bigNumberA == null || bigNumberB == null) {
            throw new IllegalArgumentException("参数不能为空");
        }
        if ("0".equals(bigNumberA) || "0".equals(bigNumberB)) {
            return "0";
        }
        if ("1".equals(bigNumberA)) {
            return bigNumberB;
        }
        if ("1".equals(bigNumberB)) {
            return bigNumberA;
        }
        // 1.把两个大整数用数组逆序存储
        int[] arrayA = getReverseArrayByString(bigNumberA, bigNumberA.length());
        int[] arrayB = getReverseArrayByString(bigNumberB, bigNumberB.length());
        // 2.构建result数组
        int[] result = new int[bigNumberA.length() + bigNumberB.length()];
        //
        int maxLength = bigNumberA.length() > bigNumberB.length() ? bigNumberA.length() : bigNumberB.length();
        String[] tempResult = new String[maxLength];
        //
        for (int i = 0; i < arrayB.length; i++) {
            // String temp = "";
            for (int j = 0; j < arrayA.length; j++) {
                // temp += arrayA[j] * arrayB[i] * 10 * i
            }
            // tempResult[i] = temp;
        }
        return null;
    }

    /**
     * String 转为 数组逆序
     */
    private int[] getReverseArrayByString(String bigNumber, int length) {
        int[] array = new int[length];
        for (int i = 0; i < bigNumber.length(); i++) {
            array[i] = bigNumber.charAt(bigNumber.length() - 1 - i) - '0';
        }
        return array;
    }

    /**
     * 数组逆序 转为 String
     */
    private String getReverseStringByArray(int[] array) {
        StringBuilder sb = new StringBuilder();
        // 是否找到大整数的最高有效位
        boolean findFirst = false;
        for (int i = array.length - 1; i >= 0; i--) {
            if (!findFirst) {
                if (array[i] == 0) {
                    continue;
                }
                findFirst = true;
            }
            sb.append(array[i]);
        }
        return sb.toString();
    }
}
