package br.com.alexduzi.anagram;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AnagramMain {
    static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();

        Arrays.sort(sArray);
        Arrays.sort(tArray);

        return Arrays.equals(sArray, tArray);
    }

    static boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            map.merge(s.charAt(i), 1, Integer::sum);
            map.merge(t.charAt(i), -1, Integer::sum);
        }

        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() != 0) {
                return false;
            }
        }

        return true;
    }

    static boolean isAnagram3(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        // tabela unicode
        int[] table = new int[256];

        for (int i = 0; i < s.length(); i++) {
            table[s.charAt(i)]++;
            table[t.charAt(i)]--;
        }

        for (int item : table) {
            if (item != 0) {
                return false;
            }
        }

        return true;
    }

    static boolean isAnagram4(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            map.merge(s.charAt(i), 1, Integer::sum);
            map.merge(t.charAt(i), -1, Integer::sum);
        }

        return map.values().stream().anyMatch(v -> v != 0);
    }

    public static void main(String[] args) {
        System.out.println(isAnagram("anagram", "nagaram"));
        System.out.println(isAnagram("rat", "car"));

        System.out.println(isAnagram2("anagram", "nagaram"));
        System.out.println(isAnagram2("rat", "car"));

        System.out.println(isAnagram3("anagram", "nagaram"));
        System.out.println(isAnagram3("rat", "car"));
    }
}
