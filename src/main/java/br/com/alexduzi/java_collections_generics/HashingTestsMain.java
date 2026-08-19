package br.com.alexduzi.java_collections_generics;

import java.util.HashMap;
import java.util.Map;

class Contact2 {
    private int age;
    private String name;

    Contact2(String name, int age) {
        this.age  = age;
        this.name = name;
    }
    @Override
    public int hashCode() {         // both instance variables 'age' and 'name' are used
        int hash = 7;
        hash = 89 * hash + this.age;
        hash = 89 * hash + this.name.length(); // a weak algorithm - demo purposes
        return hash;
    }
    @Override
    public boolean equals(Object obj) { // same instance variables used as in equals()!
        if (obj instanceof Contact2) {
            Contact2 otherContact = (Contact2) obj;
            return this.name.equals(otherContact.name) && this.age == otherContact.age;
        }
        return false;
    }
    @Override
    public String toString(){
        return name + ", " + age;
    }
}

public class HashingTestsMain {
    private static Map<Contact2, String> map = new HashMap<>();
    public static void main(String args[]) {
        Contact2 john  = new Contact2("john", 33);
        Contact2 peter = new Contact2("peter",34);
        System.out.println("john.hashCode() is " + john.hashCode());  // 58388
        System.out.println("peter.hashCode() is " + peter.hashCode());// 58478
        map.put(john, "Irish");
        map.put(peter, "American");
        System.out.println(map.get(john));  // Irish
        System.out.println(map.get(peter)); // American

        Contact2 mary = new Contact2("mary", 21);
        System.out.println("mary.hashCode() is " + mary.hashCode());// 57320
        map.put(mary, "engineer");
        otherScope();
        System.out.println("After otherScope(): "+map.get(mary)); // accountant

        testJane();
        // HashMaps do not maintain order
        System.out.println(map);//{john, 33=Irish, mary, 21=accountant, jane, 21=nurse, peter, 34=American}
    }
    public static void otherScope(){
        Contact2 anotherMary = new Contact2("mary", 21);// reconstruct "anotherMary"
        System.out.println("anotherMary.hashCode() is " + anotherMary.hashCode());// 57320
        // Separate object used as a key to access the Map!
        System.out.println("In otherScope(): "+map.get(anotherMary));  // engineer

        // the next line overwrites mary with anotherMary as a key because:
        //   a) their hashcodes are the same
        //   b) they are both equal according to equals()
        map.put(anotherMary, "accountant");
    }
    public static void testJane(){
        // "jane" will result in the same hashcode as "mary" or "anotherMary"
        // because their ages are the same and "mary" and "jane" each have
        // 4 letters. Thus, the hashing algorithm will find the same bucket.
        // The equals() method now however finds a different key because we are
        // searching based on "jane" within the bucket and not for "mary" as above.
        // As a result, when we do the "get", we get "nurse" and not "accountant".
        Contact2 jane = new Contact2("jane", 21);
        System.out.println("jane.hashCode() is " + jane.hashCode());// 57320
        map.put(jane, "nurse");
        System.out.println(map.get(jane)); // nurse
    }
}
