package br.com.alexduzi.java_findsecondlargestnumber;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FindSecondLargestNumber {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 20, 35, 50, 50, 75, 65);
        int secondLargest = numbers.stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .get();
        System.out.println(secondLargest);
    }
}
