package hashmap;

import java.util.concurrent.*;

// 线程池
public class Demo2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newCachedThreadPool();
        pool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("缓存线程池");
            }
        });

        pool = Executors.newFixedThreadPool(10);
        pool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("定长线程池");
            }
        });

        pool = Executors.newSingleThreadExecutor();
        pool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("单线程池");
            }
        });

        ScheduledExecutorService pool2 = Executors.newScheduledThreadPool(10);
        pool2.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("计划任务线程池");
            }
        }, 3, TimeUnit.SECONDS);

        // corePoolSize, 核心线程数，即线程池最初初始化的线程数
        // maximumPoolSize, 最大线程数
        // keepAliveTime, 线程保持等待任务的最大空闲时间(空闲线程等待任务超过这个时间后，会自动释放)
        // unit, 时间单位(分，秒，毫秒等)
        // workQueue, 阻塞队列
        // Executors.defaultThreadFactory(), 创建线程的工厂
        // defaultHandler, 拒绝策略，即阻塞队列溢出时的处理策略
        //      丢弃任务并抛出RejectedExecutionException异常，默认
        //      丢弃任务，但是不抛出异常
        //      丢弃队列最前面的任务，然后重新提交被拒绝的任务
        //      由调用线程（提交任务的线程）处理该任务
        ExecutorService myPool = new ThreadPoolExecutor(
                5,
                10,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(50),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );

        myPool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("自定义线程池");
            }
        });

        Future<String> future = myPool.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(2000);
                return "返回字符串测试";
            }
        });
        System.out.println(future.get());
    }
}
