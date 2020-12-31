package jvm;

// 栈内存
public class Test4 {

    static int count = 0;

    public void add() {
        count++;
        add();
    }

    public static void main(String[] args) {

        // Xss  初始栈大小

        try {
            Test4 t4 = new Test4();
            t4.add();
        } catch (Throwable t) {
            System.out.println(count);
        }
    }
}
