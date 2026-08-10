package br.com.alexduzi.dynamic_programming._1_fib;

public class Main {
    public static void main(String[] args) {
//        System.out.println(fib(70));
//        System.out.println(fib(70));
        System.out.println(fib3(60));
        System.out.println(fib4(60));
    }

    // imperative, with for loop
    // Complexity time O(n) - space O(1)
    static long fib(int n) {
        if (n <= 0) return 0;

        if (n == 1) return 1;

        long curr = 1, prev = 0;

        for (int i = 2; i <= n; i++) {
            long next = curr + prev;
            prev = curr;
            curr = next;
        }

        return curr;
    }

    // naive recursive implementation
    // Complexity time O(2^n) - space O(1)
    static long fib2(int n) {
        if (n <= 0) return 0;

        if (n == 1) return 1;

        return fib2(n - 1) + fib2(n - 2);
    }

    // dynamic programming memoization topdown solution
    // Complexity time O(n) - space O(n)
    static long fib3(int n) {
        long[] memo = new long[n + 1];
        return fib3Memo(n, memo);
    }

    static long fib3Memo(int n, long[] memo) {
        if (n <= 0) return 0;

        if (n == 1) return 1;

        if (memo[n] != 0) {
            return memo[n];
        }

        memo[n] = fib3Memo(n - 1, memo) + fib3Memo(n - 2, memo);

        return memo[n];
    }

    // dynamic programming table bottomup solution
    // Complexity time O(n) - space O(n)
    static long fib4(int n) {
        if (n <= 0) return 0;

        if (n == 1) return 1;

        long[] table = new long[n + 1];

        // base cases
        table[0] = 0;
        table[1] = 1;

        for (int i = 2; i <= n; i++) {
            table[i] = table[i - 1] + table[i - 2];
        }

        return table[n];
    }
}
