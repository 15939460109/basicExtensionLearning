package hashmap;

// 可见性
public class VisibilityDemo {

    public static void main(String[] args) throws InterruptedException {
        ThreadVolatileDemo threadVolatileDemo = new ThreadVolatileDemo();
        // 开启线程
        threadVolatileDemo.start();
        System.out.println("flag=[" + threadVolatileDemo.isFlag() + "]");
        // 睡眠3秒(主函数的执行线程)  让ThreadVolatileDemo线程执行3秒
        Thread.sleep(3000);
        // 设置flag为false
        threadVolatileDemo.setFlag(false);
        // 睡眠1秒(主函数的执行线程)
        Thread.sleep(1000);
        System.out.println("flag=[" + threadVolatileDemo.isFlag() + "]");
    }

}
