package br.com.alexduzi.java_stream_exercises._8_parallel_streams;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

    }

    // 91. Process a Large List of Numbers in Parallel
    static void ex91() {
        List<Integer> numbers = IntStream.rangeClosed(1, 1000000).boxed().collect(Collectors.toList());
        long sum = numbers.parallelStream()
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("Sum: " + sum);
    }

    // 92. Find the Sum of All Elements in a List Using Parallel Streams
    static void ex92() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        int sum = numbers.parallelStream()
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("Sum: " + sum);
    }

    // 93. Find the Maximum Element in a List Using Parallel Streams
    static void ex93() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        int max = numbers.parallelStream()
                .max(Integer::compare)
                .orElse(0);
        System.out.println("Max: " + max);
    }

    // 94. Find the Minimum Element in a List Using Parallel Streams
    static void ex94() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        int min = numbers.parallelStream()
                .min(Integer::compare)
                .orElse(0);
        System.out.println("Min: " + min);
    }

    // 95. Sort a List of Integers in Parallel Using Parallel Streams
    static void ex95() {
        List<Integer> numbers = List.of(5, 3, 1, 4, 2);
        List<Integer> sortedNumbers = numbers.parallelStream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("Sorted Numbers: " + sortedNumbers);
    }

    // 96. Filter a List of Strings in Parallel Using Parallel Streams
    static void ex96() {
        List<String> words = List.of("apple", "banana", "kiwi", "mango");
        List<String> filteredWords = words.parallelStream()
                .filter(word -> word.length() > 4)
                .collect(Collectors.toList());
        System.out.println("Filtered Words: " + filteredWords);
    }

    // 97. Count the Occurrences of Each Element in a List Using Parallel Streams
    static void ex97() {
        List<String> words = List.of("apple", "banana", "apple", "orange");
        Map<String, Long> wordCounts = words.parallelStream()
                .collect(Collectors.groupingByConcurrent(s -> s, Collectors.counting()));
        System.out.println("Word Counts: " + wordCounts);
    }

    // 98. Group a List of Objects by a Specific Attribute Using Parallel Streams
    static void ex98() {
        class Employee {
            String name;
            String department;
            // Constructor, getters, and setters
            public Employee(String name, String department) {
                this.name = name;
                this.department = department;
            }
            public String getDepartment() {
                return this.department;
            }
        }

        List<Employee> employees = List.of(
                new Employee("Alice", "HR"),
                new Employee("Bob", "IT"),
                new Employee("Charlie", "HR")
        );

        Map<String, List<Employee>> groupedByDept = employees.parallelStream()
                .collect(Collectors.groupingByConcurrent(Employee::getDepartment));
        System.out.println("Grouped by Department: " + groupedByDept);
    }

    // 99. Merge Two Lists in Parallel Using Parallel Streams
    static void ex99() {
        List<Integer> list1 = List.of(1, 2, 3);
        List<Integer> list2 = List.of(4, 5, 6);
        List<Integer> merged = Stream.concat(list1.parallelStream(), list2.parallelStream())
                .collect(Collectors.toList());
        System.out.println("Merged List: " + merged);
    }

    // 100. Find the Intersection of Two Lists Using Parallel Streams
    static void ex100() {
        List<Integer> list1 = List.of(1, 2, 3, 4);
        List<Integer> list2 = List.of(3, 4, 5, 6);
        List<Integer> intersection = list1.parallelStream()
                .filter(list2::contains)
                .collect(Collectors.toList());
        System.out.println("Intersection: " + intersection);
    }
}
