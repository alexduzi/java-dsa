package br.com.alexduzi.matrix_multiply;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[][] m1 = new int[][] {
                {1, 2, 3},
                {4, 5, 6},
        };

        int[][] m2 = new int[][] {
                {7, 8},
                {9, 10},
                {11, 12},
        };

        int[][] m3 = matrixMultiply(m1, m2);

        System.out.println(Arrays.deepToString(m3));
    }

    // exemplo de algorítimo de ordem cúbica
    static int[][] matrixMultiply(int[][] m1, int[][] m2) {
        int M = m1.length; // linhas da matriz m1
        int N = m2[0].length; // colunas da matriz m2
        int P = m2.length;

        int[][] m3 = new int[M][N];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < P; k++) {
                    m3[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }

        return m3;
    }
}
