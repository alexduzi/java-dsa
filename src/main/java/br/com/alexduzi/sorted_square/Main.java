package br.com.alexduzi.sorted_square;

import java.util.Arrays;

public class Main {
    // Dado um array de números inteiros nums ordenado em ordem crescente,
    // retorne um array com os quadrados de cada número, também ordenado de forma crescente.
    // entrada: nums = [-4,-1,0,3,10]
    // saída: [0,1,9,16,100]
    // Explicação: Após elevar ao quadrado, temos como resultado o array [16, 1, 0, 9, 100].
    // Em seguida, após ordenar os valores do array, temos [0, 1, 9, 16, 100].
    public static void main(String[] args) {
        System.out.println(Arrays.toString(sortedSquares(new int[]{ -4, -1, 0, 3, 10 })));
        System.out.println(Arrays.toString(sortedSquares(new int[]{ -7, -3, 2, 3, 11 })));
    }

    static int[] sortedSquares(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            nums[i] *= nums[i];
        }
        Arrays.sort(nums);
        return nums;
    }
}
