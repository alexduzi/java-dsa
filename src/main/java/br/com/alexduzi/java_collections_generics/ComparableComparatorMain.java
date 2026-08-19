package br.com.alexduzi.java_collections_generics;

import java.util.*;

public class ComparableComparatorMain {
    // Comparator - "Two out of three ain't bad"
    // "Comparator takes two" args but not the "To" method
    // int compare(T o1, T o2)
    // ORE Comparator and compare()

    // Comparable
    // if Comparator takes 2 then this takes 1 (natural ordering)
    // as Comparator does not have the "To" method, it must be here
    // int compareTo(T o)
    // LEO = Comparable and compareTo()
    public static void main(String[] args) {
        comparable();

        // comparable(new Dog[]{new Dog("Spot", 2), new Dog("Rover", 7)});// just-in-time array
        // comparable(Arrays.asList(new Dog("Spot", 2), new Dog("Rover", 7)));

        // comparator(new Dog[]{new Dog("Rover", 7), new Dog("Spot", 2)});
        comparator(Arrays.asList(new Dog("Rover", 7), new Dog("Spot", 2)));
    }

    public static void comparable(){
        List<Product> products = new ArrayList<>();
        products.add(new Product(99));
        products.add(new Product(9));
        products.add(new Product(19));
        Collections.sort(products);
        System.out.println(products);// [Product{id=9}, Product{id=19}, Product{id=99}]
    }

    public static void comparable(Dog[] dogArray){
        Arrays.sort(dogArray);
        System.out.println(Arrays.toString(dogArray));// [Dog{name=Rover, age=7}, Dog{name=Spot, age=2}]
    }

    public static void comparable(List<Dog> dogList){
        Collections.sort(dogList);
        System.out.println(dogList);// [Dog{name=Rover, age=7}, Dog{name=Spot, age=2}]
    }

    public static void comparator(Dog[] dogArray){
        // sorts ascending by age
        Comparator<Dog> byAge = Comparator.comparing(dog -> dog.getAge());
        Arrays.sort(dogArray, byAge);
        System.out.println(Arrays.toString(dogArray));// [Dog{name=Spot, age=2}, Dog{name=Rover, age=7}]

        Comparator<Dog> byAgeReversed = Comparator.comparing(Dog::getAge).reversed();
        Arrays.sort(dogArray, byAgeReversed);
        System.out.println(Arrays.toString(dogArray));// [Dog{name=Rover, age=7}, Dog{name=Spot, age=2}]
    }

    public static void comparator(List<Dog> dogList){
        // sorts ascending by age
        Comparator<Dog> byAge = Comparator.comparing(Dog::getAge);
        Collections.sort(dogList, byAge);
        System.out.println(dogList);// [Dog{name=Spot, age=2}, Dog{name=Rover, age=7}]

        Comparator<Dog> byAgeReversed = Comparator.comparing(Dog::getAge).reversed();
        Collections.sort(dogList, byAgeReversed);
        System.out.println(dogList);// [Dog{name=Rover, age=7}, Dog{name=Spot, age=2}]
    }

    public static void sortingMultipleFields() {
        List<Cat> cats = new ArrayList<>();
        cats.add(new Cat("Trixy", 5));
        cats.add(new Cat("Bella", 7));
        cats.add(new Cat("Bella", 2)); // second Bella
        Comparator<Cat> compCat = Comparator
                .comparing(Cat::getName)
                .thenComparingInt(Cat::getAge);
        Collections.sort(cats, compCat);
        System.out.println(cats);// [Cat{name=Bella, age=2}, Cat{name=Bella, age=7}, Cat{name=Trixy, age=5}]
    }

    public static void comparableIssue() {
        //        Set<Boss> bosses = new TreeSet<>();
//        bosses.add(new Boss(20));
//        bosses.add(new Boss(10));
//        bosses.add(new Boss(15));
//        System.out.println(bosses); // [Boss{id=10}, Boss{id=15}, Boss{id=20}]

//        Set<Worker> workers = new TreeSet<>();// ClassCastException: class Worker cannot be cast to Comparable
//        Set<Worker> workers = new TreeSet<>( (worker1, worker2) -> worker1.getId() - worker2.getId());
        Set<Worker> workers = new TreeSet<>( Comparator.comparing( worker -> worker.getId()));
//        Set<Worker> workers = new TreeSet<>( Comparator.comparing(Worker::getId));// unbound meth reference


        workers.add(new Worker(30));
        workers.add(new Worker(20));
        workers.add(new Worker(10));
        workers.add(new Worker(21));
        System.out.println(workers); // [Worker{id=10}, Worker{id=20}, Worker{id=21}, Worker{id=30}]
    }
}

class Product implements Comparable<Product>{
    private Integer id;

    public Product(Integer id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    @Override
    public String toString() {
        return "Product{" + "id=" + id + '}';
    }
    // equal objects should have the same hashcode
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Product){
            Product otherProduct = (Product)obj;
            if(Objects.equals(id, otherProduct.id)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int compareTo(Product product){ // specifies "natural ordering" for Product
        // delegate to Integer which implements Comparable<Integer>
        return id.compareTo(product.id);
//        return Integer.compare(id, product.id); // another option
//        return id-product.id;// sorts ascending by id
    }

}

class Dog implements Comparable<Dog> {
    private String name;
    private Integer age;
    public Dog() {}
    public Dog(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
    public Integer getAge() {
        return age;
    }
    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        return "Dog{" + "name=" + name + ", age=" + age + '}';
    }
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Dog){
            Dog otherDog = (Dog)obj;
            if(name.equals(otherDog.getName()))
                return true;
        }
        return false;
    }
    /* API:
        The natural ordering for a class C is said to be consistent with equals if and only if
        e1.compareTo(e2) == 0 has the same boolean value as e1.equals(e2) for every e1 and e2 of class C.
        It is strongly recommended (though not required) that natural orderings be consistent with equals.
        This is so because sorted sets (and sorted maps) without explicit comparators behave "strangely" when
        they are used with elements (or keys) whose natural ordering is inconsistent with equals. */
    @Override
    public int compareTo(Dog otherDog){ // specifies "natural ordering" for Dog
        // delegate to String which implements Comparable<String>
        return name.compareTo(otherDog.getName());// sorts alphabetically by name
    }
}

class Cat {
    private String name;
    private int age;
    public Cat() {}
    public Cat(String name, int age) {
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
}

class Worker{
    private int id;

    Worker(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Worker{" + "id=" + id + '}';
    }
}

class Boss implements Comparable<Boss>{
    private int id;

    Boss(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public int compareTo(Boss otherBoss){
        return this.id - otherBoss.getId();
    }

    @Override
    public String toString() {
        return "Boss{" + "id=" + id + '}';
    }

}