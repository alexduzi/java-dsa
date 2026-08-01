package br.com.alexduzi.search_algorithms;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println(search(new int[] { 2, 4, 6, 8, 10, 9, 11, 12 }, 9));
        System.out.println(binarySearch(new int[] { 2, 4, 6, 8, 10, 9, 11, 12 }, 9));
        System.out.println(binarySearchRecursive(new int[] { 2, 4, 6, 8, 10, 9, 11, 12 }, 9));
    }

    // busca sequencial
    // complexidade O(n)
    static int search(int[] numbers, int number) {
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == number) {
                return i;
            }
        }
        return -1;
    }

    static int binarySearch(int[] numbers, int number) {
        int low = 0, middle = 0, high = numbers.length - 1;

        while (low <= high) {
            middle = (int)Math.floor((double)(low + high) / 2);
            if (number < numbers[middle]) {
                high = middle - 1;
            } else if (number > numbers[middle]) {
                low = middle + 1;
            } else {
                return middle;
            }
        }

        return -1;
    }

    static int binarySearchRecursive(int[] numbers, int number) {
        return binarySearchRecursiveHelper(numbers, number, 0, numbers.length - 1);
    }

    static int binarySearchRecursiveHelper(int[] numbers, int number, int low, int high) {
        if (low > high) {
            return - 1;
        }

        int middle = (int)Math.floor((double)(low + high) / 2);

        if (number == numbers[middle]) {
            return middle;
        } else if (number < numbers[middle]) {
            return binarySearchRecursiveHelper(numbers, number, low, middle - 1);
        } else {
            return binarySearchRecursiveHelper(numbers, number, middle+1, high);
        }
    }
}
