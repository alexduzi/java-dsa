package br.com.alexduzi.duplicate_zeros;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] nums1 = new int[] { 1, 0, 2, 3, 0, 4, 5, 0 };
        int[] nums2 = new int[] { 1, 2, 3 };

        duplicateZeros(nums1);
        duplicateZeros(nums2);

        System.out.println(Arrays.toString(nums1));
        System.out.println(Arrays.toString(nums2));
    }

    static void duplicateZeros(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                for (int j = arr.length - 2; j > i; j--) { // começa da penúltima posição, esse for irá percorrer o sentido inverso
                    arr[j+1] = arr[j]; // deslocando os elementos para a direita
                }
                arr[i+1] = arr[i]; // coloca o zero na prox posição
                i++;
            }
        }
    }
}
