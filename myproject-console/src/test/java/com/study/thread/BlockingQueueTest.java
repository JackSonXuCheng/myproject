package com.study.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTest {
    
    public static void main(String[] args) {
        final BlockingQueue queue = new ArrayBlockingQueue(6);
        for(int i=0;i<2;i++){
            new Thread(() -> {
                while(true){
                    try {
                        Thread.sleep((long)(Math.random()*1000));
                        System.out.println(Thread.currentThread().getName() + "准备放数据!");
                        queue.put(1);
                        System.out.println(Thread.currentThread().getName() + "已经放了数据，" +
                                    "队列目前有" + queue.size() + "个数据");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },"线程:"+Integer.toString(i)).start();
        }
        
        new Thread(() -> {
            while(true){
                try {
                    //将此处的睡眠时间分别改为100和1000，观察运行结果
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + "准备取数据!");
                    Object take = queue.take();

                    System.out.println(Thread.currentThread().getName() + "已经取走数据，" +
                            "队列目前有" + queue.size() + "个数据");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"线程:3").start();
    }
}