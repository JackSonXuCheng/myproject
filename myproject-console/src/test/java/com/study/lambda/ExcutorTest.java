package com.study.lambda;

import org.junit.Test;

import java.util.Objects;


public class ExcutorTest {

    @Test
    public void test() {
        System.out.println("你是不是最傻的");
    }

    @Test
    public void test1() {
        String str = "s";
        String st = "s";
        if (Objects.equals(str, st)) {
            System.out.println("你是否就");
        }


    }
}
