package currenthashmap;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ConCurrentDemo2 {

//    static HashMap<String, String > map = new HashMap<>();
//    static Hashtable<String, String > map = new Hashtable<>();
    static ConcurrentHashMap<String, String > map = new ConcurrentHashMap<>();

    static Runnable runnable = new Runnable() {
        @Override
        public void run() {
            for (int i = 0; i < 10000; i ++) {
                map.put(UUID.randomUUID().toString(), "" + i);
                System.out.println("i = " + i);
            }
        }
    };

    public static void main(String[] args) {
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        Thread t3 = new Thread(runnable);

        t1.start();
        t2.start();
        t3.start();

        Thread.currentThread().getThreadGroup().list();

        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        System.out.println(map.size());
    }
}
