package br.com.alexduzi.java_concurrency;

import java.util.*;

public class SynchronizedCollection {
    // Usefull if you are given an existing non-concurrent collection
    // and you want to share it among several threads

    // However, if you know when you are creating your collection that you require
    // concurrency your collection that you requre concurrency across threads, use
    // the concurrenct collections outlined in the overview (better performance)

    // Note: synchronized collections also throw ConcurrentModificationException if you try to modify
    // them inside a loop (unlike concurrenct collections)
    public static void main(String[] args) {
        List<String> dogTypes = new ArrayList<>();
        dogTypes.add("German Shepherd");
        dogTypes.add("Labrador");
        List<String> dogTypesSyn = Collections.synchronizedList(dogTypes);

        // safe to use dogTypesSyn with multiple threads...

        Map<String, String> capitalCities = new HashMap<>();
        // Map<String, String> capitalCities = new ConcurrentHashMap<>(); // fixes the issue also

        capitalCities.put("Oslo", "Norway");
        capitalCities.put("Copenhagen", "Denmark");

        Map<String, String> syncCapitalCities = Collections.synchronizedMap(capitalCities);
        for (String key : syncCapitalCities.keySet()) {
            System.out.println(key + " is the capital of " + syncCapitalCities.get(key));
            // syncCapitalCities.remove(key); // throws ConcurrentModificationException
        }
    }
}
