package ForkJoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

// 加和
public class ForkJoinDemo1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 线程池，可以将任务拆解完成、合并
        // 1 - 10000

        // 创建线程池
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        // 创建任务
        CountTask task = new CountTask(1, 100);

        // 提交任务
        ForkJoinTask<Long> forkJoinTask = forkJoinPool.submit(task);

        System.out.println(forkJoinTask.get());

        // RecursiveTask    有返回值
        // RecursiveAction  没有返回值
    }

    // [1 3] [4 6] [7 9] [10 12]
    static class CountTask extends RecursiveTask<Long> {

        int start;
        int end;

        public CountTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        // 计算方法
        // 需要指定任务的拆解和合并
        // fork 拆解
        // join 获得结果
        @Override
        protected Long compute() {
            // 定义返回结果
            long sum = 0;
            // 拆解任务的阈值(如何定义一个任务是最小的)
            if (end - start <= 2) {
                // 是最小的任务，就计算求和
                for (int i = start; i <= end; i ++) {
                    sum += i;
                }
            } else {
                // 任务进行对半拆分
                int mid = (start + end) / 2;

                // 创建CountTask对象
                CountTask leftTask = new CountTask(start, mid);
                CountTask rightTask = new CountTask(mid + 1, end);

                // fork拆分任务
                leftTask.fork();
                rightTask.fork();

                // join获取结果
                long leftResult = leftTask.join();
                long rightResult = rightTask.join();
                sum = leftResult + rightResult;
            }
            return sum;
        }
    }
}
