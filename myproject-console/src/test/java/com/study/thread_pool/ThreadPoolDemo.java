package com.study.thread_pool;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author jackson
 * @version 1.0
 * @date 2019/9/26 14:57
 * @comment:
 */
public class ThreadPoolDemo {


    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            System.out.println("定时线程池已经运行:" + Thread.currentThread().getName());
        }, 1, 3, TimeUnit.SECONDS);
    }

    /**
     * 可缓存线程池
     */
    @Test
    public void test1() throws InterruptedException {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            Thread.sleep(3000);
            cachedThreadPool.execute(() -> {
                System.out.println(Thread.currentThread().getName() + ":正被执行");
            });

        }

    }

    /**
     * 定时线程池
     */
    @Test
    public void test2() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            System.out.println("定时线程池已经运行:" + Thread.currentThread().getName());
        }, 1, 3, TimeUnit.SECONDS);


    }

    @Test
    public void test3() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Future<String> future = executorService.submit(() -> "result");
        System.out.println(future.get());


    }

    @Test
    public void test4() {
        String s = "hello";
        String t = "false";
        System.out.println(t == s);

    }

}
