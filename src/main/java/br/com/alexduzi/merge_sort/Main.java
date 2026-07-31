package br.com.alexduzi.merge_sort;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] nums = new int[]{ 20, 9, 86, -2, 16, 13, 34, 4 };
        System.out.println(Arrays.toString(mergeSort(nums, 0, 7 )));
    }

    static int[] mergeSort(int[] nums, int left, int right) {
        if (left < right) {
            int middle = (int)Math.floor((double)(left+right)/2);
            mergeSort(nums, left, middle);
            mergeSort(nums, middle+1, right);
            merge(nums, left, middle, right);
        }
        return nums;
    }

    static void merge(int[] nums, int left, int middle, int right) {
        int len = right - left + 1;
        int[] result = new int[len];
        int i = left;
        int j = middle + 1;
        int k = 0;

        while(i <= middle && j <= right) {
            if (nums[i] < nums[j]) {
                result[k] = nums[i];
                k++;
                i++;
            } else {
                result[k] = nums[j];
                k++;
                j++;
            }
        }

        while (i <= middle) {
            result[k] = nums[i];
            k++;
            i++;
        }

        while (j <= middle) {
            result[k] = nums[j];
            k++;
            j++;
        }

        while (j <= right) {
            result[k] = nums[j];
            k++;
            j++;
        }

        for (int l = 0; l < len; l++) {
            nums[left+l] = result[l];
        }
    }
}
