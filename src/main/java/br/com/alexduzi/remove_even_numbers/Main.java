package br.com.alexduzi.remove_even_numbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(removeEvenNumbers(new int[] { 1 , 2, 3, 4, 5, 6, 8, 9, 10 })));
    }

    static int[] removeEvenNumbers(int[] numbers) {
        List<Integer> newNumbers = new ArrayList<>();
        for (int num : numbers) {
            if (num % 2 != 0) { // remover números pares
                newNumbers.add(num);
            }
        }
        return newNumbers.stream().mapToInt(Integer::intValue).toArray();
    }

    static int[] removeEvenNumbers2(int[] numbers) {
        return Arrays.stream(numbers)
                .filter(num -> num % 2 != 0)
                .toArray();
    }
}
