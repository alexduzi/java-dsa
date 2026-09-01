package br.com.alexduzi.selection_sort;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(selectionSort(new int[] { 20, 9, 86, -2, 16, 13, 34, 4 })));
    }

    static int[] selectionSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
                if (minIndex != i) {
                    int aux = nums[minIndex];
                    nums[minIndex] = nums[i];
                    nums[i] = aux;
                }
            }
        }
        return nums;
    }
}
