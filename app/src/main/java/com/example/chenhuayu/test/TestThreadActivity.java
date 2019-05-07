package com.example.chenhuayu.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.chenhuayu.test.java.SyncThread;

import java.util.Random;

/**
 * 多线程操作两个方法A(),B() 其中A()和B()可以同时进行，但是A()和A()互斥，B()和B()互斥
 * 关键点，synchronized锁对象
 */
public class TestThreadActivity extends AppCompatActivity {
    SyncThread syncThread1 = new SyncThread();
    SyncThread syncThread2 = new SyncThread();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_thread);
        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < 10; i++) {
                    Random random = new Random();
                    int randomInt = random.nextInt(2);
                    if (randomInt == 0) {
                        new Thread(syncThread1,"SyncThread1").start();
                    } else if (randomInt == 1) {
                        new Thread(syncThread2, "SyncThread2").start();
                    }
                }
            }
        });
    }

}
