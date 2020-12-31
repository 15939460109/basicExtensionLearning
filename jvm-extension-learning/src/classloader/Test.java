package classloader;

// 类的初始化
public class Test {

    public static void main(String[] args) {

//        A a = new A();

        // 先初始化父类，再初始化子类
//        B b = new B();

        // 当用到该类时，该类所有的静态成员都会被初始化
//        System.out.println(A.msg);

//        System.out.println(Demo.getDemo().getA());
//        System.out.println(Demo.getDemo().getB());

        A[] as = new A[10];
    }
}
