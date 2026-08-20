package br.com.alexduzi.java_concurrency;

public class Deadlock {
    public static void go(){
        final String ransom    = "ransom";
        final String hostage   = "hostage";

        Thread criminals = new Thread( () -> {
            synchronized(hostage){
                System.out.println("The criminals have the hostage and want the ransom...");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                // make sure this next block is inside the first synchronized block.
                synchronized(ransom){
                    System.out.println("The criminals have BOTH!");
                } // auto release of lock on 'ransom'
            } // auto release of lock on 'hostage'
        });

        Thread police = new Thread( () -> {
            synchronized(hostage){
                System.out.println("The police have the hostage and want the ransom...");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                synchronized(ransom){
                    System.out.println("The police have BOTH!.");
                } // auto release of lock on 'hostage'
            } // auto release of lock on 'ransom'
        });

        criminals.start();
        police.start();
    }

    // A deadlock occurs when locking threads are waiting on
    // each other to free locks that they themselves hold

    // A livelock is similar to a deadlock in that the threads
    // involved are stuck, making no progress. However, with deadlock
    // the threads are doing nothing. With livelock, the threads are busy but their actions are
    // repeatedly triggering the same conditions

    // A real-world example of livelock occurs when two people meet in a narrow corridor
    // and each tries to be polite by moving aside to let the other pass
    // but they end up swaying from side to side without making any
    // progress because they both repeategly move the same way at the same time
    public static void main(String[] args) {
        go();
    }
}
