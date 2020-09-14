package interview.loop;

/**
 * 二分查找
 *
 * 一定要明确变量的定义
 * 参数用int[]，而不用List<Integer>，因为数组可以随机访问，list底层是链表，要到中间还是要从头遍历，这样二分查找没有意义
 */
public class BinarySearch {

    /**
     * 在有序数组 nums 里查找元素 target
     * @param nums 有序数组
     * @param target 要查找的元素
     * @return 元素 target 的索引，如果没找到返回 -1
     *
     * r = nums.length - 1，java类库中用的就是arr.length - 1
     */
    private int binarySearch(int[] nums, int target) {
        // 在[l...r]的范围里寻找target   第一个边界问题：r 的取值
        int l = 0, r = nums.length - 1;
        // 当 l == r时,区间[l...r]依然是有效的   第二个边界问题：l < r or l <= r ？需要明确 r 的定义
        while (l <= r) {
            /*
             * about The value of mid:
             * first: int mid = (l + r) / 2; problem: (l + r) may overflow!
             * second: int mid = l / 2 + r / 2; problem: eg: l = 1, r = 3, so mid = 1, but mid should be 2!
             * third: int mid = l + (r - l) / 2;
             */
            int mid = l + (r - l) / 2;
            if (target > nums[mid]) {
                // target在[mid+1...r]中; [l...mid]一定没有target   第三个边界问题：l = mid or l = mid + 1 ？需要明确 l 的定义
                l = mid + 1;
            } else if (target < nums[mid]) {
                // target在[l...mid-1]中; [mid...r]一定没有target
                r = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 在有序数组 nums 里查找元素 target
     * @param nums 有序数组
     * @param target 要查找的元素
     * @return 元素 target 的索引，如果没找到返回 -1
     *
     * r = nums.length
     * 半开半闭有一定的好处：
     * [l, r) + [r, c) = [l, c)
     * r - l = len([l, r))
     * [l, l) ==> empty range
     */
    private int binarySearch2(int[] nums, int target) {
        // 在[l...r)的范围里寻找target
        int l = 0, r = nums.length;
        // Loop invariant: [l, r) is l valid range. (l <= r)
        // target may only be within rang [l, r).
        // 当 l == r 时, 区间[l...r)是一个无效区间
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (target > nums[mid]) {
                // target在[mid+1...r)中; [l...mid]一定没有target
                l = mid + 1;
            } else if (target < nums[mid]) {
                // target在[l...mid)中; [mid...r)一定没有target
                r = mid;
            } else {
                return mid;
            }
        }
        return -1;
    }

    private int binarySearch3(int[] nums, int target) {
        // 在[l...r]的范围里寻找target
        return binarySearchHelp(nums, 0, nums.length - 1, target);
    }

    private int binarySearchHelp(int[] nums, int l, int r, int target) {
        if (l > r) {
            return -1;
        }
        int mid = l + (r - l) / 2;
        if (nums[mid] > target) {
            return binarySearchHelp(nums, l, mid - 1, target);
        } else if (nums[mid] < target) {
            return binarySearchHelp(nums, mid + 1, r, target);
        }
        return mid;
    }

    private int binarySearch4(int[] nums, int target) {
        // 在[l...r)的范围里寻找target
        return binarySearchHelp2(nums, 0, nums.length, target);
    }

    private int binarySearchHelp2(int[] nums, int l, int r, int target) {
        if (l >= r) {
            return -1;
        }
        int mid = l + (r - l) / 2;
        if (nums[mid] > target) {
            return binarySearchHelp2(nums, l, mid, target);
        } else if (nums[mid] < target) {
            return binarySearchHelp2(nums, mid + 1, r, target);
        }
        return mid;
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
