package br.com.alexduzi.best_time_to_buy_and_sell_stock;

public class Main {
    // leetcode ref: https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/
    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{ 7, 1, 5, 3, 6, 4 })); // output 5
        System.out.println(maxProfit(new int[]{ 7, 6, 4, 3, 1 })); // output 0
    }

    static int maxProfit(int[] prices) {
        // Track the minimum price seen so far (initially the first price)
        int minPrice = prices[0];

        // Initialize maximum profit to 0
        int maxProfit = 0;

        for (int price : prices) {
            // Update maximum profit if selling at current price yields higher profit
            // Profit = current price - minimum price seen so far
            maxProfit = Math.max(maxProfit, price-minPrice);

            minPrice = Math.min(minPrice, price);
        }

        return maxProfit;
    }
}
