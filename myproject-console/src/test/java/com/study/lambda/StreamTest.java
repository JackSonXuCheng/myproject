package com.study.lambda;

import com.study.entity.Person;
import org.apache.logging.log4j.util.PropertySource;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {


    @Test
    public void test() {

    }

    @Test
    public void test1() {

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        //将所得到的值重新转换为一个集合对象
        List<Integer> collect = list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println(collect);

        collect.stream().sorted(Comparator.reverseOrder()).forEach(System.out::print); //进行倒序

        Optional<Integer> max = list.stream().max(Integer::compareTo);

    }

    @Test
    public void test2() {
        List<String> list = new ArrayList<>();
        list.add("你");
        list.add("是");
        list.add("我");
        list.add("最");
        list.add("好");
        list.add("的");
        list.add("朋");
        list.add("友");
        //连接流中的每个字符串
        String str = list.stream().map(String::trim).collect(Collectors.joining());
        System.out.println(str);//你是我最好的朋友

    }

    @Test
    public void test3() {

     List<Person> personList = new ArrayList<>();
     personList.add(new Person(1,"男","小明",1));
     personList.add(new Person(2,"男","小蓝",12));
     personList.add(new Person(1,"女","小红",123));
     personList.add(new Person(3,"男","小灵",123));
     personList.add(new Person(4,"女","小景",1234));
     personList.add(new Person(1,"女","小景",123));

    //求和
     personList.stream().mapToLong(Person::getTotal).sum();

     //进行分组【按Id 分组】
        Map<Integer, List<Person>> collect = personList.stream().collect(Collectors.groupingBy(Person::getId));
     //进行分组【按Id 分组】 并求和
        Map<Integer, Integer> map = personList.stream().collect(Collectors.groupingBy(Person::getId, Collectors.summingInt(Person::getTotal)));
     //进行多个字段分组
        Map<Person, IntSummaryStatistics> personMap = personList.stream().collect(Collectors.groupingBy(person -> new Person(person.getId(), person.getTotal()), Collectors.summarizingInt(Person::getTotal)));

        Map<Integer, Map<Integer, List<Person>>> permap1 = personList.stream().collect(Collectors.groupingBy
                (Person::getId, Collectors.groupingBy(Person::getTotal)));

        Map<Integer, Map<Integer, IntSummaryStatistics>> collect2 = personList.stream().collect(Collectors.groupingBy(Person::getId, Collectors.groupingBy(Person::getTotal, Collectors.summarizingInt(Person::getTotal))));

        //相等collect2=collect1
        Map<Integer, Map<Integer, IntSummaryStatistics>> collect1 = personList.stream().collect(Collectors.groupingBy(person -> person.getId(), Collectors.groupingBy(person -> person.getTotal(), Collectors.summarizingInt(Person::getTotal))));

       //根据ID和Total 进行分组 ，并求和
        Map<Integer, Map<Integer, Integer>> collect3 = personList.stream().collect(Collectors.groupingBy(Person::getId, Collectors.groupingBy(Person::getTotal, Collectors.summingInt(Person::getTotal))));


    }
}
