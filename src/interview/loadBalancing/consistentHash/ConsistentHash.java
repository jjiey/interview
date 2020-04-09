package interview.loadBalancing.consistentHash;

import interview.loadBalancing.util.ServerIps;

import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistentHash {

    /**
     * K-hash V-ip
     */
    private static SortedMap<Integer, String> virtualNodes = new TreeMap<>();

    /**
     * 虚拟节点个数，即每个真实节点会对应160个虚拟节点
     */
    private static final int VIRTUAL_NODES = 160;

    // “画哈希环”
    static {
        // 对每个真实节点添加虚拟节点，虚拟节点会根据哈希算法进行散列
        for (String ip : ServerIps.LIST) {
            for (int i = 0; i < VIRTUAL_NODES; i++) {
                virtualNodes.put(getHash(ip + "VN" + i), ip);
            }
        }
    }

    private static int getHash(String str) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < str.length(); i++) {
            hash = (hash ^ str.charAt(i)) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        // 如果算出来的值为负数则取其绝对值
        return Math.abs(hash);
    }

    /**
     * @param client 客户端信息
     * 主要步骤：
     * 1.客户端信息进行hash；
     * 2.在哈希环上找大于该hash值的第一个节点；
     * 3.如果哈希环上不存在大于该hash值的节点，说明此时该hash值落在哈希环的顺时针方向的最后一段里，则返回根节点
     */
    private static String getServer(String client) {
        // 获取大于等于该hash值的排好序的子map
        SortedMap<Integer, String> subMap = virtualNodes.tailMap(getHash(client));
        // 获取大于等于该hash值的第一个节点的位置
        Integer nodeIndex = subMap.firstKey();
        // 3.
        nodeIndex = Objects.isNull(nodeIndex) ? virtualNodes.firstKey() : nodeIndex;
        // 返回对应的虚拟节点名称
        return subMap.get(nodeIndex);
    }

    public static void main(String[] args) {
        // 连续调用10次
        for (int i = 0; i < 10; i++) {
            System.out.println(getServer("client" + i));
        }
    }
}
