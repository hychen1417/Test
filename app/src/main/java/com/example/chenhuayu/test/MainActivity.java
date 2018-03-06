package com.example.chenhuayu.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity implements View.OnClickListener{
    private Button btnJs;
    private Button btnMyCardView;
    private Button btnGreenDaoDemo;
    private Button btnkDXF;
    private Button btnLyric;
    private Button btnNotification;
    private Button btnService;
    private Button btnShake;
    private Button btnKotlin;
    private Button btnRecycleView;
    private Button btnScaleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setOnClickListener();
    }

    public void initView() {
        btnJs = (Button) findViewById(R.id.btn_js);
        btnMyCardView = (Button) findViewById(R.id.btn_mycardview);
        btnGreenDaoDemo = (Button) findViewById(R.id.btn_greenDaoDemo);
        btnkDXF = (Button) findViewById(R.id.btn_kedaxunfei);
        btnLyric = (Button) findViewById(R.id.btn_lyric);
        btnNotification = (Button) findViewById(R.id.btn_notification);
        btnService = (Button) findViewById(R.id.btn_service);
        btnShake = (Button) findViewById(R.id.shake);
        btnKotlin = (Button) findViewById(R.id.first_kotlin_activity);
        btnRecycleView = (Button) findViewById(R.id.recycleView);
        btnScaleView = (Button) findViewById(R.id.scaleview);
    }

    public void setOnClickListener(){
        btnJs.setOnClickListener(this);
        btnMyCardView.setOnClickListener(this);
        btnGreenDaoDemo.setOnClickListener(this);
        btnkDXF.setOnClickListener(this);
        btnLyric.setOnClickListener(this);
        btnNotification.setOnClickListener(this);
        btnService.setOnClickListener(this);
        btnJs.setOnClickListener(this);
        btnShake.setOnClickListener(this);
        btnKotlin.setOnClickListener(this);
        btnRecycleView.setOnClickListener(this);
        btnScaleView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_js:
                Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_mycardview:
                startActivity(new Intent(MainActivity.this, MyViewActivity.class));
                break;
            case R.id.btn_greenDaoDemo:
                startActivity(new Intent(MainActivity.this, GreenDaoDemoActivity.class));
                break;
            case R.id.btn_kedaxunfei:
                startActivity(new Intent(MainActivity.this, KeDaXunFeiActivity.class));
                break;
            case R.id.btn_lyric:
                startActivity(new Intent(MainActivity.this, LyricActivity.class));
                break;
            case R.id.btn_notification:
                startActivity(new Intent(MainActivity.this, NotificationActivity.class));
                break;
            case R.id.btn_service:
                startActivity(new Intent(MainActivity.this, MyServiceActivity.class));
                break;
            case R.id.shake:
                startActivity(new Intent(MainActivity.this, ShakeActivity.class));
                break;
            case R.id.first_kotlin_activity:
//                startActivity(new Intent(MainActivity.this, FirstKotlinActivity.class));
                break;
            case R.id.recycleView:
                startActivity(new Intent(MainActivity.this, RecycleViewActivity.class));
                break;
            case R.id.scaleview:
                startActivity(new Intent(MainActivity.this, ScaleImageViewActivity.class));
                break;
        }
    }
}
