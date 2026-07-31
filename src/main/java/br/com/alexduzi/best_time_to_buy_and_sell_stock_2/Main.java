package br.com.alexduzi.best_time_to_buy_and_sell_stock_2;

public class Main {
    // leetcode ref: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/description/
    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{ 7, 1, 5, 3, 6, 4 })); // output 7
        System.out.println(maxProfit(new int[]{ 1, 2, 3, 4, 5 })); // output 4
        System.out.println(maxProfit(new int[]{ 7, 6, 4, 3, 1 })); // output 0
    }

    static int maxProfit(int[] prices) {
        int maxProfit = 0;

        for (int i = 0; i < prices.length; i++) {
            if (i+1 == prices.length) {
                break;
            }

            if ((prices[i+1] - prices[i]) > 0) {
                maxProfit += prices[i+1] - prices[i];
            }
        }

        return maxProfit;
    }
}
