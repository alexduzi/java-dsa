package br.com.alexduzi.recursion._1_;

public class Main {
    public static void main(String[] args) {
        System.out.println(sumNaturals(5));
        System.out.println(sumNaturals(20));
        System.out.println(sumNaturals(17));
    }

    static int sumNaturals(int n) {
        if (n == 0) {
            return 0;
        }
        return n + sumNaturals(n - 1);
    }
}
