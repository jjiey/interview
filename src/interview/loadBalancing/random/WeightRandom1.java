package interview.loadBalancing.random;

import interview.loadBalancing.util.ServerIps;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 权重随机算法
 */
public class WeightRandom1 {

    private static String getServer() {
        // 生成一个随机数作为list的下标
        List<String> ips = new ArrayList<>();
        for (String ip : ServerIps.WEIGHT_LIST.keySet()) {
            Integer weight = ServerIps.WEIGHT_LIST.get(ip);
            // 按权重进行复制
            for (int i = 0; i < weight; i++) {
                ips.add(ip);
            }
        }
        Random random = new Random();
        int randomPos = random.nextInt(ips.size());
        return ips.get(randomPos);
    }

    public static void main(String[] args) {
        // 连续调用10次
        for (int i = 0; i < 10; i++) {
            System.out.println(getServer());
        }
    }
}
