package hashmap;

import java.util.HashMap;
import java.util.Map;

public class HashMapDemo4 {

    public static void main(String[] args) {

        HashMap<Object, Object> map = new HashMap<>();

        map.put("a", "111");
//        map.putIfAbsent("a", "222");

        System.out.println(map.get("a"));

        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            map.put("aaa333", "123123");
        }

        // put hashmap初始化 16 12 元素个数12 扩容
        // 冲突降低
        // 16 = 32
        // 3
        // 0011 = 3
        // 1111
        // 0011 位置 tab[3]
        // 1 0011 = 19
        // 0 1111 = 3
        // 0 0011 位置 tab[3]
        // hash 3 ， 19 hash桶中，index=3
        // 01 0000 = 16 1111
        // 10 0000 = 32 11111
        // 00011 = 3
        // 11111 = 3

        // 1 0011 = 19
        // 1 1111
        // 1 0011 = 19 hash桶中，index=19
        // 11 0011
        // 51 = 19
        // 11 0011
        // 01 1111
        // 01 0011


        // 长度16 index3 --> [3] --> [19]
        // 长度32 index3 --> [3]
        // 长度32 index19 --> [19]
        // 0000 1111 16 - 1 TAB[0-15]
        // 0001 1111 tab[0-31]

        // 0000 1101
        // 0001 1110
        // 0010 0110
        // 0001 0000
        // 0001 0000 对象必然在新数组扩容的位置
        // 0000 0000 对象必然在原数组的位置
        // 原来的key 和 元素组长度进行&运算，结果是1，在新位置，结果为0 位置不动

        // 放在新数组的什么位置 index = 原位置 + 原数组长度（16）

        // 0000 1111
        // 0001 1111
        // 0111 1111
        // 0100 0000

        // 1 2 3 4 5 6
        // 0000 1000
        // 0000 0001
        // 0000 0010
        // 0000 0011
        // 0000 0100
        // 0000 0101
        // 0000 0110
        // 0000 0000 -- 散列差 冲突高 查询性能就不好
        // oldNode = [x_] > [x_] > [x_]
        // newNode = [x=] > [x=] > [x=] > [x=]
        // [0] = [x_] > [x_] > [x_]
        // [1]
        // [2]
        // [3]
        // [4] [x] [x] [x] [x] [x] [x]
        // [5]
        // [6]
        // [7]
        // [8]
        // [9]
        // [10]
        // [11]
        // [12]
        // [13]
        // [14]
        // [15]
        // [16] = newNode = [x=] > [x=] > [x=] > [x=]

        // 遍历老数组
        // 找到冲突链表或红黑树
        // 遍历每个节点，判断是否在新的数组位置，如果在新位置，用当前index+老数组长度 = 新数组位置
        // 创建2个Node
        //  Node oldNode;
        //  Node newNode;
        // TAB[J] oldNode --> oldNode --> oldNode--> oldNode--> oldNode--> oldNode
        // TAB[J] newNode --> newNode --> newNode--> newNode
        // newTab[j] = oldNode;
        // newTab[j+老数组容量] = newNode;

    }
}
