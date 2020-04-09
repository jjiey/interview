package interview.loadBalancing.random;

import interview.loadBalancing.util.ServerIps;

import java.util.Random;

/**
 * 简单随机算法
 */
public class Random1 {

    private static String getServer() {
        // 生成一个随机数作为list的下标
        return ServerIps.LIST.get(new Random().nextInt(ServerIps.LIST.size()));
    }

    public static void main(String[] args) {
        // 连续调用10次
        for (int i = 0; i < 10; i++) {
            System.out.println(getServer());
        }
    }
}
