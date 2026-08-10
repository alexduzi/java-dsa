package br.com.alexduzi;

public class Main {
    public static void main(String[] args) {
        System.out.println(test("aaabaabaaaaabaaaabaaaa"));
    }

    static String test(String text) {
        char maxChar = text.charAt(0);
        int maxSum = 1;

        char curChar = text.charAt(0);
        int curLen = 1;

        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == curChar) {
                curLen++;
            } else {
                curChar = text.charAt(i);
                curLen = 1;
            }
            if (curLen > maxSum) {
                maxSum = curLen;
                maxChar = curChar;
            }
        }

        String result = "" + maxChar;

        return result.repeat(maxSum) + String.valueOf(maxSum);
    }

    static String test2(String text) {
        char maxChar = text.charAt(0);
        int maxLen = 1;

        char curChar = text.charAt(0);
        int curLen = 1;

        for (int i = 1; i < text.length(); i++) {
            if (text.charAt(i) == curChar) {
                curLen++;
            } else {
                curChar = text.charAt(i);
                curLen = 1;
            }
            if (curLen > maxLen) {
                maxLen = curLen;
                maxChar = curChar;
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < maxLen; i++) result.append(maxChar);
        result.append(maxLen);

        return result.toString();
    }
}