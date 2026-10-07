package br.com.alexduzi.java_calculate_age;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CalculateAge {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter your birth date: ");

            String birthDateString = sc.next();
            LocalDate birthDate = LocalDate.parse(birthDateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            LocalDate currentDate = LocalDate.now();

            Period age = Period.between(birthDate, currentDate);

            System.out.println("You are " + age.getYears() + " years old");
        }
    }
}
