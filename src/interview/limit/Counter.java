package interview.limit;

import java.util.LinkedList;

public class Counter {

    /**
     * 服务访问次数，可以放在Redis中，实现分布式系统的访问计数
     */
    Long counter = 0L;
    /**
     * 使用LinkedList来记录滑动窗口的10个格子
     */
    LinkedList<Long> ll = new LinkedList<>();

    public static void main(String[] args) {
        Counter counter = new Counter();
        counter.doCheck();
    }

    private void doCheck() {
        while (true) {
            ll.addLast(counter);
            if (ll.size() > 10) {
                ll.removeFirst();
            }
            // 比较最后一个和第一个，两者相差一秒
            if ((ll.peekLast() - ll.peekFirst()) > 100) {
                // To limit rate
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
