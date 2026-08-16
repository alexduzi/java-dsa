package br.com.alexduzi.java_queues;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        MyCustomQueue<Integer> myQueue = new MyCustomQueue<>(15);
        myQueue.add(1);
        myQueue.add(2);
        myQueue.add(3);
        myQueue.add(4);

        System.out.println("Queue size: " + myQueue.count());

        Integer value;
        while (!myQueue.isEmpty()) {
            value = myQueue.remove();
            System.out.print(value + " ");
        }
        System.out.println();

        System.out.println("Queue size: " + myQueue.count());
    }
}

// Definição de Fila
// Uma fila é uma estrutura de dados linear que segue a ordem de
// operação FIFO, onde o primeiro elemento inserido é o primeiro a ser removido
// FIFO: first in, first out
// exemplos de aplicações:
// jobs de impressão
// eventos em sistemas distribuídos
// envio de pacotes em redes
// processamento de requisições
// sistemas de mensageria
// processos em um sistema operacional
class MyCustomQueue<T> {
    private T[] items;
    private int size;
    private int index;

    @SuppressWarnings("unchecked")
    public MyCustomQueue(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Size cannot be less than 1");
        }
        this.size = size;
        this.items = (T[]) new Object[size];
        this.index = 0;
    }

    public int count() {
        return index;
    }

    public boolean isEmpty() {
        return index == 0;
    }

    public boolean isFull() {
        return index == size;
    }

    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Empty queue");
        }
        return items[0];
    }

    public void add(T item) {
        if (isFull()) {
            throw  new RuntimeException("Queue is full");
        }
        items[index] = item;
        index++; // avança para a próxima posição somente ao inserir
    }

    public T remove() {
        if (isEmpty()) {
            throw new RuntimeException("Empty queue");
        }

        T item = items[0];

        // Limpa a primeira posição para evitar vazamento de memória (Memory Leak)
        items[0] = null;

        // Move todos os elementos uma posição para a esquerda
        // Copia de 'items' (a partir do índice 1) para 'items' (a partir do índice 0)
        System.arraycopy(items, 1, items, 0, index - 1);

        // Decrementa o ponteiro de controle e limpa a antiga última posição
        index--;
        items[index] = null;

        return item;
    }
}