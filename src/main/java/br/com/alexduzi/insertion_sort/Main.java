package br.com.alexduzi.insertion_sort;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(insertionSort(new int[]{ 20, 9, 86, -2, 16, 13, 34, 4 })));
    }

    // esse algorítimo sempre verifica os elementos a esquerda e move para a direita
    static int[] insertionSort(int[] nums) {
        int N = nums.length;

        for (int i = 1; i < N; i++) {
            int aux = nums[i];

            int j = i - 1;
            while (j >= 0 && nums[j] > aux) {
                nums[j+1] = nums[j];
                j -= 1;
            }

            nums[j+1] = aux;
        }

        return nums;
    }
}
