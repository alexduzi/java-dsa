package br.com.alexduzi.java_localisation;

import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        // Locale format: language_COUNTRY
        // COUNTRY is in capitals and is optional
        Locale locale  = Locale.getDefault();
        System.out.println(locale);

        // 1. Constructors
        System.out.println(new Locale("en"));                   // en
        Locale locEnglishGB = new Locale("en", "GB");           // en_GB
        System.out.println(locEnglishGB.getDisplayLanguage());  // English
        System.out.println(locEnglishGB.getDisplayCountry());   // United Kingdom

        // 2. Built-in constants
        System.out.println(Locale.FRENCH);                          // fr
        Locale locFrenchFrance = Locale.FRANCE;                     // fr_FR
        System.out.println(locFrenchFrance.getDisplayLanguage());   // French
        System.out.println(locFrenchFrance.getDisplayCountry());    // France

        // 3. Locale.Builder() pattern
        Locale locArabicEgypt = new Locale.Builder()
                .setLanguage("ar")  // language first
                .setRegion("EG")    // country second
                .build();
        System.out.println(locArabicEgypt);  // ar_EG
        Locale locArabicKuwait = new Locale.Builder()
                .setRegion("KW")    // country first
                .setLanguage("ar")  // language second
                .build();
        System.out.println(locArabicKuwait);  // ar_KW
    }
}
