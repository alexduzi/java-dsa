package br.com.alexduzi.java_concurrency;

public class ConcurrencyMain {
    // A process consists of one or more threads
    // A thread is the smallest unit of execution. A thread executes tasks
    // A task defines the work that the thread will execute e.g this is often a lambda
    // The order of thread execution is non-deterministic and not guaranteed
    public static void main(String[] args) {
        // extending Thread
        MyCustomThread custom = new MyCustomThread();
        custom.start();

        // implementing Runnable interface
        new Thread(new MyCustomThread2()).start();

        // implementing Runnable interface lambda version
        threadRunnable();

        System.out.println("main(): " + Thread.currentThread().getName());
    }

    static void threadRunnable() {
        Thread t = new Thread(() ->
                System.out.println("Runnable Lambda: " +
                        Thread.currentThread().getName()));
        t.start();
    }
}

// Creating thread by extending Thread
class MyCustomThread extends Thread {

    @Override
    public void run() {
        System.out.println("Thread MyCustomThread: " + getName());
    }
}

// Creating thread by implementing Runnable
class MyCustomThread2 implements Runnable {

    @Override
    public void run() {
        System.out.println("Runnable MyCustomThread2: " + Thread.currentThread().getName());
    }
}
