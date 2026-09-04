package br.com.alexduzi.recursion._3_;

public class Main {
    public static void main(String[] args) {
        System.out.println(fib2(47));
    }

    static int fib(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fib(n-1) + fib(n-2);
    }

    static int fib2(int n) {
        return fib2Helper(n, 0, 1);
    }

    static int fib2Helper(int n, int a, int b) {
        if (n == 0) {
           return a;
        }
        if (n == 1) {
           return b;
        }
        return fib2Helper(n-1, b, a+b);
    }
}
