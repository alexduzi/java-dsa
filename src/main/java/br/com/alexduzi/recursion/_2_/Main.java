package br.com.alexduzi.recursion._2_;

public class Main {
    public static void main(String[] args) {
        System.out.println(fat(3));
    }

    static int fat(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }

        return n + fat(n - 1);
    }

    // solução usando recursão em calda
    static int fat2(int n) {
        return fat2Helper(n, 1);
    }

    static int fat2Helper(int n, int total) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return fat2Helper(n - 1, n * total);
    }
}
