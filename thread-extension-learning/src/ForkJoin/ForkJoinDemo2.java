package ForkJoin;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

// 统计单词数
public class ForkJoinDemo2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ForkJoinPool forkJoinPool = new ForkJoinPool();

        String[] words = {"hello world", "hello java", "hello mysql", "mysql html", "css javascript"};

        // 创建任务
        CountTask task = new CountTask(words, 0, words.length);

        // 执行任务
        ForkJoinTask<Map<String, Long>> forkJoinTask = forkJoinPool.submit(task);
        // 使用invoke方法可以直接得到结果
        // Map<String, Long> result = forkJoinPool.invoke(task);

        // 获取结果
        Map<String, Long> result = forkJoinTask.get();

        // 遍历打印
        result.forEach((k, v) ->
                System.out.println(k + "," + v)
        );
    }

    // [0 1] [1 2] [2 3] [3 4]
    static class CountTask extends RecursiveTask<Map<String, Long>> {

        // 需要给定一个String数组
        String[] words;
        int start;
        int end;

        public CountTask(String[] words, int start, int end) {
            this.words = words;
            this.start = start;
            this.end = end;
        }

        // 统计一份字符串里单词的数量
        private Map<String, Long> countOne(String str) {
            // 定义返回值
            Map<String, Long> result = new HashMap<>();

            // 拆分字符串
            String[] words = str.split("\\s");

            // 将数组里的每个单词放入map集合里
            for (String item : words) {
                Long times = result.get(item);
                if ( times == null) {
                    result.put(item, 1L);
                } else {
                    result.put(item, times + 1L);
                }
            }

            return result;
        }

        // 合并两个map集合
        private Map<String, Long> merge(Map<String, Long> left, Map<String, Long> right) {
            Map<String, Long> result = new HashMap<>();
            result.putAll(left);

            // 遍历right
            Set<Map.Entry<String, Long>> entries = right.entrySet();
            for (Map.Entry<String, Long> entry : entries) {
                // 获取键值
                String key = entry.getKey();
                Long value = entry.getValue();

                Long times = result.get(key);
                if (times == null) {
                    result.put(key, value);
                } else {
                    result.put(key, value + times);
                }
            }

            return result;
        }

        @Override
        protected Map<String, Long> compute() {
            if (end - start <= 1) {
                // 返回结果
                return countOne(words[start]);
            } else {
                // 任务进行对半拆分
                int mid = (start + end) / 2;

                // 创建CountTask对象
                CountTask leftTask = new CountTask(words, start, mid);
                CountTask rightTask = new CountTask(words, mid, end);

                // fork拆分任务
                leftTask.fork();
                rightTask.fork();

                // join获取结果
                Map<String, Long> leftResult = leftTask.join();
                Map<String, Long> rightResult = rightTask.join();

                // 最后合并，返回结果
                return merge(leftResult, rightResult);
            }
        }
    }
}
