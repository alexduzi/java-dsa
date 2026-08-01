package br.com.alexduzi.dot_product_two_arrays;

import java.util.Arrays;

public class Main {
    // Dados dois arrays, calcule o seu produto escalar.
    // Entrada: nums1 = [1,0,0,2,3], nums2 = [0,3,0,4,0]
    // saída: 8
    // Explicação: O produto escalar dos arrays acima pode ser encontrado pela expressão: (1 * 0) + (0 * 3) + (0 * 0) + (2 * 4) + (3 * 0) = 8
    // entrada: nums1 = [0,1,0,0,0], nums2 = [0,0,0,0,2]
    // saída: 0
    public static void main(String[] args) {
        System.out.println(dotProduct(new int[] { 1, 0, 0, 2, 3 }, new int[] { 0, 3, 0, 4, 0 }));
        System.out.println(dotProduct(new int[] { 0, 1, 0, 0, 0 }, new int[] { 0, 0, 0, 0, 2 }));
    }

    static int dotProduct(int[] nums1, int[] nums2) {
        int product = 0;
        for (int i = 0; i < nums1.length; i++) {
            product += nums1[i] * nums2[i];
        }
        return product;
    }
}
