package br.com.alexduzi.java_stream_exercises._7_math_questions;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {

    }

    // 81. Find the Standard Deviation of a List of Numbers
    static void ex81() {
        List<Double> numbers = List.of(1.0, 2.0, 3.0, 4.0, 5.0);
        double mean = numbers.stream().mapToDouble(Double::doubleValue).average().orElse(0);
        double variance = numbers.stream()
                .mapToDouble(n -> Math.pow(n - mean, 2))
                .average().orElse(0);
        double stdDev = Math.sqrt(variance);
        System.out.println("Standard Deviation: " + stdDev);
    }

    // 82. Find the Median of a List of Numbers
    static void ex82() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        double median = numbers.stream()
                .sorted()
                .skip((numbers.size() - 1) / 2)
                .limit(2 - numbers.size() % 2)
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0);
        System.out.println("Median: " + median);
    }

    // 83. Find the Mode of a List of Numbers
    static void ex83() {
        List<Integer> numbers = List.of(1, 2, 2, 3, 4, 4, 4);
        Map<Integer, Long> frequencyMap = numbers.stream()
                .collect(Collectors.groupingBy(n -> n, Collectors.counting()));
        int mode = frequencyMap.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(0);
        System.out.println("Mode: " + mode);
    }

    // 84. Find the Sum of Squares of All Elements in a List
    static void ex84() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        int sumOfSquares = numbers.stream()
                .mapToInt(n -> n * n)
                .sum();
        System.out.println("Sum of Squares: " + sumOfSquares);
    }

    // 85. Find the Sum of Cubes of All Elements in a List
    static void ex85() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        int sumOfCubes = numbers.stream()
                .mapToInt(n -> n * n * n)
                .sum();
        System.out.println("Sum of Cubes: " + sumOfCubes);
    }

    // 86. Find the Sum of All Prime Numbers in a List
    static void ex86() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        int sumOfPrimes = numbers.stream()
                .filter(n -> n > 1 && IntStream.rangeClosed(2, (int) Math.sqrt(n)).noneMatch(i -> n % i == 0))
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("Sum of Primes: " + sumOfPrimes);
    }

    // 87. Find the Sum of All Fibonacci Numbers in a List
    static void ex87() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        int sumOfFibonacci = numbers.stream()
                .filter(n -> {
                    int a = 0, b = 1;
                    while (b < n) {
                        int temp = b;
                        b = a + b;
                        a = temp;
                    }
                    return b == n;
                })
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("Sum of Fibonacci Numbers: " + sumOfFibonacci);
    }

    // 88. Find the Sum of All Even-Indexed Elements in a List
    static void ex88() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        int sumOfEvenIndexed = IntStream.range(0, numbers.size())
                .filter(i -> i % 2 == 0)
                .map(numbers::get)
                .sum();
        System.out.println("Sum of Even-Indexed Elements: " + sumOfEvenIndexed);
    }

    // 89. Find the Sum of All Odd-Indexed Elements in a List
    static void ex89() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        int sumOfOddIndexed = IntStream.range(0, numbers.size())
                .filter(i -> i % 2 != 0)
                .map(numbers::get)
                .sum();
        System.out.println("Sum of Odd-Indexed Elements: " + sumOfOddIndexed);
    }

    // 90. Find the Sum of All Elements Greater Than a Specific Value
    static void ex90() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        int threshold = 5;
        int sum = numbers.stream()
                .filter(n -> n > threshold)
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("Sum of Elements > " + threshold + ": " + sum);
    }
}
