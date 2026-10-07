package br.com.alexduzi.java_maxminwithstreams;

import java.util.*;
import java.util.stream.Stream;

public class MaxMinWithStreams {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 9, 8, 7, 5, 6, 2, 3, 4, 1);
        Stream<Integer> stream = numbers.stream();
        IntSummaryStatistics stats = stream.mapToInt(Integer::intValue).summaryStatistics();

        System.out.println("Max value is: " + stats.getMax());
        System.out.println("Min value is: " + stats.getMin());

        stream = numbers.stream();
        Optional<Integer> max = stream.max(Comparator.comparingInt(Integer::intValue));

        stream = numbers.stream();
        Optional<Integer> min = stream.min(Comparator.comparingInt(Integer::intValue));

        System.out.println("Max value is: " + max.get());
        System.out.println("Min value is: " + min.get());
    }
}
