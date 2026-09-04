package br.com.alexduzi.recursion._4_;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String[] list = {
                "azul", "verde", "preto", "rosa"
        };
        System.out.println(Arrays.toString(reverse(list)));
    }

    static String[] reverse(String[] list) {
        String[] newList = new String[list.length];
        for (int i = 0, j = list.length - 1; i < list.length; i++, j--) {
            newList[i] = list[j];
        }
        return newList;
    }
}
