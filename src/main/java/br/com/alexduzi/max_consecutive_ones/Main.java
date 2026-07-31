package br.com.alexduzi.max_consecutive_ones;

public class Main {
    public static void main(String[] args) {
        System.out.println(findMaxConsecutiveOnes(new int[] { 1, 1, 0, 1, 1, 1 }));
        System.out.println(findMaxConsecutiveOnes(new int[] { 1, 0, 1, 1, 0, 1 }));
    }

    static int findMaxConsecutiveOnes(int[] nums) {
        int maxOnes = 0; // maior sequencia de 1s
        int countOnes = 0; // sequencia de 1s atual

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                countOnes++;
            }
            if (nums[i] != 1 && countOnes > 0) {
                maxOnes = Math.max(maxOnes, countOnes);
                countOnes = 0;
            }
        }
        maxOnes = Math.max(maxOnes, countOnes);

        return maxOnes;
    }
}
