package com.wht.thread.safe.charpter4;

import com.wht.thread.safe.charpter4.util.SleepUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WaitNotifyExample {

    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args) {

        new Thread(new WaitThread(), "WaitThread").start();

        SleepUtils.second(1);

        new Thread(new NotifyThread(), "NotifyThread").start();

    }

    static class WaitThread implements Runnable {

        public void run() {
            synchronized (lock) {
                while (flag) {
                    try {
                        System.out.println(Thread.currentThread() + " flag is true. wait@ " +
                                new SimpleDateFormat("HH:mm:ss").format(new Date()));

                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // 满足条件，工作或者消费信息
                System.out.println(Thread.currentThread() + " flag is false. do work or consume message @ " +
                        new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }
    }


    static class NotifyThread implements Runnable {

        public void run() {
            synchronized (lock) {
                // 改变条件，让消费者可以继续工作
                flag = false;

                System.out.println(Thread.currentThread() + "  hold lock. notify @ " +
                        new SimpleDateFormat("HH:mm:ss").format(new Date()));

                // 通知
                lock.notifyAll();

                // 让本线程释放锁后，等待的线程能获取到锁
                SleepUtils.second(5);
            }

            synchronized (lock) {

                System.out.println(Thread.currentThread() + "  hold lock again. sleep @ " +
                        new SimpleDateFormat("HH:mm:ss").format(new Date()));

                SleepUtils.second(5);
            }
        }
    }
}
