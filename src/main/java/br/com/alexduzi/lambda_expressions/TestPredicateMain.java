package br.com.alexduzi.lambda_expressions;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

// custom function interface
interface EEvaluate<T> {
    boolean isNegative(T i);
}

public class TestPredicateMain {
    public static void main(String[] args) {
        // using the custom function interface
        EEvaluate<Integer> lambda = i -> i > 0;
        System.out.println("1 > 0 " + lambda.isNegative(1));
        System.out.println("-1 > 0 " + lambda.isNegative(-1));

        // When you want to filter/match
        // Predicate<T> is a functional interface i.e. one abstract method
        // boolean test(T t)
        Predicate<Integer> predLambda = i -> i > 0;
        System.out.println("1 > 0 " + predLambda.test(1));
        System.out.println("-1 > 0 " + predLambda.test(-1));

        // BiPredicate<T> is a functional interface i.e. one abstract method
        // boolean test(T t)
        BiPredicate<String, Integer> checkLength = (str, len) -> str.length() == len;
        System.out.println("Vatican city has length of 8: " + checkLength.test("Vatican city", 8));
    }
}
