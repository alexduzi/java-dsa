package br.com.alexduzi.invalid_transactions;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InvalidTransactionsMain {
    // https://leetcode.com/problems/invalid-transactions/description/

    // A transaction is possibly invalid if:

    // the amount exceeds $1000, or;
    // if it occurs within (and including) 60 minutes of another transaction with the same name in a different city.
    // You are given an array of strings transaction where transactions[i] consists of comma-separated values representing the name,
    // time (in minutes), amount, and city of the transaction.

    // Return a list of transactions that are possibly invalid. You may return the answer in any order.

    // Example 1:

    // Input: transactions = ["alice,20,800,mtv","alice,50,100,beijing"]
    // Output: ["alice,20,800,mtv","alice,50,100,beijing"]
    // Explanation: The first transaction is invalid because the second transaction occurs within a difference of 60 minutes,
    // have the same name and is in a different city. Similarly the second one is invalid too.
    // Example 2:

    // Input: transactions = ["alice,20,800,mtv","alice,50,1200,mtv"]
    // Output: ["alice,50,1200,mtv"]
    // Example 3:

        // Input: transactions = ["alice,20,800,mtv","bob,50,1200,mtv"]
    // Output: ["bob,50,1200,mtv"]
    public static void main(String[] args) {
        System.out.println(Arrays.toString(invalidTransactions(new String[]{
            "alice,20,800,mtv",
            "alice,50,1200,beijing",
            "bob,60,300,sp",
            "ana,70,1100,sp"
        })));

        System.out.println(Arrays.toString(invalidTransactions2(new String[]{
                "alice,20,800,mtv",
                "alice,50,1200,beijing",
                "bob,60,300,sp",
                "ana,70,1100,sp"
        })));
    }

    static String[] invalidTransactions(String[] transactions) {
        boolean[] trans = new boolean[transactions.length];
        String[] result = new String[transactions.length];

        for (int i = 0; i < transactions.length; i++) {
            Transaction ti = new Transaction(transactions[i]);
            if (ti.getMoney() > 1000) {
                trans[i] = true;
            }
            for (int j = i + 1; j < transactions.length; j++) {
                Transaction tj = new Transaction(transactions[i]);
                if (ti.getName().equals(tj.getName()) && Math.abs(ti.getTime() - tj.getTime()) <= 60 && !ti.getCity().equals(tj.getCity()) ) {
                    trans[i] = true;
                    trans[j] = true;
                }
            }
        }

        for (int i = 0; i < transactions.length; i++) {
            if (trans[i]) {
                result[i] = transactions[i];
            }
        }

        return result;
    }

    static String[] invalidTransactions2(String[] transactions) {
        List<Transaction> allTransactions = Arrays.stream(transactions).map(Transaction::new).toList();

        Map<String,List<Transaction>> map = new HashMap<>();

        for (Transaction tran : allTransactions) {
            if (map.containsKey(tran.getName())) {
                List<Transaction> list = map.get(tran.getName());
                list.add(tran);
            } else {
                List<Transaction> list = new ArrayList<>();
                list.add(tran);
                map.put(tran.getName(), list);
            }
        }

        List<String> result = new ArrayList<>();

        for (Transaction tran1 : allTransactions) {
            if (tran1.getMoney() > 1000) {
                result.add(tran1.getCsv());
            } else {
                for (Transaction tran2 : map.get(tran1.getName())) {
                    if ((tran1.getTime() - tran2.getTime()) <= 60 && !tran1.getCity().equals(tran2.getCity())) {
                        result.add(tran1.getCsv());
                        break;
                    }
                }
            }
        }

        return result.toArray(String[]::new);
    }
}

class Transaction {
    private String name;
    private Integer time;
    private Double money;
    private String city;
    private String csv;
    public Transaction() {
    }

    public Transaction(String csv) {
        this.csv = csv;
        String[] data = csv.split(",");
        this.name = data[0];
        this.time = Integer.parseInt(data[1]);
        this.money = Double.parseDouble(data[2]);
        this.city = data[3];
    }

    public Transaction(String name, Integer time, Double money, String city) {
        this.name = name;
        this.time = time;
        this.money = money;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCsv() {
        return csv;
    }

    public void setCsv(String csv) {
        this.csv = csv;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Transaction that)) return false;
        return Objects.equals(name, that.name) && Objects.equals(time, that.time) && Objects.equals(money, that.money) && Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, time, money, city);
    }
}

