package interview.my;

public class StringTest {

    // String对象的intern方法会得到字符串对象在常量池中对应的版本的引用（如果常量池中有一个字符串与String对象的equals结果是true），如果常量池中没有对应的字符串，则该字符串将被添加到常量池中，然后返回常量池中字符串的引用
    // 如果有大量需要进行字符串拼接的操作，最好还是使用StringBuffer或StringBuilder进行。是少量的字符串可以使用+
    public static void main(String[] args) {
        String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program" + "ming";
        System.out.println(s1 == s2); //false
        System.out.println(s1 == s3); //true
        System.out.println(s1 == s1.intern()); //true
    }

}
