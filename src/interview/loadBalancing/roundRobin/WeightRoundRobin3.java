package interview.loadBalancing.roundRobin;

import interview.loadBalancing.util.ServerIps;
import interview.loadBalancing.util.Weight;

import java.util.*;

/**
 * 权重轮询算法 - 平滑加权轮询
 */
public class WeightRoundRobin3 {

    private static String getServer() {
        // 总权重
        int totalWeight = ServerIps.WEIGHT_LIST.values().stream().reduce(0, (w1, w2) -> w1 + w2);
        Map<String, Weight> weightMap = ServerIps.getWeightMap();
//        weightMap.values().stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Weight::getCurrentWeight))), ArrayList::new)); // 去重
//        weightMap.values().stream().sorted(Comparator.comparing(Weight::getCurrentWeight)).collect(Collectors.toList()); // 排序
        // 找出currentWeight最大值
        Optional<Weight> maxWeightOptional = weightMap.values().stream().max(Comparator.comparingInt(Weight::getCurrentWeight));
        if (maxWeightOptional.isPresent()) {
            Weight maxCurrentWeight = maxWeightOptional.get();
            // maxCurrentWeight减去总权重和
            maxCurrentWeight.setCurrentWeight(maxCurrentWeight.getCurrentWeight() - totalWeight);
            // 所有Weight的currentWeight统一加上原始权重
            for (Weight weight : weightMap.values()) {
                weight.setCurrentWeight(weight.getCurrentWeight() + weight.getWeight());
            }
            // 返回maxCurrentWeight对应的ip
            return maxCurrentWeight.getIp();
        }
        return null;
    }

    public static void main(String[] args) {
        // 连续调用10次
        for (int i = 0; i < 10; i++) {
            System.out.println(getServer());
        }
    }
}
