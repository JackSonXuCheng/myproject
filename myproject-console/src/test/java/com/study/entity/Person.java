package com.study.entity;


import java.math.BigDecimal;
import java.util.Date;

public class Person  {


    private Integer id;

    private String sex;

    private String name;

    private Integer total;

    private Date date;

    private BigDecimal money;

    public Person() {

    }

    public Person(String name, Integer total) {
        this.name = name;
        this.total = total;
    }

    public Person(Integer id, Integer total) {
        this.id = id;
        this.total = total;
    }

    public Person(Integer id, String sex, String name, Integer total) {
        this.id = id;
        this.sex = sex;
        this.name = name;
        this.total = total;
    }

    public Person(Integer id, String sex, String name) {
        this.id = id;
        this.sex = sex;
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", sex='" + sex + '\'' +
                ", name='" + name + '\'' +
                ", total=" + total +
                ", date=" + date +
                ", money=" + money +
                '}';
    }
}
