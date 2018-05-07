package com.practice.lambdas.comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorLambda {

    public static void main(String args[]) {
        Employee e1 = new Employee();
        e1.setAge(10);
        e1.setName("Puneet");
        Employee e2 = new Employee();
        e2.setName("Pankaj");
        e2.setAge(13);
        Employee e3 = new Employee();
        e3.setName("Nikhil");
        e3.setAge(12);
        Employee e4 = new Employee();
        e4.setAge(10);
        e4.setName("Prince");
        List<Employee> listEmployees = new ArrayList<>();
        listEmployees.add(e1);
        listEmployees.add(e2);
        listEmployees.add(e3);
        listEmployees.add(e4);
        //static utility method added in java 8 , no need of compare method
        System.out.println("Using comparing method with name attribute\n");
        Comparator<Employee> nameComparator = Comparator.comparing(Employee::getName);
        Collections.sort(listEmployees,nameComparator);
        listEmployees.forEach(val->System.out.println(val.getName()));

        System.out.println("Using comparing method with name attribute in reverse or descending order\n");
        Comparator<Employee> nameComparatorDesc = nameComparator.reversed();
        Collections.sort(listEmployees,nameComparatorDesc);
        listEmployees.forEach(val->System.out.println(val.getName()));

        System.out.println("Using comparing method with name attribute using reverseOrder method\n");
        Comparator<Employee> nameComparatorDesc1 = Comparator.comparing(Employee::getName,Comparator.reverseOrder());
        Collections.sort(listEmployees,nameComparatorDesc1);
        listEmployees.forEach(val->System.out.println(val.getName()));

        System.out.println("Using comparing method with name first and then age attribute\n");
        Comparator<Employee> nameAndThenAgeComparator = Comparator.comparing(Employee::getName).thenComparing(Employee::getAge);
        Collections.sort(listEmployees,nameAndThenAgeComparator);
        listEmployees.forEach(val->System.out.println(val.getName()+" "+val.getAge()));

        System.out.println("Using comparingInt method with age attribute");
        Comparator<Employee> ageComparatorInt = Comparator.comparingInt(Employee::getAge);
        Collections.sort(listEmployees,ageComparatorInt);
        listEmployees.forEach(val->System.out.println(val.getAge()));

        System.out.println("Using naturalOrder method with name attribute as defined in Employee class");
        Comparator<Employee> naturalOrderComparator = Comparator.naturalOrder();
        Collections.sort(listEmployees,naturalOrderComparator);
        listEmployees.forEach(val->System.out.println(val.getName()));

        System.out.println("Using comparing method with age attribute");
        Comparator<Employee> ageComparator = Comparator.comparing(Employee::getAge);
        Collections.sort(listEmployees,ageComparator);
        listEmployees.forEach(val->System.out.println(val.getAge()));
    }

}
