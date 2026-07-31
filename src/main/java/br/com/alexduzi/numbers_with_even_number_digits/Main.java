package br.com.alexduzi.numbers_with_even_number_digits;

public class Main {
    // Dado um array nums de inteiros, retorne quantos deles contêm um número par de dígitos.
    // entrada: nums = [12,345,2,6,7896]
    // saída: 2
    // Explicação:
    // 12 contém 2 dígitos (número par de dígitos).
    // 345 contém 3 dígitos (número ímpar de dígitos).
    // 2 contém 1 dígito (número ímpar de dígitos).
    // 6 contém 1 dígito (número ímpar de dígitos).
    // 7896 contém 4 dígitos (número par de dígitos).
    // Portanto, apenas 12 e 7896 contêm um número par de dígitos.
    // entrada: nums = [555,901,482,1771]
    // saída: 1
    public static void main(String[] args) {
        System.out.println(findNumbers(new int[] { 12, 345, 2, 6, 7896 }));
        System.out.println(findNumbers(new int[] { 555, 901, 482, 1771 }));

        System.out.println(findNumbers2(new int[] { 12, 345, 2, 6, 7896 }));
        System.out.println(findNumbers2(new int[] { 555, 901, 482, 1771 }));
    }

    static int findNumbers(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            if (String.valueOf(nums[i]).length() % 2 == 0) { // método mais prático é converter o número em string
                result++;
            }
        }
        return result;
    }

    static int findNumbers2(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            if (countDigits(nums[i]) % 2 == 0) {
                result++;
            }
        }
        return result;
    }

    static int countDigits(int num) {
        int result = 0;
        while (num > 0) {
            num /= 10; // outro metodo é contar os números pela divisão sucessiva por 10
            result++;
        }
        return result;
    }
}
