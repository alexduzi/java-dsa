package br.com.alexduzi.missing_number;

public class Main {
    public static void main(String[] args) {
        System.out.println(missingNumber(new int[] { 0, 1, 2, 4, 5, 6, 7, 8, 9 })); // output 3
    }

    static int missingNumber(int[] nums) {
        int n = nums.length;
        int sum = n * (n + 1) / 2; // formula da soma de todos os números naturais até n.

        for (int num : nums) {
            sum -= num;
        }

        return sum;
    }
}
