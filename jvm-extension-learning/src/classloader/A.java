package classloader;

public class A {

    static {
        System.out.println("111");
    }

    static {
        System.out.println("222");
    }

    static {
        System.out.println("333");
    }

    final static String msg = "abcd";
}
