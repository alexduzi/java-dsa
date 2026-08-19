package br.com.alexduzi.java_collections_generics;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BinarySearchMain {
    public static void main(String[] args) {
        //searchStrings();
        searchCats();
    }

    public static void searchStrings(){
        List<String> names = Arrays.asList("John", "Martin", "Paula", "Ann");
        Collections.sort(names);    // natural order sort - alphabetic for Strings
        System.out.println(names);  // [Ann, John, Martin, Paula]
        // if found, return index
        System.out.println(Collections.binarySearch(names, "John")); // 1
        // if not found, return: -(indexItWouldHaveIfPresent) -1
        // "Laura" this is: -(2) -1 = -2 -1 = -3
        System.out.println(Collections.binarySearch(names, "Laura"));// -3
    }

    public static void searchCats(){
        Cat2 fluffy = new Cat2("Fluffy", 1);
        Cat2 bella  = new Cat2("Bella", 5);
        List<Cat2> catList = Arrays.asList(fluffy, bella);

        Collections.sort(catList);      // must sort first by natural order - name ascending
        System.out.println(catList);    // [Cat{name=Bella, age=5}, Cat{name=Fluffy, age=1}]
        // API: Searches the specified list for the specified object using the binary search algorithm.
        //      The list must be sorted into ascending order according to the natural ordering of its elements
        //      (as by the sort(List) method) prior to making this call. If it is not sorted, the results are undefined.
        //      If the list contains multiple elements equal to the specified object, there is no guarantee which one
        //      will be found.
        System.out.println("Bella is at index: "+Collections.binarySearch(catList, bella)); // 0

        // set up the Comparator<T>
        //    int compare(T o1, T o2)
        Comparator<Cat2> byAge = (cat1, cat2) -> cat1.getAge() - cat2.getAge();
        Collections.sort(catList, byAge); // sort by age ascending
        System.out.println(catList);      // [Cat{name=Fluffy, age=1}, Cat{name=Bella, age=5}]
        // API: Searches the specified list for the specified object using the binary search algorithm.
        //      The list must be sorted into ascending order according to the specified comparator
        //      (as by the sort(List, Comparator) method), prior to making this call. If it is not sorted,
        //      the results are undefined. If the list contains multiple elements equal to the specified object,
        //      there is no guarantee which one will be found.
        System.out.println("Bella is at index: "+Collections.binarySearch(catList, bella, byAge)); // 1
    }
}

class Cat2 implements Comparable<Cat2>{
    private String name;
    private int age;

    public Cat2(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    @Override
    public String toString() {
        return "Cat{" + "name=" + name + ", age=" + age + '}';
    }
    @Override
    public boolean equals(Object obj){ // consistency with compareTo
        if(obj instanceof Cat2){
            Cat2 otherCat = (Cat2)obj;
            if(name.equals(otherCat.getName()))
                return true;
        }
        return false;
    }
    @Override
    public int compareTo(Cat2 otherCat){ // natural sort order is by name
        return name.compareTo(otherCat.getName());
    }
}
