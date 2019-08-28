package com.study.lambda;

import org.junit.Test;

import java.util.function.*;

public class LambdaKindTest {
    @Test
    public void test() {

    }
    //消费型接口
    @Test
    public void test1(){
        Consumer<Integer> consumer =  System.out::println;

        consumer.accept(123);
    }
    //供给型接口
    @Test
    public void test2() {
        Supplier<Integer> supplier= () -> 1+1;
        System.out.println(supplier.get());
    }
    //函数型接口
    @Test
    public void test3() {
        Function<Integer,Integer> function =(x) -> x*x;
        System.out.println(function.apply(234));
    }
    //断言型接口
    @Test
    public void test4() {
        Predicate<String> predicate =(x) -> x.equals("s");
        System.out.println(predicate.test("s"));
    }

    @Test
    public void test5() {
        BinaryOperator<Integer> binaryOperator = (x,y) -> Math.max(x,y);
        System.out.println(binaryOperator.apply(12, 3));
        //等同于
        BinaryOperator<Integer> binaryOperator1= Math::max;
        System.out.println(binaryOperator1.apply(12, 3));
    }

}
