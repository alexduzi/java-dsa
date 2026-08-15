package br.com.alexduzi.java_streams;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        // streamFilterExample();
        streamLazyExample();
    }

    static void streamFilterExample() {
        List<Double> temps = Arrays.asList(98.4, 100.2, 87.9, 102.8);

        System.out.println("Number of temps > 100 is: " +
                temps.stream()
                        .peek(System.out::println)
                        .filter(temp -> temp > 100)
                        .peek(System.out::println)
                        .count());
    }

    static void streamLazyExample() {
        /*
        Each element moves along the chain vertically:
        filter: Alex
        forEach: Alex
        filter: David
        forEach: David
        filter: April
        forEach: April
        filter: Edward
        forEach: Edward
         */
        Stream.of("Alex", "David", "April", "Edward")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return true;
                })
                .forEach(s -> System.out.println("forEach: " + s));
    }

    static void workingWithStream() {
        List<String> animalList = Arrays.asList("cat", "dog", "sheep");
        // using stream() which is a default method in Collection interface
        Stream<String> streamAnimals = animalList.stream();
        System.out.println("Number of elements: " + streamAnimals.count());
    }

    static void workingWithStream2() {
        // stram() is a default method in the Collection interface and therefore
        // is inherited by all classes that implement Collection. Map is NOT one
        // of those i.e Map is not a Collection. To bridge between the two, we
        // use the Map method entrySet() to return a Set view of the Map
        // Set IS-A Collection
        Map<String, Integer> namesToAges = new HashMap<>();
        namesToAges.put("Mike", 22); namesToAges.put("Mary", 24); namesToAges.put("Alice", 31);
        System.out.println("Number of entries: " +
                namesToAges
                        .entrySet() // get a Set (i.e Collection) view of the Map
                        .stream() // stram() is a default method in collection
                        .count()); // 3
    }

    static void infiniteStreams() {
        // infinite stream if random unordered numbers
        // between 0..9 inclusive
        // Stream<T> generate(Suppiler<T> s)
        //  Supplier is a functional interface:
        //   T get()
        Stream<Integer> infStream = Stream.generate(() -> {
            return (int)(Math.random() * 10);
        });
        // keeps going until i kill it
        infStream.forEach(System.out::println);
    }

    static void infiniteStreams2() {
        // infinit stream of ordered numbers
        // 2, 4, 6, 8, 10, 12, etc
        // iterate(T seed, UnaryOperator<T> fn)
        //   UnaryOperator is-a Function<T, T>
        //    T apply(T t)
        Stream<Integer> infStream = Stream.iterate(2, n -> n + 2);
        // keeps going until i kill it
        infStream.forEach(System.out::println);
    }

    static void infiniteStreams3() {
        // finite stream of ordered numbers
        // 2, 4, 6, 8, 10, 12, 14, 16, 18, 20
        Stream.iterate(2, n -> n + 2)
                .limit(10) // limit() is a short-circuiting statefull intermediate operation
                .forEach(System.out::println); // forEach(Consumer) is a terminal operation
    }

    static void terminalOperations() {
        long count = Stream.of("dog", "cat").count();
        System.out.println(count); // 2

        // Optional<T> min(Comparator)
        // Optional<T> max(Comparator)
        // Optional introduce in Java 8 to replace 'null'. If the stream is empty
        // then the Optional will be empty (and we won't have to deal with null
        Optional<String> min = Stream.of("deer", "horse", "pig")
                .min((s1, s2) -> s1.length()-s2.length());
        min.ifPresent(System.out::println);

        Optional<Integer> max = Stream.of(4, 6, 2, 12, 9).max((i1, i2) -> i1-i2);
        max.ifPresent(System.out::println);
    }

    static void terminalOperations2() {
        // Optional<T> findAny()
        // Optional<T> findFirst()
        // These are terminal operations but not reductions
        // as they sometimes return without processing all
        // the elements in the stream. Reductions reduce the
        // entire stream into one value
        Optional<String> any = Stream.of("John", "Paul").findAny();
        any.ifPresent(System.out::println); // John (usually)

        Optional<String> first = Stream.of("John", "Paul").findFirst();
        first.ifPresent(System.out::println); // John

        // boolean anyMatches(Predicate)
        // boolean allMatch(Predicate)
        // boolean noneMatch(Predicate)
        List<String> names = Arrays.asList("Alan", "Brian", "Colin");
        Predicate<String> pred = name -> name.startsWith("A");
        System.out.println(names.stream().anyMatch(pred)); // true (one does)
        System.out.println(names.stream().allMatch(pred)); // false (two don't)
        System.out.println(names.stream().noneMatch(pred)); // false (one does)

        // void forEach (Consumer)
        // As there is no return value, forEach() is not reduction
        // AS the return type is 'void', if you want something to
        // happen, it has to happen inside the Consumer (side-effect)
        Stream<String> names2 = Stream.of("Cathy", "Pauline", "Zoe");
        names2.forEach(System.out::println);

        // Notes: forEach is also a method in the Collection interface.
        // Streams cannot be the source of a for-each loop
        // because streams do not implement the Iterable interface
        // Stream<Integer> s = Stream.of(1);
        // for (Integer i : s) {} // error: required array of Iterable
    }

    static void terminalOperations3() {
        // The reduce() method combines a stream into a single object.
        // it is a reduction, which means it processes all elements
        // The most common way of doing a reduction is to start with
        // an initial value and keep merging it with the next value.

        // T reduce(T identity, BinaryOperator<T> accumulator)
        // BinaryOperator<T> functional method:
        // T apply(T t);
        // The "identity" is the initial value of the reduction and also
        // what is returned if the stream is empty. This means that there
        // will always be a result and thus Optional is not the return type
        // (on this version of reduce())
        // The "accumulator" combines the current result with the
        // current value in the stream
        String name = Stream.of("s", "e", "a", "n")
                // .filter(s -> s.length() > 2)
                // .reduce("nothing", (s, c) -> s + c)
                .reduce("", (s, c) -> s + c);
        System.out.println(name);

        Integer product = Stream.of(2, 3, 4)
                .reduce(1, (a, b) -> a * b);
        System.out.println(product);
    }

    static void terminalOperations4() {
        // Optional<T> reduce(BinaryOperator<T> accumulator)
        // When you leave out the identity, an Optional is
        // returned because there may not be any data (all the
        // elements could habe been filtered out earlier). There are
        // 3 possible results:
        // a) empty stream =? empty Optional returned
        // b) one element in stream => that element is returned
        // c) multiple elements in stream => accumulator is applied
        BinaryOperator<Integer> op = (a,b) -> a+b;
        Stream<Integer> empty = Stream.empty();
        Stream<Integer> oneElement = Stream.of(6);
        Stream<Integer> multipleElements = Stream.of(3, 4, 5);
        empty.reduce(op).ifPresent(System.out::println);
        oneElement.reduce(op).ifPresent(System.out::println);
        multipleElements.reduce(op).ifPresent(System.out::println);
        // Why not just require the identity and remove this method?
        // Sometimes it is nice to know if the stream is empty as opposed
        // to the case where there is a value returned from the accumulator
        // that happens to match the identity (however unlikely).
        Integer val = Stream.of(1, 1, 1)
                //.filter(n -> n > 5) // val is 1 this way
                .reduce(1, (a, b) -> a); // val is 1 this way too
        System.out.println(val);
    }

    static void terminalOperations5() {
        // <U> U reduce (U identity, Bifunction accumulator, BinaryOperator combiner)
        // We use this version when we are dealing with different types
        // allowing us to create intermediate reductions and then combine
        // them at the end. This is useful when working with parallel
        // streams - the streams can be decomposed and reassembled by separate threads.
        // For example, if we wanted to count the length
        // of four 1000-character strings, the first 2 values and the last
        // two values could be calculated independently. The intermediate
        // results (2000) would then be combined into a final value (4000)
        // Example: we want to count the number of characters in each String
        Stream<String> stream = Stream.of("car", "bus", "train", "aeroplane");
        int length = stream.reduce(0, (n, str) -> n + str.length(), (n1, n2) -> n1 + n2);
        System.out.println(length);
    }

    static void terminalOperations6() {
        // StringBuilder collect(Supplier<StringBuilder> supplier,
        //                       BiConsumer<StringBuilder, String> accumulator,
        //                       BiConsumer<StringBuilder, StringBuilder> combiner)
        // This version is used when you want complete control over
        // how collecting should work. The accumulator adds an element
        // to the collection e.g the next String to the StringBuilder.
        // The combiner takes two collections and merges them. It is usefull
        // in parallel processing
        StringBuilder word = Stream.of("ad", "jud", "i", "cate")
                .collect(() -> new StringBuilder(),          // StringBuilder::new
                        (sb, str) -> sb.append(str), // StringBuilder::append
                        (sb1, sb2) -> sb1.append(sb2)); // StringBuilder::append
        System.out.println(word);
    }

    static void terminalOperations7() {
        String stream = Stream.of("cake", "biscuits", "apple tart")
                .collect(Collectors.joining(", "));
        System.out.println(stream); // cake, biscuits, apple tart

        Double avg = Stream.of("cake", "biscuits", "apple tart")
                .collect(Collectors.averagingInt(s -> s.length()));
        System.out.println(avg); // 7.3333

        // Collecting to Maps, two functions required: the first function tells the collector
        // how to create the key; the second fuinction tells the collector how to create the value
        Map<String, Integer> map =
                Stream.of("cake", "biscuits", "apple tart")
                        .collect(Collectors.toMap(s -> s, // Function for the key
                                                  s -> s.length())); // Function for the value
        System.out.println(map);


        Map<Integer, String> map2 =
                Stream.of("cake", "biscuits", "apple tart")
                        .collect(Collectors.toMap(s -> s.length(), // key is the length
                                                  s -> s, // value is the string
                                                       (s1, s2) -> s1 + "," + s2)); // merge function
        System.out.println(map2); // {4=cake,tart, 8=biscuits}

        // the maps returned are HashMaps but this is not guaranteed, What if we wanted
        // a TreeMap implementation so our keys would be sorted. The last argument
        // caters for this
        //    Collectors.toMap
        //    @NotNull   java.util.function.Function<? super T, ? extends K> keyMapper,
        //    @NotNull   java.util.function.Function<? super T, ? extends U> valueMapper,
        //    @NotNull   java.util.function.BinaryOperator<U> mergeFunction,
        //    @NotNull   java.util.function.Supplier<@NotNull   M> mapFactory
        TreeMap<String, Integer> map3 =
                Stream.of("cake", "biscuits", "apple tart", "cake")
                        .collect(Collectors.toMap(s -> s,
                                                  s -> s.length(),
                                                  (len1, len2) -> len1 + len2,
                                                  () -> new TreeMap<>())); // TreeMap::new
        System.out.println(map3); // {apple tart=10, biscuits=8, cake=8}
        System.out.println(map3.getClass()); // class java.util.TreeMap
    }

    static void terminalOperations8() {
        // groupingBy() tells collect() to group all of the elements into a Map
        // groupingBy() takes a Function which determines the keys in the Map
        // Each value is a List of all entries that match that key
        // The List is a default, which can be changed
        Stream<String> names = Stream.of("Joe", "Tom", "Tom", "Alan", "Peter");
        Map<Integer, List<String>> map =
                names.collect(
                        // passing in a Function that determines the
                        // key in the map
                        Collectors.groupingBy(String::length) // s -> s.length()
                );
        System.out.println(map);

        // removing duplicates by returing a set
        Stream<String> names2 = Stream.of("Joe", "Tom", "Tom", "Alan", "Peter");
        Map<Integer, Set<String>> map2 =
                names.collect(
                        Collectors.groupingBy(
                        String::length,
                        Collectors.toSet()) // s -> s.length()
                );
        System.out.println(map2);
    }

    static void primitiveStreams() {
        int[] ia = {1, 2, 3};
        double[] da = {1.1, 2.2, 3.3};
        long[] la = {1L, 2L, 3L};

        IntStream iStream1 = Arrays.stream(ia);
        DoubleStream dStream1 = Arrays.stream(da);
        LongStream lStream1 = Arrays.stream(la);

        IntStream iStream2 = IntStream.of(1, 2, 3);
        DoubleStream dStream2 = DoubleStream.of(1.1, 2.2, 3.3);
        LongStream lStream3 = LongStream.of(1L, 2L, 3L);
    }

    // IntStream.of(5, 10, 15, 20);
    // IntStream.empty();
    static void printStats(IntStream numbers) {
        IntSummaryStatistics intStats = numbers.summaryStatistics(); // terminal op.

        int min = intStats.getMin();
        System.out.println(min); // 5 (2147483647 if nothing in stream)

        int max = intStats.getMax();
        System.out.println(max); // 20 (-2147483647 if nothing in stream)

        double avg = intStats.getAverage();
        System.out.println(avg); // 12.5 (0.0 if nothing in stream)

        long count = intStats.getCount();
        System.out.println(count); // 4 (0 if nothing in stream)

        long sum = intStats.getSum();
        System.out.println(sum); // 50 (0 if nothing in stream)
    }
}
