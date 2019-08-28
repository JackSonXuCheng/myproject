package com.study.thread;

public class ModifyLock {

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public synchronized void changeAttribute(String name,int age){

        synchronized(ModifyLock.class) {
            try {
                System.out.println("当前线程：" + Thread.currentThread().getName() + "开始");
                this.setName(name);
                this.setAge(age);

                System.out.println("当前线程：" + Thread.currentThread().getName() + "修改对象内容为：" + this.getName() + "," + this.getAge());
                Thread.sleep(2000);
                System.out.println("当前线程：" + Thread.currentThread().getName() + "结束");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        final ModifyLock locks = new ModifyLock();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                locks.changeAttribute("zhangsan",34);
            }
        },"t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                locks.changeAttribute("lisi",345);
            }
        },"t2");

        t1.start();
        t2.start();
    }
}