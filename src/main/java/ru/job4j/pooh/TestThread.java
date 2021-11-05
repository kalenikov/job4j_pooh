package ru.job4j.pooh;

public class TestThread {
    public static void main(String[] args) {
        Object x = new Object();
        Object y = new Object();
        new Thread(() -> {
            synchronized (x) {
                System.out.println("Thread 1: Holding lock 1...");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ignored) {
                }
                System.out.println("Thread 1: Waiting for lock 2...");

                synchronized (y) {
                    System.out.println("Thread 1: Holding lock 1 & 2...");
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (y) {
                System.out.println("Thread 2: Holding lock 2...");

                try {
                    Thread.sleep(10);
                } catch (InterruptedException ignored) {
                }
                System.out.println("Thread 2: Waiting for lock 1...");

                synchronized (x) {
                    System.out.println("Thread 2: Holding lock 1 & 2...");
                }
            }
        }).start();
    }
}
