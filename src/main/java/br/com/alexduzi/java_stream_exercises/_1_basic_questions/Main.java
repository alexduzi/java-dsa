package br.com.alexduzi.java_stream_exercises._1_basic_questions;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

    }

    // 1. Find the Sum of All Elements in a List
    static void ex1() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        int sum = numbers.stream().mapToInt(Integer::intValue).sum();
        System.out.println("Sum: " + sum);
    }

    // 2. Find the Product of All Elements in a List
    static void ex2() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        int product = numbers.stream().reduce(1, (a, b) -> a * b);
        System.out.println("Product: " + product);
    }

    // 3. Find the Average of All Elements in a List
    static void ex3() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        double avg = numbers.stream().mapToInt(Integer::intValue).average().orElse(0);
        System.out.println("Average: " + avg);
    }

    // 4. Find the Maximum Element in a List
    static void ex4() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        int max = numbers.stream().max(Integer::compare).orElse(0);
        System.out.println("Max number: " + max);

        IntSummaryStatistics stats = numbers.stream().mapToInt(Integer::intValue).summaryStatistics();
        System.out.println("Max number: " + stats.getMax());
    }

    // 5. Find the Minimum Element in a List
    static void ex5() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        int min = numbers.stream().min(Integer::compare).orElse(0);
        System.out.println("Min number: " + min);
    }

    // 6. Count the Number of Elements in a List
    static void ex6() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        long total = numbers.stream().count(); // numbers.size();
        System.out.println("Total elements: " + total);
    }

    // 7. Check if a List Contains a Specific Element
    static void ex7() {
        int n1 = 3;
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        boolean hasNumber = numbers.stream().anyMatch(n -> n == n1);
        System.out.printf("Number %d is on the list?: %s\n", n1, hasNumber);
    }

    // 8. Filter Out Even Numbers from a List
    static void ex8() {
        // even number n % 2 == 0 (par)
        // odd number n % 2 != 0 (ímpar)
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        List<Integer> evenNumbers = numbers.stream().filter(n -> n % 2 == 0).collect(Collectors.toList());
        System.out.println("Even numbers: " + evenNumbers);
    }

    // 9. Filter Out Odd Numbers from a List
    static void ex9() {
        // even number n % 2 == 0 (par)
        // odd number n % 2 != 0 (ímpar)
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        List<Integer> oddNumbers = numbers.stream().filter(n -> n % 2 != 0).collect(Collectors.toList());
        System.out.println("Odd numbers: " + oddNumbers);
    }

    // 10. Convert a List of Strings to Uppercase
    static void ex10() {
        List<String> words = List.of("hello", "world");
        List<String> upperWords = words.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println("Upper words: " + upperWords);
    }

    // 11. Convert a List of Integers to Their Squares
    static void ex11() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        List<Integer> squareInts = numbers.stream().map(n -> n * n).collect(Collectors.toList());
        System.out.println("Squares: " + squareInts);
    }

    // 12. Find the First Element in a List
    static void ex12() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        int first = numbers.stream().findFirst().orElse(0);
        System.out.println("First element: " + first);
    }

    // 13. Find the Last Element in a List
    static void ex13() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        int last = numbers.stream().reduce((a, b) -> b).orElse(0);
        System.out.println("Last element: " + last);
    }

    // 14. Check if All Elements in a List Satisfy a Condition
    static void ex14() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        boolean allEven = numbers.stream().allMatch(n -> n % 2 == 0);
        System.out.println("All Even: " + allEven);
    }

    // 15. Check if Any Element in a List Satisfies a Condition
    static void ex15() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        boolean anyEven = numbers.stream().anyMatch(n -> n % 2 == 0);
        System.out.println("Any Even: " + anyEven);
    }

    // 16. Remove Duplicate Elements from a List
    static void ex16() {
        List<Integer> numbers = List.of(1, 2, 2, 3, 4, 4, 5);
        List<Integer> uniqueNumbers = numbers.stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Unique Numbers: " + uniqueNumbers);
    }

    // 17. Sort a List of Integers in Ascending Order
    static void ex17() {
        List<Integer> numbers = List.of(5, 3, 1, 4, 2);
        List<Integer> sortedNumbers = numbers.stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("Sorted Numbers: " + sortedNumbers);
    }

    // 18. Sort a List of Integers in Descending Order
    static void ex18() {
        List<Integer> numbers = List.of(5, 3, 1, 4, 2);
        List<Integer> sortedNumbers = numbers.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        System.out.println("Sorted Numbers (Descending): " + sortedNumbers);
    }

    // 19. Sort a List of Strings in Alphabetical Order
    static void ex19() {
        List<String> words = List.of("banana", "apple", "cherry");
        List<String> sortedWords = words.stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("Sorted Words: " + sortedWords);
    }

    // 20. Sort a List of Strings by Their Length
    static void ex20() {
        List<String> words = List.of("apple", "banana", "kiwi");
        List<String> sortedWords = words.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());
        System.out.println("Sorted Words by Length: " + sortedWords);
    }
}
