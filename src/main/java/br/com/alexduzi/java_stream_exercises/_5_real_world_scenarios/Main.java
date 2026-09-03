package br.com.alexduzi.java_stream_exercises._5_real_world_scenarios;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

    }

    // 61. Process a CSV File and Calculate Aggregate Statistics
    public void ex61() throws IOException {
        Path path = Paths.get("data.csv");
        Map<String, Double> averageSalaryByDept = Files.lines(path)
                .skip(1)
                .map(line -> line.split(","))
                .collect(
                Collectors.groupingBy(
                        fields -> fields[1],
                        Collectors.averagingDouble((fields -> Double.parseDouble(fields[2]))
                )
        ));
        System.out.println("Average Salary by Department: " + averageSalaryByDept);
    }

    // 62. Filter and Transform Data Fetched from a Database
    public void ex62() {
//        List<Employee> employees = // Fetch from database
//        Map<String, List<String>> namesByDept = employees.stream()
//                .filter(e -> e.getSalary() > 50000)
//                .collect(Collectors.groupingBy(
//                        Employee::getDepartment,
//                        Collectors.mapping(Employee::getName, Collectors.toList())
//                ));
//        System.out.println("Names by Department: " + namesByDept);
    }

    // 63. Parse and Validate JSON Payloads
    public void ex63() {
//        String json = "[{\"name\":\"Alice\",\"age\":25},{\"name\":\"Bob\",\"age\":30}]";
//        List<Person> people = new ObjectMapper().readValue(json, new TypeReference<List<Person>>() {});
//        List<String> validNames = people.stream()
//                .filter(p -> p.getAge() > 25)
//                .map(Person::getName)
//                .collect(Collectors.toList());
//        System.out.println("Valid Names: " + validNames);
    }

    // 64. Combine Multiple Asynchronous Tasks
    public void ex64() {
        CompletableFuture<List<Integer>> future1 = CompletableFuture.supplyAsync(() -> List.of(1, 2, 3));
        CompletableFuture<List<Integer>> future2 = CompletableFuture.supplyAsync(() -> List.of(4, 5, 6));
        List<Integer> combined = Stream.of(future1, future2)
                .map(CompletableFuture::join)
                .flatMap(List::stream)
                .collect(Collectors.toList());
        System.out.println("Combined List: " + combined);
    }

    // 65. Process Large Datasets in Parallel
    public void ex65() {
        List<Integer> numbers = IntStream.rangeClosed(1, 1000000).boxed().collect(Collectors.toList());
        long sum = numbers.parallelStream()
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("Sum: " + sum);
    }

    // 66. Handle Exceptions in Streams
    public void ex66() {
        List<String> numbers = List.of("1", "2", "three", "4");
        List<Integer> parsedNumbers = numbers.stream()
                .flatMap(s -> {
                    try {
                        return Stream.of(Integer.parseInt(s));
                    } catch (NumberFormatException e) {
                        return Stream.empty();
                    }
                })
                .collect(Collectors.toList());
        System.out.println("Parsed Numbers: " + parsedNumbers);
    }

    // 67. Use Custom Collectors to Calculate Statistics
    public void ex67() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        DoubleSummaryStatistics stats = numbers.stream()
                .collect(Collectors.summarizingDouble(Integer::intValue));
        System.out.println("Stats: " + stats);
    }

    // 68. Group Employees by Department and Calculate Average Salary
    public void ex68() {
//        List<Employee> employees = List.of(
//                new Employee("Alice", "HR", 50000),
//                new Employee("Bob", "IT", 60000),
//                new Employee("Charlie", "HR", 55000)
//        );
//        Map<String, Double> avgSalaryByDept = employees.stream()
//                .collect(Collectors.groupingBy(
//                        Employee::getDepartment,
//                        Collectors.averagingDouble(Employee::getSalary)
//                ));
//        System.out.println("Average Salary by Department: " + avgSalaryByDept);
    }

    // 67. Find the Top N Highest-Paid Employees
    public void ex69() {
//        List<Employee> employees = List.of(
//                new Employee("Alice", "HR", 50000),
//                new Employee("Bob", "IT", 60000),
//                new Employee("Charlie", "HR", 55000)
//        );
//        List<Employee> topN = employees.stream()
//                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
//                .limit(2)
//                .collect(Collectors.toList());
//        System.out.println("Top 2 Employees: " + topN);
    }

    // 70. Find the Top N Most Frequent Words in a Text File
    public void ex70() throws IOException {
        Path path = Paths.get("sample.txt");
        List<String> topNWords = Files.lines(path)
                .flatMap(line -> Arrays.stream(line.split(" ")))
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        System.out.println("Top 3 Words: " + topNWords);
    }
}
