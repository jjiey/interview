package interview.loadBalancing.util;

/**
 * 平滑加权轮询辅助类
 */
public class Weight {

    private String ip;

    /**
     * 固定不变的原始权重
     */
    private Integer weight;

    /**
     * 当前会变化的权重
     */
    private Integer currentWeight;

    public Weight(String ip, Integer weight, Integer currentWeight) {
        this.ip = ip;
        this.weight = weight;
        this.currentWeight = currentWeight;
    }

    // Getter and Setter and toString
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public Integer getWeight() {
        return weight;
    }
    public void setWeight(Integer weight) {
        this.weight = weight;
    }
    public Integer getCurrentWeight() {
        return currentWeight;
    }
    public void setCurrentWeight(Integer currentWeight) {
        this.currentWeight = currentWeight;
    }

    @Override
    public String toString() {
        return "ip : " + ip + ", weight : " + weight + ", currentWeight : " + currentWeight;
    }
}
