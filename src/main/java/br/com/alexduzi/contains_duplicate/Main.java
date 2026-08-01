package br.com.alexduzi.contains_duplicate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        System.out.println(containsDuplicate(new int[] { 1, 2, 3, 1 }));
        System.out.println(containsDuplicate(new int[] { 1, 2, 3, 4 }));
        System.out.println(containsDuplicate(new int[] { 1, 1, 1, 3, 3, 4, 3, 2, 4, 2 }));

        System.out.println();

        System.out.println(containsDuplicate2(new int[] { 1, 2, 3, 1 }));
        System.out.println(containsDuplicate2(new int[] { 1, 2, 3, 4 }));
        System.out.println(containsDuplicate2(new int[] { 1, 1, 1, 3, 3, 4, 3, 2, 4, 2 }));
    }

    static boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)) {
                return true;
            }
        }
        return false;
    }

    static boolean containsDuplicate2(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i-1] == nums[i]) {
                return true;
            }
        }
        return false;
    }
}
