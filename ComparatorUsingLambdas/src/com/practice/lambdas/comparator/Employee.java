package com.practice.lambdas.comparator;

import java.util.Comparator;

public class Employee implements Comparable<Employee> {

    private String name;
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int compareTo(final Employee e1)
    {
        return this.getName().compareTo(e1.getName());
    }
}
