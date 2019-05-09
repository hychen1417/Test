package com.example.chenhuayu.test;

import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.chenhuayu.test.java.SyncThread;

import java.util.Random;

/**
 * 多线程操作两个方法A(),B() 其中A()和B()可以同时进行，但是A()和A()互斥，B()和B()互斥
 * 关键点，synchronized锁对象
 */
public class TestThreadActivity extends AppCompatActivity {
    SyncThread syncThread1 = new SyncThread();
    SyncThread syncThread2 = new SyncThread();

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_thread);
        button = (Button) findViewById(R.id.start);
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(Looper.myLooper() == null)
                {
                    Looper.prepare();
                }
                Toast.makeText(TestThreadActivity.this,"aaa",Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }).start();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
//                        if(Looper.myLooper() == null)
//                        {
//                            Looper.prepare();
//                        }
//                        Toast.makeText(TestThreadActivity.this,"bbb",Toast.LENGTH_SHORT).show();
//                        Looper.loop();
//                        button.setText("aaaaaaa");
                    }
                }).start();
//                for (int i = 0; i < 10; i++) {
//                    Random random = new Random();
//                    int randomInt = random.nextInt(2);
//                    if (randomInt == 0) {
//                        new Thread(syncThread1,"SyncThread1").start();
//                    } else if (randomInt == 1) {
//                        new Thread(syncThread2, "SyncThread2").start();
//                    }
//                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Thread(new Runnable() {
            @Override
            public void run() {
                button.setText("bbbbbb");
            }
        }).start();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
//                        if(Looper.myLooper() == null)
//                        {
//                            Looper.prepare();
//                        }
//                        Toast.makeText(TestThreadActivity.this,"bbb",Toast.LENGTH_SHORT).show();
//                        Looper.loop();
                        button.setBackgroundResource(R.color.blue);
                    }
                }).start();
            }
        });
    }
}
