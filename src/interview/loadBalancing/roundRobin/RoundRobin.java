package interview.loadBalancing.roundRobin;

import interview.loadBalancing.util.Sequence;
import interview.loadBalancing.util.ServerIps;

/**
 * 简单轮询算法
 */
public class RoundRobin {

    private static String getServer() {
        // 调用Sequence.getAndIncrement()
        return ServerIps.LIST.get(Sequence.getAndIncrement());
        // 调用Sequence.incrementAndGet()
//        return ServerIps.LIST.get(Sequence.incrementAndGet() - 1);
    }

    public static void main(String[] args) {
        // 连续调用10次
        for (int i = 0; i < 10; i++) {
            System.out.println(getServer());
        }
    }
}
