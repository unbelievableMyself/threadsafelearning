package com.wht.thread.safe.charpter4.util;

import java.util.concurrent.TimeUnit;

public class SleepUtils {

    public static void second(int second) {
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
