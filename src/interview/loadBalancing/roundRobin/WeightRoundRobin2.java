package interview.loadBalancing.roundRobin;

import interview.loadBalancing.util.Sequence;
import interview.loadBalancing.util.ServerIps;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

/**
 * 权重轮询算法
 */
public class WeightRoundRobin2 {

    private static String getServer() {
        // 总权重
        int totalWeight = 0;
        // 所有权重是否都相等。如果所有权重都相等，那么随机一个ip即可
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
        Set<String> ipKeys = ServerIps.WEIGHT_LIST.keySet();
        if (!sameWeight) {
            // 调用Sequence.getAndIncrement()
            Integer offset = Sequence.getAndIncrement() % totalWeight;
            for (String ip : ipKeys) {
                Integer weight = ServerIps.WEIGHT_LIST.get(ip);
                if (offset < weight) {
                    return ip;
                }
                offset -= weight;
            }
            // 调用Sequence.incrementAndGet()
//            Integer offset = Sequence.incrementAndGet() % totalWeight;
//            offset = offset == 0 ? totalWeight : offset;
//            for (String ip : ipKeys) {
//                Integer weight = ServerIps.WEIGHT_LIST.get(ip);
//                if (offset <= weight) {
//                    return ip;
//                }
//                offset -= weight;
//            }
        }
        return new ArrayList<>(ipKeys).get(new Random().nextInt(ServerIps.WEIGHT_LIST.size()));
    }

    public static void main(String[] args) {
        // 连续调用10次
        for (int i = 0; i < 10; i++) {
            System.out.println(getServer());
        }
    }
}
