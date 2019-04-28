package interview.my;

public class C {

    /**
     * B extends A
     * 创建对象时构造器的调用顺序是：
     * （1）静态成员，
     * （2）父类构造器，
     * （3）非静态成员，
     * （4）自身构造器。
     */
    public static void main(String[] args) {
        A ab = new B();
        ab = new B();
    }

}
