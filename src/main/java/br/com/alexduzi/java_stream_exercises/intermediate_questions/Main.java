package br.com.alexduzi.java_stream_exercises.intermediate_questions;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

    }

    // 20. Sort a List of Strings by Their Length
    static void ex20() {
        List<String> words = List.of("apple", "banana", "kiwi");
        List<String> sortedWords = words.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());
        System.out.println("Sorted Words by Length: " + sortedWords);
    }

    // 21. Find the Sum of Digits of a Number
    static void ex21() {
        int number = 12345;
        int sum = String.valueOf(number).chars()
                .map(Character::getNumericValue)
                .sum();
        System.out.println("Sum of Digits: " + sum);
    }

    // 22. Find the Factorial of a Number
    static void ex22() {
        int number = 5;
        int factorial = IntStream.rangeClosed(1, number)
                .reduce(1, (a, b) -> a * b);
        System.out.println("Factorial: " + factorial);
    }

    // 23. Find the Second-Largest Element in a List
    static void ex23() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        int secondLargest = numbers.stream()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .orElse(0);
        System.out.println("Second Largest: " + secondLargest);
    }

    // 24. Find the Second-Smallest Element in a List
    static void ex24() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        int secondSmallest = numbers.stream()
                .sorted()
                .skip(1)
                .findFirst()
                .orElse(0);
        System.out.println("Second Smallest: " + secondSmallest);
    }

    // 25. Find the Longest String in a List
    static void ex25() {
        List<String> words = List.of("apple", "banana", "kiwi");
        String longest = words.stream()
                .max(Comparator.comparingInt(String::length))
                .orElse("");
        System.out.println("Longest Word: " + longest);
    }

    // 26. Find the Shortest String in a List
    static void ex26() {
        List<String> words = List.of("apple", "banana", "kiwi");
        String shortest = words.stream()
                .min(Comparator.comparingInt(String::length))
                .orElse("");
        System.out.println("Shortest Word: " + shortest);
    }

    // 27. Group a List of Strings by Their Length
    static void ex27() {
        List<String> words = List.of("apple", "banana", "kiwi");
        Map<Integer, List<String>> groupedByLength = words.stream()
                .collect(Collectors.groupingBy(String::length));
        System.out.println("Grouped by Length: " + groupedByLength);
    }

    // 28. Group a List of Objects by a Specific Attribute
    static void ex28() {
        class Person {
            String name;
            int age;
            Person(String name, int age) {
                this.name = name;
                this.age = age;
            }
            public int getAge() {
                return age;
            }
        }

        List<Person> people = List.of(
                new Person("Alice", 25),
                new Person("Bob", 30),
                new Person("Charlie", 25)
        );

        Map<Integer, List<Person>> groupedByAge = people.stream()
                .collect(Collectors.groupingBy(Person::getAge));
        System.out.println("Grouped by Age: " + groupedByAge);
    }

    // 29. Partition a List of Integers into Even and Odd Numbers
    static void ex29() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        Map<Boolean, List<Integer>> partitioned = numbers.stream()
                .collect(Collectors.partitioningBy(n -> n % 2 == 0));
        System.out.println("Partitioned: " + partitioned);
    }

    // 30. Merge Two Lists into a Single List
    static void ex30() {
        List<Integer> list1 = List.of(1, 2, 3);
        List<Integer> list2 = List.of(4, 5, 6);
        List<Integer> merged = Stream.concat(list1.stream(), list2.stream())
                .collect(Collectors.toList());
        System.out.println("Merged List: " + merged);
    }

    // 31. Find the Intersection of Two Lists
    static void ex31() {
        List<Integer> list1 = List.of(1, 2, 3, 4);
        List<Integer> list2 = List.of(3, 4, 5, 6);
        List<Integer> intersection = list1.stream()
                .filter(list2::contains)
                .collect(Collectors.toList());
        System.out.println("Intersection: " + intersection);
    }

    // 32. Find the Union of Two Lists
    static void ex32() {
        List<Integer> list1 = List.of(1, 2, 3);
        List<Integer> list2 = List.of(3, 4, 5);
        List<Integer> union = Stream.concat(list1.stream(), list2.stream())
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Union: " + union);
    }

    // 33. Find the Difference Between Two Lists
    static void ex33() {
        List<Integer> list1 = List.of(1, 2, 3, 4);
        List<Integer> list2 = List.of(3, 4, 5, 6);
        List<Integer> difference = list1.stream()
                .filter(n -> !list2.contains(n))
                .collect(Collectors.toList());
        System.out.println("Difference: " + difference);
    }

    // 34. Count the Occurrences of Each Element in a List
    static void ex34() {
        List<String> words = List.of("apple", "banana", "apple", "orange");
        Map<String, Long> wordCounts = words.stream()
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));
        System.out.println("Word Counts: " + wordCounts);
    }

    // 35. Count the Occurrences of Each Character in a String
    static void ex35() {
        String input = "hello";
        Map<Character, Long> charCounts = input.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
        System.out.println("Character Counts: " + charCounts);
    }

    // 36. Count the Occurrences of Each Word in a String
    static void ex36() {
        String input = "hello world hello";
        Map<String, Long> wordCounts = Arrays.stream(input.split(" "))
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));
        System.out.println("Word Counts: " + wordCounts);
    }

    // 37. Count the Occurrences of Each Vowel in a String
    static void ex37() {
        String input = "hello world";
        Map<Character, Long> vowelCounts = input.chars()
                .mapToObj(c -> (char) c)
                .filter(c -> "aeiou".contains(String.valueOf(c)))
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
        System.out.println("Vowel Counts: " + vowelCounts);
    }

    // 38. Count the Occurrences of Each Digit in a String
    static void ex38() {
        String input = "hello 123 world 456";
        Map<Character, Long> digitCounts = input.chars()
                .mapToObj(c -> (char) c)
                .filter(Character::isDigit)
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
        System.out.println("Digit Counts: " + digitCounts);
    }

    // 39. Reverse a List Using Streams
    static void ex39() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        List<Integer> reversed = numbers.stream()
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> {
                    Collections.reverse(list);
                    return list;
                }));
        System.out.println("Reversed List: " + reversed);
    }

    // 40. Reverse a String Using Streams
    static void ex40() {
        String input = "hello";
        String reversed = input.chars()
                .mapToObj(c -> String.valueOf((char) c))
                .reduce("", (a, b) -> b + a);
        System.out.println("Reversed String: " + reversed);
    }
}
