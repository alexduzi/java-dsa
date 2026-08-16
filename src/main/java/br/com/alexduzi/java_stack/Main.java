package br.com.alexduzi.java_stack;

import java.util.EmptyStackException;

public class Main {
    public static void main(String[] args) {
        MyCustomStack<Integer> myStack = new MyCustomStack<>(15);
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);
        myStack.push(5);

        Integer value;
        while (!myStack.isEmpty()) {
            value = myStack.pop();
            System.out.println("Popped value: " + value);
        }

        System.out.println("Stack is empty: " + myStack.isEmpty());
    }
}

// último que entra é o primeiro que sai
// LAST IN FIRST OUT
// LIFO
class MyCustomStack<T> {
    private T[] items;
    private int size;
    private int top;

    @SuppressWarnings("unchecked")
    public MyCustomStack(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Size cannot be less than 1");
        }
        this.size = size;
        this.items = (T[]) new Object[size];
        this.top = 0;
    }

    public void clear() {
        top = -1;
    }

    public int count() {
        return top + 1;
    }

    public boolean isEmpty() {
        return this.top == 0;
    }

    public boolean isFull() {
        return this.top == this.size-1;
    }

    public void push(T item) {
        if (isFull()) {
            throw new StackOverflowError("Stack is full");
        }
        items[top] = item;
        top++;
    }

    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        T item = items[top-1];
        items[top] = null;
        top--;
        return item;
    }

    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return items[top];
    }
}
