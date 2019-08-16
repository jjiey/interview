package interview.my;

/**
 * 两个大整数相加
 */
public class TwoBigNumSum {

    public static void main(String[] args) {
        TwoBigNumSum lc = new TwoBigNumSum();
        String bigNumberA = "426709752318";
        String bigNumberB = "95481253129";
        String result = lc.bigNumberSum(bigNumberA, bigNumberB);
        System.out.println(result);
    }

    private String bigNumberSum(String bigNumberA, String bigNumberB) {
        if ("".equals(bigNumberA)) {
            return bigNumberB;
        }
        if ("".equals(bigNumberB)) {
            return bigNumberA;
        }
        // 1.把两个大整数用数组逆序存储，数组长度 = 较大整数长度 + 1
        int maxLength = bigNumberA.length() > bigNumberB.length() ? bigNumberA.length() : bigNumberB.length();
        int[] arrayA = getReverseArrayByString(bigNumberA, maxLength + 1);
        int[] arrayB = getReverseArrayByString(bigNumberB, maxLength + 1);
        // 2.构建result数组，数组长度 = 较大整数长度 + 1
        int[] result = new int[maxLength + 1];
        // 3.遍历数组，按位相加
        int temp;
        for (int i = 0; i < result.length; i++) {
            temp = result[i] + arrayA[i] + arrayB[i];
            // 判断是否进位
            if (temp >= 10) {
                temp = temp - 10;
                result[i + 1] = 1;
            }
            result[i] = temp;
        }
        // 4.把result数组再次逆序并转成String
        return getReverseStringByArray(result);
    }

    private String bigNumberSum2(String bigNumberA, String bigNumberB) {
        if ("".equals(bigNumberA)) {
            return bigNumberB;
        }
        if ("".equals(bigNumberB)) {
            return bigNumberA;
        }
        // 1.把两个大整数用数组逆序存储，数组长度 = 较大整数长度 + 1
        int maxLength = bigNumberA.length() > bigNumberB.length() ? bigNumberA.length() : bigNumberB.length();
        int[] arrayA = getReverseArrayByString(bigNumberA, maxLength + 1);
        int[] arrayB = getReverseArrayByString(bigNumberB, maxLength + 1);
        // 2.构建result数组，数组长度 = 较大整数长度 + 1
        int[] result = new int[maxLength + 1];
        // 3.遍历数组，按位相加
        int carry = 0;
        int sum;
        for (int i = 0; i < result.length - 1; i++) {
            sum = carry + arrayA[i] + arrayB[i];
            carry = sum / 10;
            result[i] = sum % 10;
        }
        if (carry > 0) {
            result[result.length - 1] = carry;
        }
        // 4.把result数组再次逆序并转成String
        return getReverseStringByArray(result);
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
