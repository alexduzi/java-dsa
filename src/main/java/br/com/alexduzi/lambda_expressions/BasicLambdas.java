package br.com.alexduzi.lambda_expressions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

interface Printable<T> {
    void print(T t);
}

interface Retrievable<T> {
    T retrieve();
}

interface Evaluate<T> {
    boolean isNegative(T t);
}

interface Functionable<T, R> {
    R applyThis(T t);
}

class Person {
    private String name;
    private Integer age;
    private Double height;

    public Person(String name, Integer age, Double height) {
        this.name = name;
        this.age = age;
        this.height = height;
    }

    public Integer getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public Double getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                '}';
    }
}

public class BasicLambdas {
    public static void main(String[] args) {
        // BasicLambdas bl = new BasicLambdas();
        // bl.consumer();
        // bl.supplier();
        // bl.predicate();
        // bl.function();

//        List<Person> listPeople = bl.getPeople();
//        bl.sortAge(listPeople);
//        listPeople.forEach((person) -> System.out.println(person));
       // listPeople.forEach(System.out::println);

//        List<Person> listPeople = bl.getPeople();
//        bl.sortName(listPeople);
//        listPeople.forEach((person) -> System.out.println(person));
       // listPeople.forEach(System.out::println);

//        List<Person> listPeople = bl.getPeople();
//        bl.sortHeight(listPeople);
//        listPeople.forEach((person) -> System.out.println(person));
       // listPeople.forEach(System.out::println);
    }

    public void consumer() {
        Printable<String> printable = (s1) -> System.out.println(s1);
        printable.print("Printable lambda");

        Consumer<String> consumer1 = (s1) -> System.out.println(s1);
        Consumer<String> consumer2 = System.out::println;

        consumer1.accept("Printable lambda");
        consumer2.accept("Printable lambda");
    }

    public void supplier() {
        Retrievable<Integer> retrievable = () -> 77;
        System.out.println(retrievable.retrieve());

        Supplier<Integer> sup = () -> 77;
        System.out.println(sup.get());
    }

    public void predicate() {
        Evaluate<Integer> evaluate = (n1) -> n1 < 0;
        System.out.println(evaluate.isNegative(-1));
        System.out.println(evaluate.isNegative(1));

        Predicate<Integer> pred1 = (n1) -> n1 < 0;
        System.out.println(pred1.test(-1));
        System.out.println(pred1.test(1));

        System.out.println(check(4, (n1) -> n1 % 2 == 0));
        System.out.println(check(7, (n1) -> n1 % 2 == 0));

        System.out.println(check("Mr. Joe Bloggs", (s1) -> s1.startsWith("Mr.")));
        System.out.println(check("Ms. Ann Bloggs", (s1) -> s1.startsWith("Mr.")));

        System.out.println(check(new Person("Mike", 33,1.8D), (p1) -> p1.getAge() >= 18));
        System.out.println(check(new Person("Ann", 13, 1.4D), (p1) -> p1.getAge() >= 18));
    }

    public <T> boolean check(T t, Predicate<T> pred) {
        return pred.test(t);
    }

    public void function() {
        Functionable<Integer, String> functionable = (n1) -> String.format("Number is: %d", n1);
        System.out.println(functionable.applyThis(25));

        Function<Integer, String> func = (n1) -> String.format("Number is: %d", n1);
        System.out.println(func.apply(25));
    }

    private static void sortName(List<Person> listPeople) {
        listPeople.sort(Comparator.comparing(person -> person.getName()));
    }

    private static void sortAge(List<Person> listPeople) {
        listPeople.sort(Comparator.comparing(person -> person.getAge()));
    }

    private static void sortHeight(List<Person> listPeople) {
        listPeople.sort(Comparator.comparing(person -> person.getHeight()));
    }

    private static List<Person> getPeople() {
        List<Person> result = new ArrayList<>();
        result.add(new Person("Mike", 33, 1.8));
        result.add(new Person("Mary", 25, 1.4));
        result.add(new Person("Alan", 34, 1.7));
        result.add(new Person("Zoe", 30, 1.5));
        return result;
    }
}
