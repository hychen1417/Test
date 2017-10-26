package com.example.chenhuayu.test;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.chenhuayu.test.service.MyIntentService;
import com.example.chenhuayu.test.service.MyService;

public class MyServiceActivity extends Activity implements View.OnClickListener {

    private Button start;
    private Button stop;
    private Button bind;
    private Button unbind;
    private Button btnIntentService;

    private MyService.DownloadBinder downloadBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_service);

        start = (Button) findViewById(R.id.start_service);
        stop = (Button) findViewById(R.id.stop_service);
        bind = (Button) findViewById(R.id.bind_service);
        unbind = (Button) findViewById(R.id.unbind_service);
        btnIntentService = (Button) findViewById(R.id.start_intent_service);

        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        bind.setOnClickListener(this);
        unbind.setOnClickListener(this);
        btnIntentService.setOnClickListener(this);
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (MyService.DownloadBinder) service;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_service:
                Intent intent = new Intent(this, MyService.class);
                startService(intent);
                break;
            case R.id.stop_service:
                stopService(new Intent(this, MyService.class));
                break;
            case R.id.bind_service:
                Intent bindIntent = new Intent(this, MyService.class);
                bindService(bindIntent, serviceConnection, Service.BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service:
                unbindService(serviceConnection);
                break;
            case R.id.start_intent_service:
                startService(new Intent(this, MyIntentService.class));
                Log.e("MainActivity", "Thread id: "+ Thread.currentThread().getId() );
                break;
        }
    }
}
