package com.study.lambda;

import org.junit.Test;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Supplier;


public class LambdaTest {

    @Test
    public void test() {

    }

        // Lambda表达式 无参
    @Test
    public void test1(){
        Runnable runnable =()-> System.out.println("Hello Lambda");
        //启动线程
        runnable.run();

    }
    //Lambda表达式 需要一个参数
    @Test
    public void test2(){
        Consumer<Integer> consumer=(a)-> System.out.println(a);
        consumer.accept(1);
    }
    //Lambda表达式 需要一个参数 参数的括号可以省略
    @Test
    public void test3(){
        Consumer<Integer> consumer = a -> System.out.println(a);
        consumer.accept(12);
    }
    //Lambda表达式 需要两个参数 并且有返回值
    @Test
    public void test4() {
        BinaryOperator<Integer> bo=(x,y) -> {
            System.out.println(x+y);
            return x+y;
        };
        Integer apply = bo.apply(12, 34);
    }

    //Lambda表达式 需要两个参数 只有一条语句，return与大括号可以省略
    @Test
    public void test5() {
        BinaryOperator<Integer> bo =(x,y) -> x+y;
        System.out.println(bo.apply(12, 34));
    }

    //Lambda表达式 不需要参数 只有一条语句，return与大括号可以省略
    @Test
    public void test6() {
        //供给型接口
        Supplier<Integer> supplier = () -> 1+1;
        System.out.println(supplier.get());
    }

}
