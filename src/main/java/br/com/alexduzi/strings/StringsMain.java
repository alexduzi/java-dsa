package br.com.alexduzi.strings;

public class StringsMain {
    public static void main(String[] args) {
        String greeting = "Hello";
        System.out.println(greeting);

        String txt = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        System.out.println("The length of the txt string is: " + txt.length());

        txt = "Hello World";
        System.out.println(txt.toUpperCase()); // Outputs "HELLO WORLD"
        System.out.println(txt.toLowerCase()); // Outputs "hello world"

        txt = "Please locate where 'locate' occurs!";
        System.out.println(txt.indexOf("locate")); // Outputs 7

        txt = "Hello";
        System.out.println(txt.charAt(0)); // H
        System.out.println(txt.charAt(4)); // o

        String txt1 = "Hello";
        String txt2 = "Hello";

        String txt3 = "Greetings";
        String txt4 = "Gree tings";
        System.out.println(txt1.equals(txt2)); // true
        System.out.println(txt3.equals(txt4)); // false

        txt = "   Hello World   ";
        System.out.println("Before: [" + txt + "]");
        System.out.println("After:  [" + txt.trim() + "]");

        String firstName = "John";
        String lastName = "Doe";
        System.out.println(firstName + " " + lastName);
        System.out.println(firstName.concat(" ").concat(lastName));
        System.out.println(String.format("%s %s", firstName, lastName));
    }
}
