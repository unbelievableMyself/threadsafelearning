package com.wht.thread.safe.charpter4;

import java.io.PipedReader;
import java.io.PipedWriter;

public class PipeCommunication {

    public static void main(String[] args) throws Exception {
        try (PipedWriter out = new PipedWriter();
             PipedReader in = new PipedReader()) {

            // 输出管道绑定输入管道
            out.connect(in);

            // 将输入管道传入子线程
            new Thread(new PrintThread(in), "PrintThread").start();

            int receive = 0;

            // 将控制台的输入写入管道
            while ((receive = System.in.read()) != -1) {
                out.write(receive);
            }
        } finally {

        }

    }

    static class PrintThread implements Runnable {

        PipedReader reader = null;

        public PrintThread(PipedReader reader) {
            this.reader = reader;
        }

        @Override
        public void run() {
            int receive = 0;

            try {
                // 从管道里读出数据，并打印
                while ((receive = reader.read()) != -1) {
                    System.out.print((char) receive);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
