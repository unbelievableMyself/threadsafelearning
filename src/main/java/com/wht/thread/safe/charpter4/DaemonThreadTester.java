package com.wht.thread.safe.charpter4;

/**
 * Daemon 线程是后台线程
 *
 * 当所有的用户线程执行完毕，后台线程无论是否执行完成，jvm都会退出
 */
public class DaemonThreadTester {

    public static void main(String[] args) {
        Thread t = new Thread(new DaemonRunner(), "DaemonRunner");
        t.setDaemon(true);
        t.start();
    }

    static class DaemonRunner implements Runnable {

        public void run() {
            try {
                Thread.sleep(10 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Daemon thread finally run.");
            }
        }
    }
}
