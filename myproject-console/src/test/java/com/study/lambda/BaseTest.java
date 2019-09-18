package com.study.lambda;

import com.study.entity.Person;
import org.junit.Test;

import java.util.*;
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

}
