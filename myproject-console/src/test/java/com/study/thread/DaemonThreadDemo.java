package com.study.thread;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 输入输出逻辑包装进守护线程
 */
public class DaemonThreadDemo {

    public static void main(String[] args) throws InterruptedException {
      /* Runnable runnable= new TestRunable();
        Thread thread = new Thread(runnable);
        thread.setDaemon(true);//设置守护线程 不推荐输入输出流放到守护线程
        thread.start();*/

      //

        Thread thread = new Thread(() -> {
            while (true){
                //睡眠一秒
                try {

                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("运行陈公公");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        //启动线程
        thread.setDaemon(true);//设置为main的守护线程
        thread.start();
        TimeUnit.SECONDS.sleep(2);

        System.out.println("主线程：main 退出");

    }



}
class TestRunable implements Runnable{

    @Override
    public void run() {
        try {
           // Thread.sleep(1000);
            File f=new File("C:\\daemon.txt");
            boolean exists = f.exists();
            System.out.println(exists);
            FileOutputStream os=new FileOutputStream(f,true);
            os.write("daemon".getBytes());
        }  catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}