package com.study.lambda;

import com.study.entity.Person;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author jackson
 * @version 1.0
 * @date 2019/9/18 16:24
 * @comment:基础知识测试
 */
public class BaseTest {

    @Test
    public void test1() {
        long result = Math.round(-1.5);
        System.out.println(result);

    }

    /**
     * 并列排名
     */
    @Test
    public void test2() {
        List<Person> persons = Arrays.asList(new Person("小妹", 12),
                new Person("小明", 16),
                new Person("xls", 12),
                new Person("xld", 13),
                new Person("xla", 15),
                new Person("xli", 16),
                new Person("xlp", 17)
        );
        List<Integer> list = persons.stream().sorted(Comparator.comparingInt(Person::getTotal)).map
                (Person::getTotal).collect(Collectors
                .toList());
        Collections.reverse(list);
        System.out.println(list);
        List<Person> resultList = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        int i = 1;
        for (Integer r : list) {
            Person person = new Person();
            Integer res = map.get(r);
            //不为空则添加原来的
            person.setTotal(r);
            if (res != null) {
                person.setId(res);
            }
            //为空
            else {
                map.put(r, i);
                person.setId(i);
            }

            resultList.add(person);
            i++;
        }
        List<Integer> resultL = resultList.stream().map(Person::getId).collect(Collectors.toList());
        System.out.println(resultL);
    }

    /**
     * 线程安全 配合concurrentHashMap  AtomicInteger
     *
     * @throws InterruptedException
     */
    @Test
    public void test3() throws InterruptedException {
        ConcurrentHashMap<String, AtomicInteger> map = new ConcurrentHashMap<String, AtomicInteger>();
        AtomicInteger integer = new AtomicInteger(1);
        map.put("key", integer);
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 1000; i++) {
            executorService.execute(() -> map.get("key").incrementAndGet());
        }
        Thread.sleep(3000); //模拟等待执行结束
        System.out.println("------" + map.get("key") + "------");
        executorService.shutdown();


    }

    /**
     * 线程不安全
     *
     * @throws InterruptedException
     */
    @Test
    public void test4() throws InterruptedException {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<String, Integer>();
        map.put("key", 1);
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 1000; i++) {
            executorService.execute(() -> {
                int key = map.get("key") + 1; //step 1
                map.put("key", key);//step 2
            });
        }
        Thread.sleep(3000); //模拟等待执行结束
        System.out.println("------" + map.get("key") + "------");
        executorService.shutdown();


    }

    /**
     * 字符串翻转
     */
    @Test
    public void test5() {
        String str = "小明是二货";
        str = StringUtils.reverse(str);

        char[] chars = str.toCharArray();
        int length = chars.length;
        char[] newChar = new char[length];
        for (int i = 0; i < length; i++) {
            newChar[i] = chars[length - i - 1];
        }
        System.out.println(new String(newChar));

        //利用栈先进后出的原则
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < chars.length; i++) {
            stack.push(Character.valueOf(chars[i]));
        }
        char[] result = new char[length];
        for (int i = 0; i < result.length; i++) {
            result[i] = stack.pop();
        }

        System.out.println(new String(result));
    }

    /**
     * IO流
     */
    @Test
    public void test6() throws IOException {
        //字节流
        String a = "C:\\学习笔记\\a.txt";
        FileInputStream fileInputStream = new FileInputStream(a);
        String b = "C:\\学习笔记\\b.txt";
        FileOutputStream fileOutputStream = new FileOutputStream(b);
        byte[] buf = new byte[1024];
        int len = 0;
        /*
        while ((len=fileInputStream.read(buf))!=-1){
            fileOutputStream.write(buf,0,len);
        }*/
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        /*while ((len=bufferedInputStream.read(buf))!=-1){
            bufferedOutputStream.write(buf);
            bufferedOutputStream.flush();
        }*/
        bufferedOutputStream.close();
        bufferedInputStream.close();
        //字符流
        FileReader fileReader = new FileReader(a);
        FileWriter fileWriter = new FileWriter(b);
        char[] chars = new char[1024];

        while ((len = fileReader.read(chars)) != -1) {
            fileWriter.write(chars, 0, len);
        }
        fileWriter.flush();

    }

}
