package com.study.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
//callAble<T> 配合 ExecutorService submit  get
//thread(runnable/callable) 所以线程复用  这两个接口都是只有run()/call()待实现,真正创建线程在thread中
//fork-join  分合
//todo 判断线程并发
//基数小运行快
public class CallableUtil {

    public static int count = 400000;

    //todo  future.get()会阻塞当前线程,拿到结果再往下执行  future 异步计算结果
    //两条线程,同时进行黑名单去除与内容去重
    //线程池不会自己结束  需执行shutdown()手动结束
    //submit(callable)  来自AbstractExecutorService,包装成futureTask

    //future  线程池两条线程不并发,   结果长度200000                        耗时5549
    //400000           14102
    //800000           46556=25083+
    //todo  改造  future.get()移后
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        long start = System.currentTimeMillis();
        long spend;
        List<Future<String>> futureList = new ArrayList<>();
        String result = "";
        for (int i = 0; i < 2; i++) {
            String common = String.valueOf(i);
            System.out.println("加值" + common);
            Future<String> future = pool.submit(new MyTask(common, CallableUtil.count));
            futureList.add(future);

            /*String futureResult = future.get();
            result += futureResult;*/
            /*spend = System.currentTimeMillis() - start;
            System.out.println("耗时:" + spend);*/
        }

        for (Future<String> temp : futureList) {
            result += temp.get();
            spend = System.currentTimeMillis() - start;
            System.out.println("耗时:" + spend);
        }

        pool.shutdown();
        System.out.println("结果长度:" + result.length());
        spend = System.currentTimeMillis() - start;
        System.out.println("共耗时:" + spend);
    }

    //800000    38838
    //线程池两条线程并发
    @Test
    public void testq() throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        long start = System.currentTimeMillis();
        long spend;
        String result = "";
        String common = String.valueOf(0);
        System.out.println("加值" + common);
        Future<String> future1 = pool.submit(new MyTask(common, CallableUtil.count));
        common = String.valueOf(1);
        System.out.println("加值" + common);
        Future<String> future2 = pool.submit(new MyTask(common, CallableUtil.count));
        String futureResult1 = future1.get();
        String futureResult2 = future2.get();

        result += futureResult1;
        result += futureResult2;

        spend = System.currentTimeMillis() - start;
        System.out.println("耗时:" + spend);

        pool.shutdown();
        System.out.println("结果长度:" + result.length());
        spend = System.currentTimeMillis() - start;
        System.out.println("共耗时:" + spend);
    }

    //forkjoin   结果长度200000                   耗时5522
    //400000             15377=8968+
    //800000             47006=25294+
    @Test
    public void testa() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool(100);
        long spend;
        //最终合并的结果
        String result = "";
        for (int i = 0; i < 2; i++) {
            String common = String.valueOf(i);
            System.out.println("加值" + common);
            SmallTask smallTask = new SmallTask(common, CallableUtil.count);

            ForkJoinTask<String> future = forkJoinPool.submit(smallTask);
            String s = future.get();
            result += s;
            spend = System.currentTimeMillis() - start;
            System.out.println("耗时:" + spend);
        }
        forkJoinPool.shutdown();
        System.out.println("结果长度:" + result.length());
        spend = System.currentTimeMillis() - start;
        System.out.println("共耗时:" + spend);
    }

    //单线程  结果长度 200000                 耗时9284  8951
    //400000                 31240=8639+
    //800000                 113482=26036+
    @Test
    public void testb() {
        long start = System.currentTimeMillis();
        //最终合并的结果
        String result = "";

        String common = String.valueOf(0);
        System.out.println("加值" + common);
        for (int i = 0; i < CallableUtil.count; i++) {
            result += common;
        }
        long spend = System.currentTimeMillis() - start;
        System.out.println("耗时:" + spend);
        common = String.valueOf(1);
        System.out.println("加值" + common);
        for (int i = 0; i < CallableUtil.count; i++) {
            result += common;
        }

        System.out.println("结果长度:" + result.length());
        spend = System.currentTimeMillis() - start;
        System.out.println("共耗时:" + spend);
    }
}

//RecursiveAction & RecursiveTask  extends ForkJoinTask
//池用
class SmallTask extends RecursiveTask<String> {
    private int count;
    private String common;

    public SmallTask(String common, int count) {
        this.common = common;
        this.count = count;
    }

    @Override//运行10s
    protected String compute() {
        String result = "";

        for (int i = 0; i < count; i++) {
            result += common;
        }
        System.out.println(Thread.currentThread().getName());
        return result;
    }
}

class MyTask implements Callable<String> {
    private int count;
    private String common;


    public MyTask(String common, int count) {
        this.common = common;
        this.count = count;
    }

    @Override
    public String call() throws Exception {
        String result = "";

        for (int i = 0; i < count; i++) {
            result += common;
        }
        return result;
    }
}



