package br.com.alexduzi.lambda_expressions;

import java.time.LocalTime;
import java.util.function.Supplier;

public class TestSupplierMain {
    public static void main(String[] args) {
        // When you want to supply values without any input
        // Supplier<T> is a functional interface i.e. one abstract method
        // T get()
        Supplier<StringBuilder> supSB = () -> new StringBuilder();
        System.out.println("Supplier SB: " + supSB.get().append("SB"));

        Supplier<LocalTime> supTime = () -> LocalTime.now();
        System.out.println("Supplier Time: " + supTime.get());

        Supplier<Double> sRandom = () -> Math.random();
        System.out.println(sRandom.get());
    }
}
