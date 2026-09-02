package br.com.alexduzi.java_stream_exercises._3_advanced_questions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

    }

    // 41. Find the Most Frequent Element in a List
    static void ex41() {
        List<String> words = List.of("apple", "banana", "apple", "orange", "banana", "apple");
        String mostFrequent = words.stream()
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
        System.out.println("Most Frequent: " + mostFrequent);
    }

    // 42. Find the Least Frequent Element in a List
    static void ex42() {
        List<String> words = List.of("apple", "banana", "apple", "orange", "banana", "apple");
        String leastFrequent = words.stream()
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()))
                .entrySet().stream()
                .min(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
        System.out.println("Least Frequent: " + leastFrequent);
    }

    // 43. Find the First Non-Repeated Character in a String
    static void ex43() {
        String input = "hello";
        Character firstNonRepeated = input.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
        System.out.println("First Non-Repeated Character: " + firstNonRepeated);
    }

    // 44. Find the First Repeated Character in a String
    static void ex44() {
        String input = "hello";
        Character firstRepeated = input.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
        System.out.println("First Repeated Character: " + firstRepeated);
    }

    // 45. Check if a String is a Palindrome
    static void ex45() {
        String input = "madam";
        boolean isPalindrome = IntStream.range(0, input.length() / 2)
                .allMatch(i -> input.charAt(i) == input.charAt(input.length() - 1 - i));
        System.out.println("Is Palindrome: " + isPalindrome);
    }

    // 46. Find All Anagrams of a String from a List
    static void ex46() {
        List<String> words = List.of("listen", "silent", "enlist", "google", "inlets");
        String target = "silent";
        List<String> anagrams = words.stream()
                .filter(word -> Arrays.equals(
                        word.chars().sorted().toArray(),
                        target.chars().sorted().toArray()
                ))
                .collect(Collectors.toList());
        System.out.println("Anagrams: " + anagrams);
    }

    // 47. Generate the Fibonacci Sequence Using Streams
    static void ex47() {
        Stream.iterate(new int[]{0, 1}, fib -> new int[]{fib[1], fib[0] + fib[1]})
                .limit(10)
                .map(fib -> fib[0])
                .forEach(System.out::println);
    }

    // 48. Generate a List of Random Numbers Using Streams
    static void ex48() {
        List<Integer> randomNumbers = Stream.generate(() -> new Random().nextInt(100))
                .limit(10)
                .collect(Collectors.toList());
        System.out.println("Random Numbers: " + randomNumbers);
    }

    // 49. Flatten a List of Lists into a Single List
    static void ex49() {
        List<List<Integer>> listOfLists = List.of(
                List.of(1, 2, 3),
                List.of(4, 5, 6),
                List.of(7, 8, 9)
        );
        List<Integer> flattened = listOfLists.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
        System.out.println("Flattened List: " + flattened);
    }

    // 50. Find the Sum of All Even Numbers in a Nested List
    static void ex50() {
        List<List<Integer>> listOfLists = List.of(
                List.of(1, 2, 3),
                List.of(4, 5, 6),
                List.of(7, 8, 9)
        );
        int sum = listOfLists.stream()
                .flatMap(List::stream)
                .filter(n -> n % 2 == 0)
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("Sum of Even Numbers: " + sum);
    }

    // 51. Find the Sum of All Odd Numbers in a Nested List
    static void ex51() {
        List<List<Integer>> listOfLists = List.of(
                List.of(1, 2, 3),
                List.of(4, 5, 6),
                List.of(7, 8, 9)
        );
        int sum = listOfLists.stream()
                .flatMap(List::stream)
                .filter(n -> n % 2 != 0)
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("Sum of Odd Numbers: " + sum);
    }

    // 52. Find the Longest Palindrome in a List of Strings
    static void ex52() {
        List<String> words = List.of("madam", "racecar", "apple", "banana", "level");
        String longestPalindrome = words.stream()
                .filter(word -> word.equals(new StringBuilder(word).reverse().toString()))
                .max(Comparator.comparingInt(String::length))
                .orElse("");
        System.out.println("Longest Palindrome: " + longestPalindrome);
    }

    // 53. Find the Shortest Palindrome in a List of Strings
    static void ex53() {
        List<String> words = List.of("madam", "racecar", "apple", "banana", "level");
        String shortestPalindrome = words.stream()
                .filter(word -> word.equals(new StringBuilder(word).reverse().toString()))
                .min(Comparator.comparingInt(String::length))
                .orElse("");
        System.out.println("Shortest Palindrome: " + shortestPalindrome);
    }

    // 54. Find the Longest Word in a String
    static void ex54() {
        String input = "hello world this is a test";
        String longestWord = Arrays.stream(input.split(" "))
                .max(Comparator.comparingInt(String::length))
                .orElse("");
        System.out.println("Longest Word: " + longestWord);
    }

    // 55. Find the Shortest Word in a String
    static void ex55() {
        String input = "hello world this is a test";
        String shortestWord = Arrays.stream(input.split(" "))
                .min(Comparator.comparingInt(String::length))
                .orElse("");
        System.out.println("Shortest Word: " + shortestWord);
    }

    // 56. Find the Number of Words in a String
    static void ex56() {
        String input = "hello world this is a test";
        long wordCount = Arrays.stream(input.split(" ")).count();
        System.out.println("Word Count: " + wordCount);
    }

    // 57. Find the Number of Lines in a File
    static void ex57() throws IOException {
        Path path = Paths.get("sample.txt");
        long lineCount = Files.lines(path).count();
        System.out.println("Line Count: " + lineCount);
    }

    // 58. Find the Number of Characters in a File
    static void ex58() throws IOException {
        Path path = Paths.get("sample.txt");
        long charCount = Files.lines(path)
                .flatMapToInt(String::chars)
                .count();
        System.out.println("Character Count: " + charCount);
    }

    // 59. Find the Number of Words in a File
    static void ex59() throws IOException {
        Path path = Paths.get("sample.txt");
        long wordCount = Files.lines(path)
                .flatMap(line -> Arrays.stream(line.split(" ")))
                .count();
        System.out.println("Word Count: " + wordCount);
    }

    // 60. Find the Number of Unique Words in a File
    static void ex60() throws IOException {
        Path path = Paths.get("sample.txt");
        long uniqueWordCount = Files.lines(path)
                .flatMap(line -> Arrays.stream(line.split(" ")))
                .distinct()
                .count();
        System.out.println("Unique Word Count: " + uniqueWordCount);
    }
}
