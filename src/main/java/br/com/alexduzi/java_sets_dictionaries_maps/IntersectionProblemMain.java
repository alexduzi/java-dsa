package br.com.alexduzi.java_sets_dictionaries_maps;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IntersectionProblemMain {
    // ref: Leetcode intersection-of-two-arrays
    // Dados dois arrays num1 e num2, retorne um array contendo sua interseção.
    // Cada elemento do array resultante deve ser único,
    // e você pode apresentar os elementos em qualquer ordem.
    public static void main(String[] args) {
        int[] nums1 = new int[] { 1, 2, 2, 1 };
        int[] nums2 = new int[] { 2, 2 };

        int[] result = intersection(nums1, nums2);

        System.out.println(Arrays.toString(result));
    }

    static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums1) {
            set.add(num);
        }

        Set<Integer> resultSet = new HashSet<>();
        for (Integer num : nums2) {
            if (set.contains(num)) {
                resultSet.add(num);
            }
        }

        // Convert to primitive array
        int[] resultArray = new int[resultSet.size()];
        int i = 0;
        for (Integer num : resultSet) {
            resultArray[i++] = num;
        }

        // result.stream().mapToInt(Integer::intValue).toArray();
        return resultArray;
    }
}
