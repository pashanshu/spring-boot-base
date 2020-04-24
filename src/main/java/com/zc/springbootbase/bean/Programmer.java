package com.zc.springbootbase.bean;


import java.time.LocalDate;

/**
 * @author : heibaiying
 */

public class Programmer {

    private String name;

    private int age;

    private float salary;

    private LocalDate birthday;

    public Programmer(String name, int age, float salary, LocalDate birthday) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.birthday = birthday;
    }
    public Programmer(){

    }

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

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}