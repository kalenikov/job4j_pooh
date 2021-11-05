package ru.job4j.pooh;

public class TestThread2 {
    public static void main(String[] args) {
        final String res1 = "my sample text";
        final String res2 = "some other text";

        // Пусть поток P1 навесит замок на ресурс res1, а затем на res2
        Thread P1 = new Thread() {
            public void run() {
                synchronized (res1) {
                    System.out.println("Поток 1 навесил замок на Ресурс 1");
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                    }

                    synchronized (res2) {
                        System.out.println("Поток 1 навесил замок на Ресурс 2");
                    }
                }
            }
        };

        // Поток P2 последовательно пытается запереть доступ к res2 и res1
        Thread P2 = new Thread() {
            public void run() {
                synchronized (res2) {
                    System.out.println("Поток 2 навесил замок на Ресурс 2");
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                    }
                    synchronized (res1) {
                        System.out.println("Поток 2 навесил замок на Ресурс 1");
                    }
                }
            }
        };

        P1.start();
        P2.start();
    }


}
