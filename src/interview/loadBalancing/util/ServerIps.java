package interview.loadBalancing.util;

import java.util.*;

public class ServerIps {

    public static final List<String> LIST = Arrays.asList(
            "192.168.0.1",
            "192.168.0.2",
            "192.168.0.3",
            "192.168.0.4",
            "192.168.0.5",
            "192.168.0.6",
            "192.168.0.7",
            "192.168.0.8",
            "192.168.0.9",
            "192.168.0.10"
    );

    public static final Map<String, Integer> WEIGHT_LIST = new HashMap<String, Integer>(){{
        // 权重之和为50
        put("192.168.0.1", 1);
        put("192.168.0.2", 8);
        put("192.168.0.3", 3);
        put("192.168.0.4", 6);
        put("192.168.0.5", 5);
        put("192.168.0.6", 5);
        put("192.168.0.7", 4);
        put("192.168.0.8", 7);
        put("192.168.0.9", 2);
        put("192.168.0.10", 9);
    }};

    private static final Map<String, Weight> WEIGHT_MAP = new HashMap<>(WEIGHT_LIST.size());

    public static Map<String, Weight> getWeightMap() {
        if (WEIGHT_MAP.isEmpty()) {
            // 初始化weightMap时将currentWeight赋值为weight
            WEIGHT_LIST.forEach((key, value) -> WEIGHT_MAP.put(key, new Weight(key, value, value)));
        }
        return WEIGHT_MAP;
    }

    public static final Map<String, Integer> ACTIVITY_LIST = new LinkedHashMap<String, Integer>(){{
        // 服务器当前活跃数
        put("192.168.0.1", 2);
        put("192.168.0.2", 0);
        put("192.168.0.3", 1);
        put("192.168.0.4", 3);
        put("192.168.0.5", 0);
        put("192.168.0.6", 1);
        put("192.168.0.7", 4);
        put("192.168.0.8", 2);
        put("192.168.0.9", 7);
        put("192.168.0.10", 3);
    }};
}
