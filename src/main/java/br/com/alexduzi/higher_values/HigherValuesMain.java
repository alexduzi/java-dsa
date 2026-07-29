package br.com.alexduzi.higher_values;

import java.util.Arrays;

public class HigherValuesMain {

    // Função que recebe um vetor de números, e retorna um novo vetor dizendo quantos elementos
    // maiores existem no vetor, para cada elemento do vetor
    static int[] higherValues(int[] arr) {
        int[] newArr = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] > arr[i]) {
                    newArr[i]++;
                }
            }
        }

        return newArr;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(higherValues(new int[]{ 7, 3, 8, 7, 5 })));
    }
}
