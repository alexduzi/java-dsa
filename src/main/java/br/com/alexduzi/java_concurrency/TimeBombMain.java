package br.com.alexduzi.java_concurrency;

class CountDown implements Runnable {

    String[] timeStr = { "Zero", "One", "Two", "Three",
            "Four", "Five", "Six", "Seven", "Eight", "Nine" };

    @Override
    public void run() {
        for (int i = timeStr.length-1; i >= 0; i--) {
            try {
                System.out.println(timeStr[i]);
                // Causes the currently executing thread to sleep (temporarily cease execution) for the specified number of milliseconds
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class TimeBombMain {
    public static void main(String[] args) {
        Thread timer = new Thread(new CountDown());
        System.out.println("Starting 10 second count down...");
        timer.start();
        try {
            // the main thread must wait here, until the timer thread finishes
            timer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Boom!!");
    }
}
