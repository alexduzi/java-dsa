package br.com.alexduzi.lambda_expressions;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class TestFunctionMain {
    public static void main(String[] args) {
        // use to transform the input an output (types can be different)
        // Function<T, R> is a functional interface i.e. one abstract method:
        // R apply(T t)

        Function<String, Integer> fn2 = s -> s.length();
        System.out.println("Function: " + fn2.apply("Moscow"));

        // Bifunction<T, U, R> is a functional interface i.e. one abstract method:
        // R apply(T t, U u)
        BiFunction<String, String, Integer> biFn = (s1, s2) -> s1.length() + s2.length();
        System.out.println("BiFunction: " + biFn.apply("William", "Shakespeare"));

        BiFunction<String, String, String> biFn2 = (s1, s2) -> s1.concat(s2);
        System.out.println("BiFunction: " + biFn2.apply("William", "Shakespeare"));

        // used for transform the input into an output (types are the same)
        // UnaryOperator<T> extends Function<T, T> is a functional interface i.e. one abstract method:
        // T apply(T t)
        UnaryOperator<String> unaryOp = name -> "My name is " +name;
        System.out.println("UnaryOperator: " + unaryOp.apply("Sean"));

        // BinaryOperator<T> extends BiFunction<T, T, T> is a functional interface i.e one abstract method:
        // T apply(T t1, T t2)
        BinaryOperator<String> binaryOp = (s1, s2) -> s1.concat(s2);
        System.out.println("BinaryOperator: " + binaryOp.apply("William", "Shakespeare"));
    }
}
