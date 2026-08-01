package br.com.alexduzi.reverse_array;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(reverse(new int[] { 1, 2, 3, 4, 5, 6 })));
        System.out.println(Arrays.toString(reverse2(new int[] { 1, 2, 3, 4, 5, 6 })));
    }

    static int[] reverse(int[] arr) {
        int[] newArr = new int[arr.length];

        for (int i = 0, j = arr.length - 1; i < arr.length; i++, j--) {
            newArr[i] = arr[j];
        }

        return newArr;
    }

    static int[] reverse2(int[] arr) {
        int[] newArr = new int[arr.length];

        int start = 0, end = arr.length - 1;

        while (start < arr.length) {
            newArr[start] = arr[end];
            start++;
            end--;
        }

        return newArr;
    }
}
