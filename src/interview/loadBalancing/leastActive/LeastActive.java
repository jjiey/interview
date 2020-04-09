package interview.loadBalancing.leastActive;

import interview.loadBalancing.util.ServerIps;

import java.util.*;

public class LeastActive {

    private static String getServer() {
        Random random = new Random();
        // 找出当前最小活跃数
        Optional<Integer> minValueOptional = ServerIps.ACTIVITY_LIST.values().stream().min(Comparator.naturalOrder());
        if (minValueOptional.isPresent()) {
            Integer minValue = minValueOptional.get();
            // 当前活跃数最小的服务器ip集合
            List<String> minActivityIps = new ArrayList<>();
            ServerIps.ACTIVITY_LIST.forEach((ip, activity) -> {
                if (activity.equals(minValue)) {
                    minActivityIps.add(ip);
                }
            });
            // 如果最小活跃数的ip有多个，则根据权重来选，权重大的优先
            if (minActivityIps.size() > 1) {
                // 过滤处对应的ip和权重
                Map<String, Integer> weightList = new LinkedHashMap<>();
                ServerIps.WEIGHT_LIST.forEach((ip, weight) -> {
                    if (minActivityIps.contains(ip)) {
                        weightList.put(ip, ServerIps.WEIGHT_LIST.get(ip));
                    }
                });
                // 总权重
                int totalWeight = 0;
                // 所有权重是否都相等。如果所有权重都相等，那么随机一个ip即可
                boolean sameWeight = true;
                Object[] weights = weightList.values().toArray();
                for (int i = 0; i < weights.length; i++) {
                    Integer weight = (Integer) weights[i];
                    totalWeight += weight;
                    if (sameWeight && i > 0 && !weight.equals(weights[i - 1])) {
                        sameWeight = false;
                    }
                }
                if (!sameWeight) {
                    int randomPos = random.nextInt(totalWeight);
                    for (String ip : weightList.keySet()) {
                        Integer value = weightList.get(ip);
                        if (randomPos < value) {
                            return ip;
                        }
                        randomPos -= value;
                    }
                }
                return (String) weightList.keySet().toArray()[random.nextInt(weightList.size())];
            }
            return minActivityIps.get(0);
        }
        return (String) ServerIps.WEIGHT_LIST.keySet().toArray()[random.nextInt(ServerIps.WEIGHT_LIST.size())];
    }

    public static void main(String[] args) {
        // 连续调用10次
        for (int i = 0; i < 10; i++) {
            System.out.println(getServer());
        }
    }
}
