package interview.loadBalancing.roundRobin;

import interview.loadBalancing.util.Sequence;
import interview.loadBalancing.util.ServerIps;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * 权重轮询算法
 */
public class WeightRoundRobin1 {

    private static String getServer() {
        // 总权重
        int totalWeight = 0;
        // 所有权重是否都相等。如果所有权重都相等，那么随机一个ip即可
        boolean sameWeight = true;
        Object[] weights = ServerIps.WEIGHT_LIST.values().toArray();
        // 生成一个随机数作为list的下标
        List<String> ips = new ArrayList<>();
        Set<String> ipKeys = ServerIps.WEIGHT_LIST.keySet();
        int i = 0;
        for (String ip : ipKeys) {
            Integer weight = ServerIps.WEIGHT_LIST.get(ip);
            // 按权重进行复制
            for (int j = 0; j < weight; j++) {
                ips.add(ip);
            }
            totalWeight += weight;
            if (sameWeight && i > 0 && !weight.equals(weights[i - 1])) {
                sameWeight = false;
            }
            i ++;
        }
        if (!sameWeight) {
            // 调用Sequence.getAndIncrement()
            return ips.get(Sequence.getAndIncrement() % totalWeight);
            // 调用Sequence.incrementAndGet()
//            Integer offset = Sequence.incrementAndGet() % totalWeight;
//            offset = offset == 0 ? totalWeight : offset;
//            return ips.get(offset - 1);
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
