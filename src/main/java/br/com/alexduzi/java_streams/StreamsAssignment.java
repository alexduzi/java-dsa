package br.com.alexduzi.java_streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Item{
    private int id;
    private String name;
    public Item(int id, String name){
        this.id = id;
        this.name = name;
    }
    public Integer getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String toString(){    // outputs the name
        return name;
    }
}

class Person{
    private String firstName, lastName;
    private Integer age;

    Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{" + "firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + '}';
    }
}

class Book{
    private String title;
    private double price;
    Book(String title, double price){
        this.title = title;
        this.price = price;
    }
    public String getTitle() {   return title;}
    public double getPrice() {   return price;}
    public String toString() { return title+ " " + price;}
}

class AnotherBook {
    private String title;
    private String genre;
    public AnotherBook(String title, String genre){
        this.title = title;
        this.genre = genre;
    }     //accessors not shown
    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }
}

public class StreamsAssignment {
    public static void main(String[] args) {
        ex1();
        ex2();
        ex3();
        ex4();
        ex5();
        ex6();
        ex7();
        ex8();
        ex9();
        ex10();
        ex11();
    }

//    Stream a list of int primitives between the range of 0 (inclusive) and 5 (exclusive).
//    Calculate and output the average.
    static void ex1() {
        IntSummaryStatistics stats = IntStream.range(0, 5).summaryStatistics();
        System.out.println(stats.getAverage());
    }

//    Given the Item class (in the zip file), declare a List typed for Item with the following Item’s:
//    a. id=1 name=”Screw”
//    b. id=2 name=”Nail”
//    c. id=3 name=”Bolt”
//    Stream the list and sort it so that it outputs “BoltNailScrew” i.e. alphabetic name order. Use Stream’s
//    forEach method to output the names (use the method reference version for the required Consumer lambda).
    static void ex2() {
        List<Item> list = Arrays.asList(new Item(1, "Screw"),
                                            new Item(2, "Nail"),
                                            new Item(3, "Bolt"));

        list.stream()
                .map(Item::getName)
                .forEach(System.out::print);
        System.out.println();
    }

//    Generate a Stream<List<String>> using the Stream.of(Arrays.asList(“a”, “b”), Arrays.asList(“a”,
//    “c”)) method call. Filter the stream so that only list’s that contain “c” make it through the filter.
//    Flatten the Stream<List<String>> to a Stream<String> using the flatMap() operation. Note that
//    flapMap() states in the API “Each mapped stream is closed after its contents have been placed into
//    this [new] stream.”. Use forEach() to output the new stream.
    static void ex3() {
        Stream.of(Arrays.asList("a", "b"),
                  Arrays.asList("a", "c"),
                  Arrays.asList("a", "c"))
                .filter(s -> s.contains("c"))
                .flatMap(Collection::stream)
                .forEach(str -> System.out.print(str + " "));
        System.out.println();
    }

    static void ex4() {
//     Using 1, 2 and 3 create a List of Integers.
//       i. Stream the list and calculate the sum, using the sum() method from IntStream.
//       ii. Stream the list again and calculate the maximum value, using the max() method from IntStream.
        List<Integer> list = List.of(1 ,2 ,3);
        System.out.println(list.stream().mapToInt(Integer::intValue).sum());
        list.stream().mapToInt(Integer::intValue).max().ifPresent(System.out::println);

//      Given the Person class (in the zip file), declare a List typed for Person with the following
//      Person’s:
//        i. “Alan”, “Burke”, 22
//        ii. “Zoe”, “Peters”, 20
//        iii. “Peter”, “Castle”, 29
        List<Person> persons = List.of(new Person("Alan", "Burke", 22),
                                       new Person("Zoe", "Peters", 20),
                                       new Person("Peter", "Castle", 29));
        persons.stream()
                .max(Comparator.comparing(Person::getAge))
                .ifPresent(System.out::println);

//      Using 10, 47, 33 and 23 create a List of Integers. Stream the list and using the following
//      versions of reduce(), calculate the maximum value:
//        i. Optional<T> reduce(BinaryOperator<T> accumulator)
//        ii. T reduce(T identity, BinaryOperator<T> accumulator)
        List<Integer> numbers = List.of(10, 47, 33, 23);
        // numbers.reduce((acc, n) -> acc + n);
        numbers.stream().reduce(Integer::max).ifPresent(System.out::println);
        System.out.println(numbers.stream().reduce(Integer.MIN_VALUE, Integer::max));
    }

    static void ex5() {
        // Code a method public static Optional<String> getGrade(int marks)
//          a. in the method getGrade:
//            i. declare an empty optional, typed for String called grade
//            ii. insert the following code:
//            if (marks > 50) {grade = Optional.of(“PASS”);} else {grade.of(“FAIL”);}

//          b. in main():
//            i. declare an Optional, typed for String named grade1 which is initialised to the return
//                    value of calling getGrade(50)
//            ii. declare an Optional, typed for String named grade2 which is initialised to the return
//                    value of calling getGrade(55)
//            iii. using orElse() on grade1, output the value of grade1 or “UNKNOWN”
//            iv. if(grade2.isPresent()) is true: use ifPresent(Consumer) to output the contents of
//            grade2; if false, use orElse() to output the contents of grade2 or “Empty”
//            v. Notes:
//            1. Optional’s are immutable.
//            2. Optional.of(null); // NullPointerException
//            3. Optional.ofNullable(null); // Optional.empty returned
        Optional<String> grade1 = getGrade(50); // returns an empty Optional
        Optional<String> grade2 = getGrade(55); // returns "PASS" Optional
        System.out.println(grade1.orElse("UNKNOWN"));
        if (grade1.isPresent()) {
            grade2.ifPresent(System.out::println);
        } else {
            System.out.println(grade2.orElse("Empty"));
        }
    }

    static Optional<String> getGrade(int marks) {
        Optional<String> grade = Optional.empty();
        if (marks > 50) {
            grade = Optional.of("PASS");
        } else {
            grade.of("FAIL"); // Optionals are immutable!
        }
        return grade;
    }

    public static void ex6() {
//        Given the Book class (in the zip file), declare a List typed for Book with the following Book’s:
//          a. title=”Thinking in Java”, price=30.0
//          b. title=”Java in 24 hrs”, price=20.0
//          c. title=”Java Recipes”, price=10.0
//        Stream the books and calculate the average price of the books whose price is > 10.
//        Change the filter to books whose price is > 90. Ensure you do not get an exception.
        List<Book> books = List.of(new Book("Thinking in Java", 30.0),
                                   new Book("Java in 24 hrs", 20.0),
                                   new Book("Java Recipes", 10.0));

        double avg1 = books.stream()
                .filter(b -> b.getPrice() > 10)
                .mapToDouble(b -> b.getPrice())
                .average()
                .orElse(0.0);
        System.out.println(avg1);

        double avg2 = books.stream()
                .filter(b -> b.getPrice() > 90)
                .mapToDouble(b -> b.getPrice())
                .average()
                .orElse(0.0);
        System.out.println(avg2);
    }

    static void ex7() {
//        Given the Book class (in the zip file), declare a List typed for Book with the following Book’s:
//          a. title=”Atlas Shrugged”, price=10.0
//          b. title=”Freedom at Midnight”, price=5.0
//          c. title=”Gone with the wind”, price=5.0
//          Stream the books and instantiate a Map named ‘bookMap’ that maps the book title to its price. To do
//            this use the collect(Collectors.toMap(Function fnToGetKey, Function fnToGetValue)). Iterate
//          through ‘bookMap’ (using the Map forEach(BiConsumer) method). The BiConsumer only outputs
//          prices where the title begins with “A”.
        List<Book> books = List.of(new Book("Atlas Shrugged", 10.0),
                new Book("Freedom at Midnight", 5.0),
                new Book("Gone with the wind", 5.0));

        Map<String, Double> bookMap = books
                .stream()
                .collect(Collectors.toMap(Book::getTitle, Book::getPrice));

        bookMap.forEach((key, value) -> {
            if (key.startsWith("A")) {
                System.out.printf("Book: %s prince: %.2f", key, value);
            }
        });
    }

    static void ex8() {
//     Given the Book class (in the zip file), declare a List typed for Book with the following Book’s:
//        a. title=”Gone with the wind”, price=5.0
//        b. title=”Gone with the wind”, price=10.0
//        c. title=”Atlas shrugged”, price=15.0
//          In a pipeline which has no return type:
//                stream the books
//                using the collect() method, generate a Map that maps the book title to its price
//                using forEach(), output the title and price of each entry in the map
//                        What happened and why? Fix this by using the Collectors.toMap(Function, Function,
//                                BinaryOperator) method.
        List<Book> books = List.of(new Book("Gone with the wind", 5.0),
                new Book("Gone with the wind", 10.0),
                new Book("Atlas shrugged", 15.0));

        books.stream()
            //.collect(Collectors.toMap(Book::getTitle, Book::getPrice)) //  IllegalStateException
            .collect(Collectors.toMap(Book::getTitle, Book::getPrice, (b1, b2) -> b1*b2)) // merge function version
            .forEach((a, b)->System.out.println(a+" "+b));
    }

    static void ex9() {
//      Given the Person class (in the zip file), declare a List typed for Person with the following Person’s:
//            a. name=”Bob”, age=31
//            b. name=”Paul”, age=32
//            c. name=”John”, age=33
//       Pipeline the following where the return type is double:
//       stream the people
//       filter the stream for Person’s whose age is < 30 map to int primitives
//       calculate the average age.
//       This should generate a NoSuchElementException. Using orElse(), fix the pipeline (not the filter) so
//       that 0.0 is returned instead of an exception being generated.
        List<Person> persons = List.of(new Person("Bob", "Burke", 31),
                new Person("Paul", "Peters", 32),
                new Person("John", "Castle", 33));
        double avg = persons.stream()
                .filter(p -> p.getAge() < 30)
                .mapToInt(Person::getAge)
                .average()
                .orElse(0.0);
        System.out.println(avg);
    }

    static void ex10() {
//     Declare an Optional, typed for Double, named ‘price’ using the Optional.ofNullable(20.0).
//     Output the Optional value for ‘price’ 3 times: using ifPresent(Consumer), orElse(T) and
//        orElseGet(Supplier).
        Optional<Double> price = Optional.ofNullable(20.0);
        price.ifPresent(System.out::println);
        System.out.println(price.orElse(0.0));
        System.out.println(price.orElseGet(() -> 0.0));

//        declare a new Optional, typed for Double, named ‘price2’ (or comment out (a) and re-use ‘price’).
//        Use Optional.ofNullable again but this time, pass in null.
//                i. Output ‘price2’ in a normal System.out.println().
//                ii. check to see if price2 isEmpty() and if so output “empty”.
//        iii. do (ii) again except this time use the more functional “ifPresent(Consumer)” method.
//                iv. initialise a Double x to the return of “price2.orElse(44.0)”. Output and observe the
//        value of x.
        Optional<Double> price2 = Optional.ofNullable(null);
        System.out.println(price2);
        if (price2.isEmpty()) {
            System.out.println("empty");
        }
        price2.ifPresent(System.out::println);
        Double x = price2.orElse(44.0);
        System.out.println(x);

//        declare a new Optional, typed for Double, named ‘price3’ (or comment out (b) and re-use
//        ‘price’). Use Optional.ofNullable passing in null.
//                i. initialise a Double z to the return of “price3.orElseThrow(() -> new
//                RuntimeException(“Bad Code”). Output and observe the value of z.

        // uncomment the code below to see the output.

        // Optional<Double> price3 = Optional.ofNullable(null);
        // Double z = price3.orElseThrow(() -> new RuntimeException("Bad Code")); // Exception in thread "main" java.lang.RuntimeException: Bad Code
        // System.out.println(z);
    }

    static void ex11() {
//        Given the AnotherBook class (in the zip file), declare a List typed for AnotherBook namely ‘books’
//        with the following AnotherBook’s:
//        a. title=”Gone with the wind”, genre=”Fiction”
//        b. title=”Bourne Ultimatum”, genre=”Thriller”
//        c. title=”The Client”, genre=”Thriller”
//        Declare the following: List<String> genreList = new ArrayList<>();
//        Stream books so that genreList refers to a List containing the genres of the books in the books List.
        List<AnotherBook> books = List.of(new AnotherBook("Gone with the wind", "Fiction"),
                                    new AnotherBook("Bourne Ultimatum", "Thriller"),
                                    new AnotherBook("The Client", "Thriller"));
        List<String> genreList = new ArrayList<>();
        books.stream()
                .map(AnotherBook::getGenre)
                .forEach(genreList::add);
        System.out.println(genreList);
    }
}
