package br.com.alexduzi.recursion._4_;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String[] list = {
                "azul", "verde", "preto", "rosa"
        };
        System.out.println(Arrays.toString(reverse(list)));
        System.out.println(Arrays.toString(reverse2(list)));
    }

    static String[] reverse(String[] list) {
        String[] newList = new String[list.length];
        for (int i = 0, j = list.length - 1; i < list.length; i++, j--) {
            newList[i] = list[j];
        }
        return newList;
    }

    static String[] reverse2(String[] list) {
        if (list.length <= 1) {
            return list;
        }

        String head = list[0];
        String[] tail = List.of(list).subList(1, list.length).toArray(String[]::new);

        // 1. Recursively reverse the tail first
        String[] reversedTail = reverse2(tail);

        String[] result = new String[list.length];

        // 2. Copy the reversed tail into the beginning of the result array
        System.arraycopy(reversedTail, 0, result, 0, reversedTail.length);

        // 3. Place the original head at the very end
        result[result.length - 1] = head;

        return result;
    }
}
