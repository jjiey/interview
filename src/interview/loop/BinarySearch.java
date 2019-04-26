package interview.loop;

/**
 * 二分查找
 */
public class BinarySearch {

    /**
     * Searches element k in a sorted array.
     * @param arr a sorted array
     * @param k the element to search
     * @return index in arr where k is -1 if not found.
     * 参数用int[]，而不用List<Interger>，因为数组可以随机访问，list底层是链表，要到中间还是要从头遍历，这样二分查找没有意义
     */
    public int binarySearch(int[] arr, int k){
        int a = 0;
        int b = arr.length; // arr.length和arr.length如何选择？两种都可以
        /*
         * 如果选择arr.length，要始终保证k所在区间在a...b-1中
         * 如果选择arr.length，要始终保证k所在区间在a...b中
         * java类库中的就是arr.length - 1
         * 这里使用arr.length
         */
        // Loop invariant: [a, b) is a valid range. (a <= b)
        // k may only be within rang [a, b).
        /*
         * 半开半闭有一定的好处：
         * [a, b) + [b, c) = [a, c)
         * b - a = len([a, b))
         * [a, a) ==> empty range
         */
        while(a < b){
            /*
             * about The value of m:
             * first: int m = (a + b) / 2; problem: (a + b) / 2 may overflow!
             * second: int m = a / 2 + b / 2; problem: eg: a = 1, b = 3, so m = 1, but m should be 2!
             * third: int m = a + (b - a) / 2;
             */
            int m = a + (b - a) / 2;
            // b == a + 1: m = a
            // b == a + 2: m = a + 1
            if (k < arr[m]){
                b = m;
            } else if (k > arr[m]){
                a = m + 1;
            } else {
                return m;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        BinarySearch bs = new BinarySearch();

        System.out.println(bs.binarySearch(new int[]{1, 2, 10, 15,100},15));
        System.out.println(bs.binarySearch(new int[]{1, 2, 10, 15,100},-2));
        System.out.println(bs.binarySearch(new int[]{1, 2, 10, 15,100},101));
        System.out.println(bs.binarySearch(new int[]{1, 2, 10, 15,100},13));
        System.out.println("==========");
        System.out.println(bs.binarySearch(new int[]{},13));
        System.out.println(bs.binarySearch(new int[]{12},13));
        System.out.println(bs.binarySearch(new int[]{13},13));
        System.out.println("==========");
        System.out.println(bs.binarySearch(new int[]{12, 13},13));
        System.out.println(bs.binarySearch(new int[]{12, 13},12));
    }
}
