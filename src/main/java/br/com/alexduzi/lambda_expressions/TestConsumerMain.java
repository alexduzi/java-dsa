package br.com.alexduzi.lambda_expressions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class TestConsumerMain {
    public static void main(String[] args) {
        // use the parameter but not interested in the return value
        // Consumer<T> is a functional interface i.e. one abstract method:
        // void accept(T t)
        Consumer<String> printC = s -> System.out.println(s);
        printC.accept("To be or not to be, that is the question");

        List<String> names = new ArrayList<>();
        names.add("John");names.add("Mary");
        names.forEach(printC);

        // BiConsumer<T, U> is a functional interface i.e. one abstract method:
        // void accept(T t, U u)
        var mapCapitalCities = new HashMap<String, String>();
        BiConsumer<String, String> biCon = (key, value) -> mapCapitalCities.put(key, value);
        biCon.accept("Dublin", "Ireland");
        biCon.accept("Washington D.C.", "USA");
        System.out.println(mapCapitalCities);

        BiConsumer<String, String> mapPrint = (key, value) -> System.out.println(key + " is the capital of: " + value);
        mapCapitalCities.forEach(mapPrint);
    }
}
