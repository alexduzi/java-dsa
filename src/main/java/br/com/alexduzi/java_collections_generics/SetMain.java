package br.com.alexduzi.java_collections_generics;

import java.util.*;

public class SetMain {
    public static void main(String[] args) {
        //factoryMethods();
        //treeSet();
        //hashSet();
        linkedHashSet();
    }

    public static void factoryMethods(){
        // unmodifiable sets returned
        Set<String> of     = Set.of("a", "b", "c"); // immutable
        Set<String> copy   = Set.copyOf(of); // immutable

        of.add("d");    // UnsupportedOperationException
        copy.add("d");  // UnsupportedOperationException

        of.remove("a");    // UnsupportedOperationException
    }

    public static void treeSet(){
        // SUU - Sets are Unique and Unordered
        Set<String> names = new TreeSet<>();
        names.add("John");
        names.add("John");
        names.add("Helen");
        names.add("Anne");
        // No duplicates, elements are sorted alphabetically
        System.out.println(names);// [Anne, Helen, John]

        Set<Integer> numbers = new TreeSet<>();
        numbers.add(23);
        numbers.add(Integer.valueOf("21"));
        numbers.add(Integer.valueOf("11"));
        numbers.add(99);
        // No duplicates, elements are sorted numerically
        System.out.println(numbers);// [11, 21, 23, 99]
    }

    public static void hashSet(){
        // HashSet
        Set<Contact> contactsHS = new HashSet<>();
        contactsHS.add(new Contact("zoe", 45));
        contactsHS.add(new Contact("zoe", 45)); // "zoe" only added once (Set)
        contactsHS.add(new Contact("alice",34));
        contactsHS.add(new Contact("andrew",35));
        contactsHS.add(new Contact("brian",36));
        contactsHS.add(new Contact("carol",37));
        /* Output:
            brian, 36
            andrew, 35
            carol, 37
            alice, 34
            zoe, 45
        */
        for(Contact contact : contactsHS){
            System.out.println(contact);
        }
        System.out.println();
    }

    public static void linkedHashSet(){
        // LinkedHashSet
        // API : This implementation differs from HashSet in that it maintains
        // a doubly-linked list running through all of its entries. This linked list
        // defines the iteration ordering, which is the order in which elements were
        // inserted into the set (insertion-order).
        // This implementation spares its clients from the unspecified, generally
        // chaotic ordering provided by HashSet, without incurring the increased cost
        // associated with TreeSet.
        Set<Contact> contactsLHS = new LinkedHashSet();
        contactsLHS.add(new Contact("zoe", 45));
        contactsLHS.add(new Contact("zoe", 45)); // "zoe" only added once (Set)
        contactsLHS.add(new Contact("alice",34));
        contactsLHS.add(new Contact("andrew",35));
        contactsLHS.add(new Contact("brian",36));
        contactsLHS.add(new Contact("carol",37));
        /*
            zoe, 45
            alice, 34
            andrew, 35
            brian, 36
            carol, 37
        */
        for(Contact contact:contactsLHS){
            System.out.println(contact);
        }
    }
}

class Contact {
    private int age;
    private String name;

    public Contact(String name, int age) {
        this.age  = age;
        this.name = name;
    }
    @Override
    public int hashCode() {         // both instance variables 'age' and 'name' are used
        int hash = 7;
        hash = 89 * hash + this.age;
        hash = 89 * hash + this.name.length(); // a weak algorithm - demo purposes
        // Objects.hash(this.age, this.name);
        return hash;
    }
    @Override
    public boolean equals(Object obj) { // same instance variables used as in equals()!
        if (obj instanceof Contact) {
            Contact otherContact = (Contact) obj;
            return this.name.equals(otherContact.name) && this.age == otherContact.age;
        }
        return false;
    }
    @Override
    public String toString(){
        return name + ", " + age;
    }
}