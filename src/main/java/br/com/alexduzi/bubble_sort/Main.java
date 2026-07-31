package br.com.alexduzi.bubble_sort;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(bubbleSort(new int[]{ 10, 20, 5, 3, 1 })));
    }

    static int[] bubbleSort(int[] nums) {
        int N = nums.length;

        for (int i = 0; i < N; i++) {
            boolean swapped = true;
            for (int j = 0; j < N-1-i; j++) {
                if (nums[j] > nums[j+1]) {
                    int aux = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = aux;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }

        return nums;
    }
}
