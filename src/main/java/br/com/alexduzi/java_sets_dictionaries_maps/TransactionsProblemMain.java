package br.com.alexduzi.java_sets_dictionaries_maps;

import java.util.*;

public class TransactionsProblemMain {
    //"transacoes" (ref: Leetcode invalid-transactions)
    // A transaction is possibly invalid if:
    // the amount exceeds $1000, or;
    // if it occurs within (and including) 60 minutes of another transaction with the same name in a different city.

    // You are given an array of strings transaction where transactions[i] consists of comma-separated values representing the name,
    // time (in minutes), amount, and city of the transaction. Return a list of transactions that are possibly invalid.
    // You may return the answer in any order.

        // Constraints:
    // transactions.length <= 1000
    // Each transactions[i] takes the form "{name},{time},{amount},{city}"
    // Each {name} and {city} consist of lowercase English letters, and have lengths between 1 and 10.
    // Each {time} consist of digits, and represent an integer between 0 and 1000.
    // Each {amount} consist of digits, and represent an integer between 0 and 2000.
    public static void main(String[] args) {
        String[] transactions = new String[] {
                "alice,20,800,mtv", "alice,50,100,beijing"
        };
        System.out.println(Arrays.toString(invalidTransactions(transactions)));

        String[] transactions2 = new String[] {
                "alice,20,800,mtv", "alice,50,1200,mtv"
        };
        System.out.println(Arrays.toString(invalidTransactions(transactions2)));

        String[] transactions3 = new String[] {
                "alice,20,800,mtv", "bob,50,1200,mtv"
        };
        System.out.println(Arrays.toString(invalidTransactions(transactions3)));
    }

    static String[] invalidTransactions(String[] transactions) {
        Map<String, List<Transaction>> mapTransactions = new HashMap<>();
        List<Transaction> allTransactions = new ArrayList<>();

        for (String csvLine : transactions) {
            Transaction tran = new Transaction(csvLine);
            mapTransactions.computeIfAbsent(tran.getName(), k -> new ArrayList<>()).add(tran);
            allTransactions.add(tran);
        }

        List<String> result = new ArrayList<>();

        for (Transaction t1 : allTransactions) {
            if (t1.getAmount() > 1000) {
                result.add(t1.getCsv());
                continue;
            }

            boolean foundConflict = false;
            List<Transaction> sameNameTransactions = mapTransactions.getOrDefault(t1.getName(), new ArrayList<>());

            for (Transaction t2 : sameNameTransactions) {
                if (t1 == t2) {
                    continue;
                }
                if (Math.abs(t1.getTime() - t2.getTime()) <= 60 && !t1.getCity().equals(t2.getCity())) {
                    foundConflict = true;
                    break;
                }
            }

            if (foundConflict) {
                result.add(t1.getCsv());
            }
        }

        return result.toArray(String[]::new);
    }
}

class Transaction {
    private String name;
    private Integer time;
    private Double amount;
    private String city;
    private String csv;

    public Transaction(String csvLine) {
        String[] data = csvLine.split(",");

        this.name = data[0];
        this.time = Integer.parseInt(data[1]);
        this.amount = Double.parseDouble(data[2]);
        this.city = data[3];
        this.csv = csvLine;
    }

    public String getName() {
        return name;
    }

    public Integer getTime() {
        return time;
    }

    public Double getAmount() {
        return amount;
    }

    public String getCsv() {
        return csv;
    }

    public String getCity() {
        return city;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Transaction that)) return false;
        return Objects.equals(name, that.name) && Objects.equals(time, that.time) && Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, time, amount);
    }
}