package interview.my;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 红包金额分配算法
 */
public class RedPackage {

    public static void main(String[] args) {
        RedPackage rp = new RedPackage();
        int totalAmount =1000;
        int totalPeopleNum = 10;
        System.out.println(rp.divide(totalAmount, totalPeopleNum));
    }

    /**
     * 二倍均值法，能够保证每次随机金额的平均值是相等的
     * @param totalAmount 红包总数
     * @param totalPeopleNum 总人数
     * @return 分配好的红包金额
     */
    private List<Integer> divide(int totalAmount, int totalPeopleNum) {
        List<Integer> amountList = new ArrayList<>();
        int restAmount = totalAmount;
        int restPeopleNum = totalPeopleNum;
        Random random = new Random();
        // 随机计算总人数 - 1个人的红包大小，最后一个人的红包大小为剩余金额
        for (int i = 0; i < totalPeopleNum - 1; i++) {
            // 每次抢到的金额 = 随机区间[0.01, restAmount / restPeopleNum * 2 - 0.01]元
            // random.nextInt(n)方法返回[0, n)的int值
            // 为什么要- 2，又为什么要+ 1（+ 1也许是为了怕抢到0元）
            int amount = random.nextInt(restAmount / restPeopleNum * 2 - 2) + 1;
            restAmount -= amount;
            restPeopleNum --;
            amountList.add(amount);
        }
        amountList.add(restAmount);
        return amountList;
    }
}
