package interview.my;

public class ThreadPrintIntNormal2 implements Runnable {

    private static final Object LOCK = new Object();

    private static int count = 0;

    private static int[] input;

    private int threadNo;

    private Foo foo;

    public ThreadPrintIntNormal2(int threadNo, Foo foo) {
        this.threadNo = threadNo;
        this.foo = foo;
    }

    @Override
    public void run() {
        synchronized (LOCK) {
            while (threadNo % input.length != threadNo) {
                try {
                    LOCK.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // 调用foo的方法
            if (threadNo == 0) {
                foo.one();
            } else if (threadNo == 1) {
                foo.two();
            } else if (threadNo == 2) {
                foo.three();
            }
            count++;
            LOCK.notifyAll();
        }
    }

    public static void main(String[] args) {
        input = new int[]{3, 1, 2};
        Foo foo = new Foo();
        for (int i = 0; i < input.length; i++) {
            new Thread(new ThreadPrintIntNormal2(input[i] - 1, foo)).start();
        }
    }
}

class Foo {
    public void one() {
        System.out.print("one");
    }
    public void two() { System.out.print("two"); }
    public void three() { System.out.print("three"); }
}
