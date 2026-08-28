package br.com.alexduzi.max_chars;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println(maxChar("abcccccccd"));
        System.out.println(maxChar("12311111"));
        System.out.println();
        System.out.println(maxChar2("abcccccccd"));
        System.out.println(maxChar2("12311111"));
    }

    static char maxChar(String str) {
        HashMap<Character, Integer> charCount = new HashMap<>();

        for (int i = 0; i < str.length(); i++) {
            charCount.merge(str.charAt(i), 1, Integer::sum);
        }

        char ch = str.charAt(0);
        int count = Integer.MIN_VALUE;
        for (Map.Entry<Character, Integer> entry : charCount.entrySet()) {
            if (count < entry.getValue()) {
                count = entry.getValue();
                ch = entry.getKey();
            }
        }

        return ch;
    }

    static char maxChar2(String str) {
        HashMap<Character, Integer> charCount = new HashMap<>();

        for (int i = 0; i < str.length(); i++) {
            charCount.merge(str.charAt(i), 1, Integer::sum);
        }

        return charCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .get().getKey();
    }
}
