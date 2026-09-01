package br.com.alexduzi.quick_sort;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] nums = { 20, 9, 86, -2, 16, 13, 34, 4 };
        System.out.println(Arrays.toString(quickSort(nums, 0, 7)));
    }

    static int[] quickSort(int[] nums, int left, int right) {
        if (left < right) {
            int pivot = partition(nums, left, right);
            quickSort(nums, left, pivot-1);
            quickSort(nums, pivot+1, right);
        }
        return nums;
    }

    static int partition(int[] nums, int left, int right) {
        int pivot = nums[right];
        int i = left;
        for (int j = left; j < right; j++) {
            if (nums[j] < pivot) {
                int aux = nums[j];
                nums[j] = nums[i];
                nums[i] = aux;
                i++;
            }
        }
        int aux = nums[left];
        nums[left] = nums[right];
        nums[right] = aux;
        return i;
    }
}
