package br.com.alexduzi.java_concurrency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    }
}
