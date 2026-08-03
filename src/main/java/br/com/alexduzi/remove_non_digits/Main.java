package br.com.alexduzi.remove_non_digits;

import java.util.Arrays;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        System.out.println(removeNonDigits("836.420.580-35"));
        System.out.println(removeNonDigits2("836.420.580-35"));
        System.out.println(removeNonDigits3("836.420.580-35"));
    }

    static String removeNonDigits(String text) {
        char[] newText = new char[text.length()];

        int i = 0, j = 0;
        while (i < text.length()) {
            if (Character.isDigit(text.charAt(i))) {
                newText[j] = text.charAt(i);
                j++;
            }
            i++;
        }

        return new String(Arrays.copyOf(newText,j));
    }

    static String removeNonDigits2(String text) {
        char[] newText = new char[text.length()];

        int i = 0, j = 0;
        while (i < text.length()) {
            if (text.charAt(i) >= '0' && text.charAt(i) <= '9') {
                newText[j] = text.charAt(i);
                j++;
            }
            i++;
        }

        return new String(Arrays.copyOf(newText, j));
    }

    static String removeNonDigits3(String text) {
        // return text.replaceAll("[^0-9]", "");
        return text.replaceAll("\\D", "");
    }
}
