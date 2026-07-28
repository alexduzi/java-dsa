package br.com.alexduzi.maximum_subarray;

import java.util.Arrays;

public class MaximumSubarrayMain {

    // solução brute force
    static int maxSubArray(int[] v) {
        int maxSum = 0;

        for (int i = 0; i < v.length; i++) {
            int currentSum = 0;
            for (int j = i; j < v.length; j++) {
                currentSum += v[j];
                maxSum = Math.max(maxSum, currentSum);
            }
        }

        return maxSum;
    }

    // solução usando PD
    static int maxSubArray2(int[] v) {
        int[] localMax = new int[v.length];

        // caso base
        localMax[0] = v[0];
        int maxSum = v[0];

        for (int i = 1; i < v.length; i++) {
            localMax[i] = Math.max(v[i], v[i] + localMax[i-1]);
            maxSum = Math.max(maxSum, localMax[i]);
        }

        return maxSum;
    }

    // solução usando algoritimo de kadane
    static int maxSubArray3(int[] v) {
        int maxSum = v[0], currentSum = 0;

        for (int i = 1; i < v.length; i++) {
            currentSum = Math.max(v[i], v[i] + currentSum);
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[]{ -2, 1, -3, 4, -1, 2, 1, -5, 4 }));
        System.out.println(maxSubArray2(new int[]{ -2, 1, -3, 4, -1, 2, 1, -5, 4 }));
        System.out.println(maxSubArray3(new int[]{ -2, 1, -3, 4, -1, 2, 1, -5, 4 }));
    }
}
