package com.study.lambda;

import com.study.entity.Person;
import org.junit.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author jackson
 * @version 1.0
 * @date 2019/8/28 10:09
 */
public class ReflexTest {


    @Test
    public void test1() throws IllegalAccessException {
        Person person = new Person();
        person.setId(1);
        person.setName("2");
        person.setSex("男");
        person.setTotal(234);
        person.setDate(new Date());
        person.setMoney(BigDecimal.valueOf(12));
        Field[] fields = person.getClass().getDeclaredFields();
        System.out.println(fields.length);
        for (Field field : fields) {
            field.setAccessible(true);

            String name = field.getName();
            Object o =  field.get(person);
            if (o instanceof String){
                System.out.println("string类型");
            }
            if (o instanceof Date){
                System.out.println("日期类型");
            }
            if (o instanceof Number){
                System.out.println("数字类型");
            }

        }


    }
}
