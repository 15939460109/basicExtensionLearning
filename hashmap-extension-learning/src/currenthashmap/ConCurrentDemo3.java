package currenthashmap;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;


public class ConCurrentDemo3 {

    public static void main(String[] args) {

        HashMap map = null;
        Hashtable hashtable = null;
        ConcurrentHashMap concurrentHashMap = null;

        // 计算key的hash值，算出tab中的位置i
        // 判断i是否为空
        // 为空直接放  =需要同步=
        // CAS 保证数据安全
        // 比较交换
        // 修改变量值x = 1，100个线程，99
        // 初始值1，最终修改值99
        // 原子操作，cpu指令CAS是原子性的
        // 判断当前线程，进行交换之后，x的值是不是初始值1，如果是，复制，如果不是，什么都不做
        // 保证只有一个线程赋值成功，而并没有线程的阻塞、线程切换的开销  CAS ABA问题
        // 不为空则链式添加，如果节点已有，则覆盖  =需要同步=
        // 判断count值，是否大于树化标准，转换数据结构  链表--红黑树  =需要同步=
        // 为保证map性能，牺牲空间换时间，判断节点数量是否超过当前大小阈值  扩容  =需要同步=
        // size集合大小+1  =需要同步=

        concurrentHashMap.put("aaa", 123);
    }
}
