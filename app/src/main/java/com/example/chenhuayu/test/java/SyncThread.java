package com.example.chenhuayu.test.java;

import android.util.Log;


public class SyncThread implements Runnable {
    private int a, b;
    public void run() {
        synchronized (this) {//equals public synchronized run()
            if ("SyncThread1".equals(Thread.currentThread().getName())) {
                A();
            } else if ("SyncThread2".equals(Thread.currentThread().getName())) {
                B();
            }
        }
    }

    private void A() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        a++;
        Log.e("chy", "A---a=" + a + ",b=" + b);
    }

    private void B() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        b++;
        Log.e("chy", "B---a=" + a + ",b=" + b);
    }
}
