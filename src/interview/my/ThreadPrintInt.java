package interview.my;

import java.util.concurrent.Semaphore;

/**
 * 多线程（指定线程数）交替打印数字
 */
public class ThreadPrintInt {

    private static int result = 0;

    private static int maxPrintCount = 100;

    public static void main(String[] args) throws InterruptedException {
        print(10);
    }

    private static void print(int threadCount) throws InterruptedException {
        Thread[] threads = new Thread[threadCount];
        final Semaphore[] syncObjects = new Semaphore[threadCount];
        for (int i = 0; i < threadCount; i++) {
            syncObjects[i] = new Semaphore(1);
            // 最后一个Semaphore不acquire，即只有最后一个Semaphore有一个许可
            if (i != threadCount - 1) {
                syncObjects[i].acquire();
            }
        }
        for (int i = 0; i < threadCount; i++) {
            // 如果是0，拿到的是最后一个Semaphore；否则，拿到的是当前Semaphore的前一个Semaphore；如果把N排个循环的顺序，那其实还是最后一个Semaphore
            final Semaphore lastSemaphore = i == 0 ? syncObjects[threadCount - 1] : syncObjects[i - 1];
            // 当前Semaphore
            final Semaphore curSemaphore = syncObjects[i];
            // 线程编号
            final int threadIndex = i;
            threads[i] = new Thread(() -> {
                try {
                    while (true) {
                        lastSemaphore.acquire();
                        System.out.println("thread" + threadIndex + ": " + result ++);
                        if (result > maxPrintCount) {
                            System.exit(0);
                        }
                        // 保证下次拿到的lastSemaphore是有许可的
                        curSemaphore.release();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            threads[i].start();
        }
    }
}
