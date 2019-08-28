package com.study.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
 
public class CountdownLatchTest2 {
 
        public static void main(String[] args) {
              ExecutorService service = Executors. newCachedThreadPool();
               final CountDownLatch cdOrder = new CountDownLatch(1);
               final CountDownLatch cdAnswer = new CountDownLatch(4);
               for (int i = 0; i < 4; i++) {
                     Runnable runnable = () -> {
                            try {
                                  System. out.println("选手" + Thread.currentThread().getName() + "正等待裁判发布口令");
                                  cdOrder.await();
                                  System. out.println("选手" + Thread.currentThread().getName() + "已接受裁判口令");
                                  Thread. sleep((long) (Math. random() * 10000));
                                  System. out.println("选手" + Thread.currentThread().getName() + "到达终点");
                                  cdAnswer.countDown();
                           } catch (Exception e) {
                                  e.printStackTrace();
                           }
                    };
                     service.execute(runnable);
              }
               try {
                   Thread. sleep((long) (Math. random() * 10000));
 
                     System. out.println("裁判" + Thread.currentThread ().getName() + "即将发布口令" );
                   cdOrder.countDown();// 递减锁存器的计数，如果计数到达零，则释放所有等待的线程。如果当前计数大于零，则将计数减少。如果新的计数为零，出于线程调度目的，将重新启用所有的等待线程。
                     System. out.println("裁判" + Thread.currentThread ().getName() + "已发送口令，正在等待所有选手到达终点" );
                     cdAnswer.await();
                     System. out.println("所有选手都到达终点" );
                     System. out.println("裁判" + Thread.currentThread ().getName() + "汇总成绩排名" );
              } catch (Exception e) {
                     e.printStackTrace();
              }
              service.shutdown();
 
       }
}