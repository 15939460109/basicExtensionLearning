package hashmap;

public class ThreadVolatileDemo extends Thread {

    private volatile boolean flag = true;
//    private boolean flag = true;

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(name + "==开始执行==");
        while (true) {
            // 决定是否结束循环
            if (!isFlag()) {
                System.out.println("执行线程退出");
                break;
            }
        }
        System.out.println(name + "==线程停止==");
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
