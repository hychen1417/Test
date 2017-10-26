package com.example.chenhuayu.test.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by chenhuayu on 2017/10/17.
 */

public class MyService extends Service {

    private static final String TAG = "MyService";
    private DownloadBinder mBinder = new DownloadBinder();

    public class DownloadBinder extends Binder {

        public void startDownload() {
            Log.e(TAG, "startDownload: ");
        }
        public int getProgress(){
            Log.e(TAG, "getProgress: ");
            return 0;
        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
    public void onCreate(){
        Log.e(TAG, "onCreate: " );
        super.onCreate();
    }

    public int onStartCommand(Intent intent,int flags,int startId){
        Log.e(TAG, "onStartCommand: " );
        return super.onStartCommand(intent,flags,startId);
    }

    public void onDestroy(){
        Log.e(TAG, "onDestroy: " );
        super.onDestroy();
    }
}
