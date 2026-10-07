package br.com.alexduzi.java_sumofdigits;

import java.util.stream.IntStream;

public class SumOfDigits {
    public static void main(String[] args) {
        int input = 12345; // output 15
        int sum = String.valueOf(input).chars()
                .map(Character::getNumericValue)
                .sum();
        System.out.println(sum);
    }
}
