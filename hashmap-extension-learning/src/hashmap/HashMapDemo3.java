package hashmap;

import java.util.HashMap;

public class HashMapDemo3 {

    public static void main(String[] args) {

        HashMap map = new HashMap();

        map.get("hello");

        map.remove("ss");
        // 当前数组位置只有一个对象NODE，index = null
        // 当前位置是链表，
        // [NODE] --> [==NODE==] --> [NODE] --> [NODE]
        // [NODE] -->  --> [NODE] --> [NODE]
        // [NODE1] --> [==NODE2==] --> [NODE3] --> [NODE]
        //           [==NODE==] --> [NODE] --> [NODE]
        // [NODE1] -->  [NODE3] --> [NODE]
        // 红黑树
    }

}
