package classloader;

public class Demo {

    private static Demo demo = new Demo();

    private static int a = 0;
    private static int b;

    private Demo() {

        a++;
        b++;
    }

    public static Demo getDemo() {
        return demo;
    }

    public int getA() {
        return a;
    }
    public void setA(int a) {
        Demo.a = a;
    }

    public int getB() {
        return b;
    }
    public void setB(int b) {
        Demo.b = b;
    }
}
