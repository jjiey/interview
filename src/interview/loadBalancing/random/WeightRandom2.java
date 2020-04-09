package interview.loadBalancing.random;

import interview.loadBalancing.util.ServerIps;

import java.util.Random;

/**
 * 权重随机算法
 */
public class WeightRandom2 {

    private static String getServer() {
        // 总权重
        int totalWeight = 0;
        // 所有权重是否都相等
        boolean sameWeight = true;
        Object[] weights = ServerIps.WEIGHT_LIST.values().toArray();
        // 计算totalWeight，判断sameWeight
        for (int i = 0; i < weights.length; i++) {
            Integer weight = (Integer) weights[i];
            totalWeight += weight;
            if (sameWeight && i > 0 && !weight.equals(weights[i - 1])) {
                sameWeight = false;
            }
        }
        if (!sameWeight) {
            int randomPos = new Random().nextInt(totalWeight);
            for (String ip : ServerIps.WEIGHT_LIST.keySet()) {
                Integer value = ServerIps.WEIGHT_LIST.get(ip);
                if (randomPos < value) {
                    return ip;
                }
                randomPos = randomPos - value;
            }
        }
        // 如果所有权重都相等，那么随机一个ip即可
        return (String) ServerIps.WEIGHT_LIST.keySet().toArray()[new Random().nextInt(ServerIps.WEIGHT_LIST.size())];
    }

    public static void main(String[] args) {
        // 连续调用10次
        for (int i = 0; i < 10; i++) {
            System.out.println(getServer());
        }
    }
}
