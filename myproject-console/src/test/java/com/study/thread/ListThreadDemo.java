package com.study.thread;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListThreadDemo {
    static ArrayList<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws InterruptedException {
        ListThreadDemo listThreadDemo = new ListThreadDemo();
        ArrayList<Integer> list1 = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            list.add(i);
        }
        List<Integer> integers = list.subList(0, list.size() / 2);
        List<Integer> integers1 = list.subList(list.size() / 2, list.size());
        System.out.println("shuju1::"+integers.size());
        System.out.println("shuju2::"+integers1.size());
        Runnable runnable1 = () -> {
            for (int i=0;i<integers.size();i++){
                list1.add(integers.get(i));
            }

        };



        Runnable runnable2 = () -> {
            System.out.println(list1.size());
            for (int i =0; i < integers1.size(); i++) {
                list1.add(integers1.get(i));
            }
        };
        Thread thread1 = new Thread(runnable1,"thread1");
        Thread thread2 =new Thread(runnable2,"thread2");
        thread1.start();
        thread2.start();

        System.out.println("数据；"+list1.size());
    }


}
