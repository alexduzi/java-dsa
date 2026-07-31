package br.com.alexduzi.merge_sorted_array;

import java.util.Arrays;

public class Main {
    // Dado dois arrays de números inteiros nums1 e nums2, ordenados em ordem crescente, sendo m e n seus tamanhos, respectivamente.

    // Junte os arrays nums1 e nums2 em um único array ordenado de forma crescente.

        // O array final ordenado não deve ser retornado pela função, mas sim armazenado dentro do array nums1.
    // Para acomodar os elementos, nums1 possui um comprimento de m + n.
    // entrada: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
    // saída: [1,2,2,3,5,6]
    // entrada: nums1 = [1], m = 1, nums2 = [], n = 0
    // saída: [1]
    public static void main(String[] args) {
        int[] nums1 = new int[] { 1, 2, 3, 0, 0, 0 };
        int[] nums2 = new int[] { 2, 5, 6 };

        int m = 3;
        int n = 3;

        merge(nums1, m, nums2, n);

        System.out.println(Arrays.toString(nums1));
    }

    static void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int i = 0; i < n; i++) {
            nums1[m+i] = nums2[i]; // nums[m+i] começa a partir da prox posição
        }
        Arrays.sort(nums1);
    }
}
