package currenthashmap;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class ConCurrentDemo1 {

    public static void main(String[] args) {

        ConcurrentHashMap map;

        // 创建线程
        // 1.继承Thread或实现Runnable
        // 2.重写run方法，就是线程执行的逻辑
        // 启动线程
        new Thread().start();
        // t1.run();  这样启动不了？

        HashMap map1 = new HashMap();
        map1.put("aaa", 1);
    }
}
