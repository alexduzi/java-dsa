package br.com.alexduzi.longest_common_prefix;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String[] words = new String[]{ "flower", "flow", "flight" };
        System.out.println(longestCommonPrefix(words));
    }

    static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }

        Arrays.sort(strs);

        String first = strs[0];
        String last = strs[strs.length - 1];
        String result = "";

        for (int i = 0; i < first.length(); i++) {
            if (i < last.length() && first.charAt(i) == last.charAt(i)) {
                result += first.charAt(i);
            } else {
                break;
            }
        }

        return result;
    }
}
