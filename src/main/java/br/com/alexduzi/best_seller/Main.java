package br.com.alexduzi.best_seller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Type sellerTypeList = new TypeToken<List<Seller>>(){}.getType();

        try (InputStream is = Main.class.getResourceAsStream("/seller.json")) {

            if (is == null) {
                System.err.println("Error: seller.json not found!");
                return;
            }

            try (InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                 BufferedReader reader = new BufferedReader(isr)) {

                List<Seller> sellers = gson.fromJson(reader, sellerTypeList);

                System.out.println("Best seller is: " + findBestSeller(sellers));

                System.out.println("Best seller 2 is: " + findBestSeller2(sellers));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static String findBestSeller(List<Seller> sellers) {
        String name = "";
        Double maxAmount = sellers.getFirst().getAmount();

        for (Seller seller : sellers) {
            if (seller.getAmount() > maxAmount) {
                maxAmount = seller.getAmount();
                name = seller.getName();
            }
        }

        return name;
    }

    static String findBestSeller2(List<Seller> sellers) {
        sellers.sort((a, b) -> {
            if (a.getAmount().equals(b.getAmount())) {
                return 0;
            }
            if (a.getAmount() < b.getAmount()) {
                return 1;
            }
            return -1;
        });

        return sellers.getFirst().getName();
    }
}

class Seller {
    private String name;
    private Double amount;
    public Seller() {
    }

    public Seller(String name, Double amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}