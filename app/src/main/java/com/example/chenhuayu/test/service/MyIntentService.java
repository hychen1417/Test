package com.example.chenhuayu.test.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by chenhuayu on 2017/10/17.
 */

public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.e("MyIntentService", "Thread id: " + Thread.currentThread().getId());
    }

    public void onDestroy(){
        Log.e("MyIntentService", "onDestroy: " );
    }
}
