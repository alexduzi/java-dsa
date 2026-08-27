package br.com.alexduzi.reverse_string;

public class Main {
    public static void main(String[] args) {
        System.out.println(reverseString("apple"));
        System.out.println(reverseString("hello"));
        System.out.println(reverseString("Greetings!"));
        System.out.println();
        System.out.println(reverseString2("apple"));
        System.out.println(reverseString2("hello"));
        System.out.println(reverseString2("Greetings!"));
    }
    
    static String reverseString(String s) {
        char[] reverse = new char[s.length()];
        for (int i = 0, j = s.length() - 1; i < s.length(); i++, j-- ) {
            reverse[i] = s.charAt(j);
        }
        return new String(reverse);
    }
    
    static String reverseString2(String s) {
        return new StringBuilder(s).reverse().toString();
    }
}
