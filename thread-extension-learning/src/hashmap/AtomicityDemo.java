package hashmap;

import java.util.concurrent.atomic.AtomicInteger;

// 原子性
public class AtomicityDemo extends Thread {

    // 没有原子性
//    private static int count;

    // 原子对象
    private static AtomicInteger count = new AtomicInteger(0);

    private static void addCount() {
        for (int i = 0; i < 1000; i++) {
//            count ++;
            count.incrementAndGet();
        }
        System.out.println(count);
    }

    @Override
    public void run() {
        addCount();
    }

    public static void main(String[] args) {
        int len = 10;
        // 创建len个线程
        AtomicityDemo[] atomicityDemos = new AtomicityDemo[len];
        // 初始化
        for (int i = 0; i < len; i ++) {
            atomicityDemos[i] = new AtomicityDemo();
        }
        // 开启线程
        for (int i = 0; i < len; i ++) {
            atomicityDemos[i].start();
        }
    }
}
