package br.com.alexduzi.palindrome_text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
        try (InputStream is = br.com.alexduzi.best_seller.Main.class.getResourceAsStream("/words.txt")) {
            if (is == null) {
                System.out.println("File not found in resources!");
                return;
            }
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (!line.trim().isEmpty()) {
                        System.out.println(String.format("Text: %s isPalindrome?: %s", line, isPalindrome2(line)));
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static boolean isPalindrome(String text) {
        text = text.trim().toLowerCase();
        for (int i = 0, j = text.length() - 1; i < text.length(); i++, j--) {
            if (text.charAt(i) != text.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    static boolean isPalindrome2(String text) {
        text = text.trim().toLowerCase();

        int start = 0;
        int end = text.length() - 1;

        while (start < end) {

            if (text.charAt(start) != text.charAt(end)) {
                return false;
            }

            start++;
            end--;
        }

        return true;
    }
}
