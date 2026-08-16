package br.com.alexduzi.valid_parentheses;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        // System.out.println(isValid("((({{{}}}[])))"));
        System.out.println(isValid2("()[)]({}))"));
    }

    static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (Character symbol : s.toCharArray()) {

            if (symbol.equals('(') || symbol.equals('[') || symbol.equals('{')) {
                stack.push(symbol);
            } else {
                if (stack.empty()) {
                    return false;
                }
                Character top = stack.peek();

                if (symbol.equals(')') && top.equals('(') ||
                        symbol.equals(']') && top.equals('[') ||
                        symbol.equals('}') && top.equals('{')) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }

        return stack.empty();
    }

    static boolean isValid2(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        for (Character symbol : s.toCharArray()) {
            if (map.containsKey(symbol)) {
                Character top = stack.peek();
                if (!stack.isEmpty() && top.equals(map.get(symbol)) ) {
                    stack.pop();
                } else {
                    return false;
                }
            } else {
                stack.push(symbol);
            }
        }
        return stack.isEmpty();
    }
}
