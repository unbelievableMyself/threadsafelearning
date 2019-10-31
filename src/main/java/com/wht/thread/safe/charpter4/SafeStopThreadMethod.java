package com.wht.thread.safe.charpter4;

public class SafeStopThreadMethod {

    public static void main(String[] args) throws InterruptedException {
        Counter counter1 = new Counter();

        Thread t1 = new Thread(counter1,"counter 1");
        t1.start();

        Thread.sleep(1*1000);

        // 中断第一个线程
        t1.interrupt();

        Counter counter2 = new Counter();
        Thread t2 = new Thread(counter2,"counter 2");
        t2.start();

        Thread.sleep(1 * 1000);

        // 中断第二个线程
        counter2.cancel();
    }

    static class Counter implements Runnable {

        private long i;

        private volatile boolean on = true;

        public void run() {
            while (on && !Thread.currentThread().isInterrupted()) {
                i++;
            }

            System.out.println("Count i = " + i);
        }

        public void cancel() {
            on = false;
        }
    }
}
