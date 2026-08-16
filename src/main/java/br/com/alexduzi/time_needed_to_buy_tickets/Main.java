package br.com.alexduzi.time_needed_to_buy_tickets;

import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    // problema tickets leetcode
    // https://leetcode.com/problems/time-needed-to-buy-tickets/description/
    public static void main(String[] args) {
        System.out.println(timeRequiredToBuy(new int[] { 2, 3, 2 }, 2));
    }

    // O(n * max(tickets))
    static int timeRequiredToBuy(int[] tickets, int k) {
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < tickets.length; i++) {
            queue.add(i);
        }

        int time = 0;

        while (true) {
            // tira da fila
            int i = queue.poll();

            // decrementa, é a mesma coisa que comprar o ticket
            tickets[i]--;

            // e incrementa o tempo
            time++;

            // Se a pessoa k acabou de comprar seu último bilhete, para imediatamente
            if (i == k && tickets[i] == 0) {
                return time;
            }

            // Se ainda restam ingressos para essa pessoa (qualquer uma), ela volta para a fila
            if (tickets[i] > 0) {
                queue.add(i);
            }
        }
    }

    // O(n)
    static int timeRequiredToBuy2(int[] tickets, int k) {
        int time = 0;
        int targetTickets = tickets[k];

        for (int i = 0; i < tickets.length; i++) {
            if (i <= k) {
                // Compra o mínimo entre o que a pessoa quer e o que k quer
                time += Math.min(tickets[i], targetTickets);
            } else {
                // Compra o mínimo entre o que a pessoa quer e o que k quer - 1
                time += Math.min(tickets[i], targetTickets - 1);
            }
        }

        return time;
    }
}
