package hashmap;

import java.util.HashMap;

public class HashMapDemo2 {

    public static void main(String[] args) {

        HashMap map = new HashMap();
        // 无参构造
        HashMap map1 = new HashMap();

        // 初始化容量
        HashMap map2 = new HashMap(9);
        // 数组初始化 16
//        int n = cap - 1;
//        8
//        0000 1000
//        n |= n >>> 1;
//        0000 1000 n
//        0000 0100 n >>> 1
//        0000 1100 n
//        n |= n >>> 2;
//        0000 1100 n
//        0000 0011 n >>> 2
//        0000 1111 n
//        n |= n >>> 4;
//        0000 1111 n
//        0000 0000 n >>> 4
//        0000 1111 n
//        n |= n >>> 8;
//        0000 1111 n
//        0000 0000 n >>> 8
//        0000 1111 n
//        n |= n >>> 16;
//        0000 1111 n
//        0000 0000 n >>> 16
//        0000 1111 n
//        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
//        16

        // 初始化容量和参数因子
        HashMap map3 = new HashMap(9, 0.5f);
        // 使用0.5 * 16(初始化容量) = 8，如果HashMap里的元素个数超过了这个数
        // 它会首先考虑扩展数组的长度，从而避免hash冲突

        // 以map构建一个新的map
        HashMap map4 = new HashMap(map);
    }
}
