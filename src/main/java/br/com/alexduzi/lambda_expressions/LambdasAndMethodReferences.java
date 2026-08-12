package br.com.alexduzi.lambda_expressions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.*;

public class LambdasAndMethodReferences {
    public static void main(String[] args) {
        // staticMR();
        // boundMR();
        // unboundMR();
        constructorMR();
    }

    static void staticMR() {
        // 1.a
        List<Integer> integers = Arrays.asList(1,2,7,4,5);
        // 1.b
        Consumer<List<Integer>> consumer1 = (list) -> Collections.sort(list);
        // 1.c
        consumer1.accept(integers);
        // 1.d
        integers.forEach(System.out::println);
        // 1.e
        integers = Arrays.asList(1,2,7,4,5);
        // 1.f
        Consumer<List<Integer>> consumer2 = Collections::sort;
        // 1.g
        consumer2.accept(integers);
        // 1.h
        integers.forEach(System.out::println);
    }

    static void boundMR() {
        // 2.a
        String name = "Mr. Joe Bloggs";
        // 2.b
        Predicate<String> pred1 = (s1) -> name.startsWith(s1);
        // 2.c
        System.out.println(pred1.test("Mr."));
        // 2.d
        System.out.println(pred1.test("Ms."));
        // 2.e
        Predicate<String> pred2 = name::startsWith;
        // 2.f
        System.out.println(pred2.test("Mr."));
        System.out.println(pred2.test("Ms."));
    }

    static void unboundMR() {
        // 3.a
        Predicate<String> pred1 = (s1) -> s1.isEmpty();
        // 3.b
        System.out.println(pred1.test(""));
        // 3.c
        System.out.println(pred1.test("xyz"));
        // 3.d
        Predicate<String> pred2 = String::isEmpty;
        // 3.e
        System.out.println(pred2.test(""));
        System.out.println(pred2.test("xyz"));
        // 3.f
        BiPredicate<String, String> biPred = (s1, s2) -> s1.startsWith(s2);
        System.out.println(biPred.test("Mr. Joe Bloggs", "Mr."));
        System.out.println(biPred.test("Mr. Joe Bloggs", "Ms."));
        // 3.g
        BiPredicate<String, String> biPred2 = String::startsWith;
        // 3.h
        System.out.println(biPred2.test("Mr. Joe Bloggs", "Mr."));
        System.out.println(biPred2.test("Mr. Joe Bloggs", "Ms."));
    }

    static void constructorMR() {
        // 4.a
        Supplier<List<String>> sup = () -> new ArrayList<>();
        // 4.b
        List<String> list = sup.get();
        // 4.c
        list.add("Lambda");
        // 4.d
        list.forEach(System.out::println);
        // 4.e
        Supplier<List<String>> sup2 = ArrayList::new;
        list = sup2.get();
        list.add("Method Reference");
        list.forEach(System.out::println);
        // 4.f
        Function<Integer, List<String>> lambda2 = n -> new ArrayList(n);
        list = lambda2.apply(20);
        list.add("Lambda");
        list.forEach(System.out::println);
        // 4.g
        Function<Integer, List<String>> methodRef2 = ArrayList::new; // context!
        list = methodRef2.apply(20);
        list.add("Method Reference");
        list.forEach(System.out::println);
    }
}
