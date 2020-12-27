package hashmap;

import java.util.HashMap;

public class HashMapDemo1 {

    public static void main(String[] args) {

        HashMap map = new HashMap();
        // key --> value
        // Node{ key , value}
        // new Node{ key , value}
        // node.key == key
        // return node.value;
        // Node[] nodes;
        // node[0] = new Node{ key , value}
        // node[1] = new Node{ key , value}
        // node[2] = new Node{ key , value}
        // node[3] = new Node{ key , value}
        // node[4] = new Node{ key , value}
        // node[5] = new Node{ key , value}
        // node[6] = new Node{ key , value}
        // node[7] = new Node{ key , value ,next}
        // for(Node node : nodes)
        // key hash(key) = value --> [i]
        // 10 20 30
        // key = 20 node[16] 0 - 15 - [0000 - 1111]
        // 0001 0100
        // 1001 0100
        // 1101 0100
        // 0011 0100
        // 0000 1111
        // 0000 0100 = arr[4]
        // 相同为1 不同为0
        // Node node = arr[4]; x
        // node = new Node{ "lili" , 33 next } == arr[4]
        // node = new Node{ "qq" , 22} == arr[4]
        // arr[4] = ["lili"] --> ["qq"]  ... 【】  【】  【】  【】  【YY】
//        arr[4] 是不是空的
//        arr[4] 是对象 还是链表
//        判断 key == key ， value == value
//        数组 链表
//        HASH 在没有冲突的情况下 O(1)
//        HASH 在有冲突的情况下 O(n)
//        链表 -- 红黑树 O(lgn)
//        map.remove("a");
    }

}
