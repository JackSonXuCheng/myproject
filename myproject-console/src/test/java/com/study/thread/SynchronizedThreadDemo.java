package com.study.thread;

import java.util.concurrent.TimeUnit;

public class SynchronizedThreadDemo {
    private int num=0;
    private static int size=10;

    public static void main(String[] args) throws InterruptedException {
        //创建两个不同的对象
        SynchronizedThreadDemo synchronizedThreadDemo1 = new SynchronizedThreadDemo();
        SynchronizedThreadDemo synchronizedThreadDemo2 = new SynchronizedThreadDemo();
      /*  Thread a = new Thread(() -> {
            synchronizedThreadDemo1.printNum("a");
        });
        Thread b = new Thread(() -> {
            synchronizedThreadDemo1.printNum("b");
        });
        a.start();
        b.start();*/

/*
        Thread c = new Thread(() -> {
            synchronizedThreadDemo1.printNum(size);
        },"c");
        Thread d = new Thread(() -> {
            synchronizedThreadDemo1.printNum(size);
        },"d");
        c.start();
        d.start();*/

/**
 * 分析：
 * t1线程先持有object对象的Lock锁，t2线程可以以异步的方式调用对象中的非synchronized修饰的方法
 * t1线程先持有object对象的Lock锁，t2线程如果在这个时候调用对象中的同步（synchronized）方法则需等待，也就是同步
 */
        Thread f = new Thread(() -> synchronizedThreadDemo1.method1(),"f");
        Thread g = new Thread(() -> synchronizedThreadDemo1.method2(),"g");
        f.start();
        g.start();

    }
    //加锁 不会出现脏数据
    public synchronized void printNum(String tag){

        if (tag.equals("a")){
                num=100;
            System.out.println("tag a ,set num over"+Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
        }else {
            num=200;
            System.out.println("tag b ,set num over"+Thread.currentThread().getName());

        }
        System.out.println("tag:"+tag+"num ="+num+Thread.currentThread().getName());

    }

    public synchronized void  printNum(int i){

       for (int j=0;j<i;j++){
           System.out.println("数据："+j+":"+Thread.currentThread().getName());
       }


    }

    public synchronized void method1(){
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /** synchronized */
    public  void method2(){
        System.out.println(Thread.currentThread().getName());
    }

}
