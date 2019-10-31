package com.wht.thread.safe.charpter4;

import java.util.concurrent.TimeUnit;

/**
 * 线程中断
 *
 * 拥有线程ref的线程，使用ref的中断方法，向线程发起中断信号；
 * 线程通过不断检查自身的中断线程来决定程序是否继续进行或者停止
 *
 * 适用场景：取消或者停止任务
 */
public class ThreadInterruptExample {

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(new SleepRunner(), "SleepThread");
        t1.setDaemon(true);

        Thread t2 = new Thread(new BusyRunner(), "BusyThread");
        t2.setDaemon(true);

        t1.start();
        t2.start();

        TimeUnit.SECONDS.sleep(5);

        t1.interrupt();
        t2.interrupt();

        System.out.println("Sleep thread interrupted is " + t1.isInterrupted());
        System.out.println("Busy thread interrupted is " + t2.isInterrupted());

        // 避免两个线程退出
        TimeUnit.SECONDS.sleep(2);
    }

    static class SleepRunner implements Runnable {

        public void run() {
            while (true) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class BusyRunner implements Runnable {
        public void run() {
            while (true) {

            }
        }
    }
}
