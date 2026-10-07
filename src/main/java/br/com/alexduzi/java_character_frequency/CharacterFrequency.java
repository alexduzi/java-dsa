package br.com.alexduzi.java_character_frequency;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CharacterFrequency {
    public static void main(String[] args) {
        String input = "hello world";

        IntStream stream = input.chars();
        Map<Character, Long> characterLongMap = stream.mapToObj(c -> (char)c)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));
        System.out.println(characterLongMap);
    }
}
