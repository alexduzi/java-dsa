package br.com.alexduzi.reverse_integer;

public class Main {
    public static void main(String[] args) {
        System.out.println(reverseInteger(178));
        
        
        System.out.println(reverseInteger2(-15));
    }
    
    static int reverseInteger(int number) {
        int result = 0;
        while (number > 0) {
            result = (result * 10) + (number % 10); // remove and add
            number = number / 10; // remove number
        }
        return result;
    }
    
    static int reverseInteger2(int number) {
        boolean isNegative = number < 0;
        long absoluteNumber = Math.abs((long)number);
        
        String text = String.valueOf(absoluteNumber);
        String reversedText = new StringBuilder(text).reverse().toString();
        
        long reversedResult = Long.parseLong(reversedText);
        
        if (isNegative) {
            reversedResult = -reversedResult;
        }
        
        // check boundaries
        if (reversedResult < Integer.MIN_VALUE || reversedResult > Integer.MAX_VALUE) {
            return 0;
        }
        
        return (int)reversedResult;
    }
}
