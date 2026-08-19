package br.com.alexduzi.java_collections_generics;

import java.util.ArrayList;
import java.util.List;

class Animal{}
class Dog2 extends Animal {}
class Terrier extends Dog2 {}
class Cat3 extends Animal {}
class Manx extends Cat3{}


public class GenericsMain {
    public static void addAnimal(Animal[] animals){
        animals[0] = new Dog2();
        animals[1] = new Cat3();// ArrayStoreException generated -  JVM knows the type
    }
    public static void addAnimal(List<Animal> animals){
        animals.add(new Dog2());
    }

    public static void main(String[] args) {
        // 1a. Polymorphic assignments.
        // Generics came in in Java 5. Type erasure required to support legacy code.
        // Thus, generics offer compile time protection.
        // Arrays have similar issues (polymorphic assignments) but the difference is
        // in how the compiler and JVM behave. Due to type erasure with generics,
        // the JVM does not know the types; with arrays the JVM does.

        // First, let's look at arrays
        Dog2[] dogs0 = {new Dog2(), new Dog2()};
        addAnimal(dogs0);

        // polyorphism ok for the base type; List -> ArrayList
        List<Cat3> cats1 = new ArrayList<Cat3>();
        // polyorphism not ok for the generic type; Animal -> Cat
        //       List<Animal> animals = new ArrayList<Cat>();
        List<Cat3> cats2  = new ArrayList<Cat3>(); // generic types on both sides must match
        List<Cat3> cats3 = new ArrayList<>();     // or use type inference
        // As the JVM does not know the types (stripped out during type erasure), the
        // compiler has to step in.
        addAnimal(cats2);

        // 1b. 'extends' - polymorphic assignments
        // Note: extends is read-only
        List<? extends Animal> animals1 = new ArrayList<Animal>();
        animals1.add(new Animal());// read-only
        List<? extends Animal> animals2 = new ArrayList<Dog2>();
        List<? extends Animal> animals4 = new ArrayList<Terrier>();
        List<? extends Animal> animals3 = new ArrayList<Cat3>();
        List<? extends Animal> animals5 = new ArrayList<Manx>();
        List<? extends Animal> animals6 = new ArrayList<Object>();

        // 1c. 'super' - polymorphic assignments
        List<? super Dog2> dogs1 = new ArrayList<Dog2>();
        dogs1.add(new Dog2()); // now, can add to the list
        List<? super Dog2> dogs2 = new ArrayList<Animal>();
        List<? super Dog2> dogs3 = new ArrayList<Object>();
        List<? super Dog2> dogs4 = new ArrayList<Terrier>();

        // 2. declarations for 'extends' and 'super' examples
        List<Object> objects   = new ArrayList<>(); objects.add(new Object());
        List<Animal> animals   = new ArrayList<>(); animals.add(new Animal());
        List<Cat3> cats         = new ArrayList<>(); cats.add(new Cat3());
        List<Manx> manxCats    = new ArrayList<>(); manxCats.add(new Manx());
        List<Dog2> dogs         = new ArrayList<>(); dogs.add(new Dog2());
        List<Terrier> terriers = new ArrayList<>(); terriers.add(new Terrier());

        // 3. extends
        //      ext(List<? extends Animal> list) => readonly
        ext(animals); // Animal is-an Animal        - OK
        ext(cats);    // Cat is-an Animal           - OK
        ext(manxCats);// Manx is-an Animal          - OK
        ext(dogs);    // Dog is-an Animal           - OK
        ext(terriers);// Terrier is-an Animal       - OK
        // ext(objects); // Object is-not an Animal    - not OK

        // 4. super
        //      spr(List<? super Cat> list) => modifiable
        spr(cats);    // Cat is Cat                     - OK
        spr(animals); // Animal is a supertype of Cat     - OK
        spr(objects); // Object is a supertype of Cat     - OK
//        spr(dogs);    // compiler error: Dog is not Cat or a supertype of Cat     - not OK
//        spr(terriers);// compiler error: Terrier is not Cat or a supertype of Cat - not OK
//        spr(manxCats);// compiler error: Manx is not Cat or a supertype of Cat    - not OK
    }

    public static void spr(List<? super Cat3> list){ // The "lower-bound" is Cat.
        // IN: Cat, Animal, Object.
        // The only objects that can safely be added are any type of Cat (including subtypes)
        // because the method could be getting in a list of Animals or Objects (or Cats).
        list.add(new Cat3());    // Cat is-a Cat (Cat is-an Animal, Cat is-an Object)
        list.add(new Manx());   // Manx is-a Cat (Manx is-an Animal, Manx is-an Object)

//        list.add(new Dog2());    // compiler error - Dog is not a Cat
//        list.add(new Animal()); // compiler error - Animal is not a Cat (Cat is an Animal)
//        list.add(new Object()); // compiler error - Object is not a Cat (Cat is an Object)

//        for(Cat3 cat:list){      // compiler errors when reading - 'list' passed in could be Animal's
//            System.out.println(cat);
//        }
//        for(Animal a:list){      // compiler errors when reading - 'list' passed in could be Object's
//            System.out.println(a);
//        }
        for(Object o:list){         // ok - the only thing we can safely say is that the 'list'
            System.out.println(o);  // coming in can all be treated as Object's
        }                           // Cat is-an Object, Animal is-an Object, Object is-an Object
    }
    public static void ext(List<? extends Animal> list){ // "upper-bound" is Animal
        // 'extends' implies read-only
        // IN: List<Animal>, List<Cat>, List<Manx>, List<Dog>, List<Terrier>
        // If 'extends' allowed us to add to 'list', then we could take in
        // a List of Cat's and add a Dog to it - thereby breaking type safety.
        list.add(new Cat3());      // compiler error if we try to modify 'list'
        list.add(new Dog2());      // compiler error if we try to modify 'list'
        list.add(new Animal());   // compiler error if we try to modify 'list'
        for(Dog2 dog:list){        // compiler errors reading - 'list' could be a list of Cat's
            System.out.println(dog);
        }
        // No compiler errors reading once we treat them as Animal; whether this method receives
        // in a list of Animal, Cat, Manx, Dog, or Terrier; they are *all* Animal.
        for(Animal animal:list){
            System.out.println(animal);
        }
    }
}
