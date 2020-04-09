package interview.my;

/**
 * 字符串字符全排列
 */
public class PermutationStr {

    public static void main(String[] args) {
        PermutationStr permutationStr = new PermutationStr();
        String str = "abcd";
        permutationStr.permutation(str);
    }

    /**
     * str字符串中的字符进行全排列
     */
    private void permutation(String str){
        if (str == null || str.isEmpty()) {
            return;
        }
        // 从字符0位置开始进行全排列
        permutation(str.toCharArray(), 0);
    }

    private void permutation(char[] chars, int pos) {
        // 到最后一个了
        if (pos == chars.length - 1) {
            System.out.println(chars);
        }
        // for循环保证了递归结束条件
        for (int i = pos; i < chars.length; i++) {
            // 第一个字符和它后面的字符（包括自己）进行交换
            swap(chars, i, pos);
            // 递归求后面所有字符的全排列
            permutation(chars, pos + 1);
            // 由于前面交换了一下，所以chars的内容改变了，需要还原回来，以便第一个字符再与其他字符交换
            swap(chars, i, pos);
        }
    }

    private void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }
}
