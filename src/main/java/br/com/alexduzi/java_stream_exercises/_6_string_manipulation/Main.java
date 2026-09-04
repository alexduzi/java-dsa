package br.com.alexduzi.java_stream_exercises._6_string_manipulation;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

    }

    // 71. Remove All Vowels from a String
    static void ex71() {
        String input = "hello world";
        String result = input.chars()
                .filter(c -> !"aeiou".contains(String.valueOf((char)c)))
                .mapToObj(c -> String.valueOf((char)c))
                .collect(Collectors.joining());
        System.out.println("Result: " + result);
    }

    // 72. Remove All Consonants from a String
    static void ex72() {
        String input = "hello world";
        String result = input.chars()
                .filter(c -> "aeiou".contains(String.valueOf((char) c)))
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.joining());
        System.out.println("Result: " + result);
    }

    // 73. Remove All Digits from a String
    static void ex73() {
        String input = "hello 123 world";
        String result = input.chars()
                .filter(c -> !Character.isDigit(c))
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.joining());
        System.out.println("Result: " + result);
    }

    // 74. Remove All Special Characters from a String
    static void ex74() {
        String input = "hello@world!";
        String result = input.chars()
                .filter(c -> Character.isLetterOrDigit(c) || Character.isWhitespace(c))
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.joining());
        System.out.println("Result: " + result);
    }

    // 75. Extract All Digits from a String and Sum Them
    static void ex75() {
        String input = "hello 123 world 456";
        int sum = input.chars()
                .filter(Character::isDigit)
                .map(Character::getNumericValue)
                .sum();
        System.out.println("Sum of Digits: " + sum);
    }

    // 76. Extract All Words from a String and Count Their Occurrences
    static void ex76() {
        String input = "hello world hello";
        Map<String, Long> wordCounts = Arrays.stream(input.split(" "))
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));
        System.out.println("Word Counts: " + wordCounts);
    }

    // 77. Extract All Unique Words from a String
    static void ex77() {
        String input = "hello world hello";
        List<String> uniqueWords = Arrays.stream(input.split(" "))
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Unique Words: " + uniqueWords);
    }

    // 78. Extract All Palindromic Words from a String
    static void ex78() {
        String input = "madam racecar apple banana level";
        List<String> palindromes = Arrays.stream(input.split(" "))
                .filter(word -> word.equals(new StringBuilder(word).reverse().toString()))
                .collect(Collectors.toList());
        System.out.println("Palindromes: " + palindromes);
    }

    // 79. Extract All Words Starting with a Specific Letter
    static void ex79() {
        String input = "hello world this is a test";
        List<String> wordsStartingWithT = Arrays.stream(input.split(" "))
                .filter(word -> word.startsWith("t"))
                .collect(Collectors.toList());
        System.out.println("Words Starting with 't': " + wordsStartingWithT);
    }

    // 80. Extract All Words Ending with a Specific Letter
    static void ex80() {
        String input = "hello world this is a test";
        List<String> wordsEndingWithD = Arrays.stream(input.split(" "))
                .filter(word -> word.endsWith("d"))
                .collect(Collectors.toList());
        System.out.println("Words Ending with 'd': " + wordsEndingWithD);
    }
}
