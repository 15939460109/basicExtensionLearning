package demo;

// 有序性
public class ReorderDemo {

//    static int a = 0, b = 0;
//    static int x = 0, y = 0;

    static volatile int a = 0, b = 0;
    static volatile int x = 0, y = 0;

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            reorder();
        }
    }

    static void reorder() throws InterruptedException {
        Thread t1 = new Thread((Runnable) () -> {
           a = 1;
           x = b;
        });
        Thread t2 = new Thread((Runnable) () -> {
            b = 1;
            y = a;
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        if (x == 0 && y ==0) {
            System.out.println("(" + x + "," + y + ")");
        }
        x = 0;
        y = 0;
        a = 0;
        b = 0;
    }
}
