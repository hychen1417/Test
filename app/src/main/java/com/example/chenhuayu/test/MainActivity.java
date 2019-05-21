package com.example.chenhuayu.test;

import android.app.Activity;
import android.app.LauncherActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chenhuayu.test.bean.TestBean;
import com.example.chenhuayu.test.util.StartActivityUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipeline;

import java.util.HashMap;


public class MainActivity extends Activity implements View.OnClickListener {
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
    private Button btnPicasso;
    private Button btnTestRequestFeature;
    private Button btnToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
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
        btnPicasso = (Button) findViewById(R.id.picasso);
        btnTestRequestFeature = (Button) findViewById(R.id.request_feature);
        btnToolBar = (Button) findViewById(R.id.tool_bar);
    }

    public void setOnClickListener() {
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
        btnPicasso.setOnClickListener(this);
        btnTestRequestFeature.setOnClickListener(this);
        btnToolBar.setOnClickListener(this);
        findViewById(R.id.test_thread).setOnClickListener(this);
        findViewById(R.id.animator).setOnClickListener(this);
        findViewById(R.id.launch_mode).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_js:
                StartActivityUtil.skipAnotherActivity(MainActivity.this, WebViewActivity.class);
                break;
            case R.id.btn_mycardview:
                StartActivityUtil.skipAnotherActivity(MainActivity.this, MyViewActivity.class);
                break;
            case R.id.btn_greenDaoDemo:
                StartActivityUtil.skipAnotherActivity(MainActivity.this, GreenDaoDemoActivity.class);
                break;
            case R.id.btn_kedaxunfei:
                StartActivityUtil.skipAnotherActivity(MainActivity.this, KeDaXunFeiActivity.class);
                break;
            case R.id.btn_lyric:
                StartActivityUtil.skipAnotherActivity(MainActivity.this, LyricActivity.class);
                break;
            case R.id.btn_notification:
                StartActivityUtil.skipAnotherActivity(MainActivity.this, NotificationActivity.class);
                break;
            case R.id.btn_service:
                StartActivityUtil.skipAnotherActivity(MainActivity.this, MyServiceActivity.class);
                break;
            case R.id.shake:
                StartActivityUtil.skipAnotherActivity(MainActivity.this, ShakeActivity.class);
                break;
            case R.id.first_kotlin_activity:
//                startActivity(new Intent(MainActivity.this, FirstKotlinActivity.class));
                break;
            case R.id.recycleView:
                StartActivityUtil.skipAnotherActivity(MainActivity.this, RecycleViewActivity.class);
                break;
            case R.id.scaleview:
                StartActivityUtil.skipAnotherActivity(MainActivity.this, ScaleImageViewActivity.class);
                break;
            case R.id.picasso:
                StartActivityUtil.skipAnotherActivity(MainActivity.this, PicassoActivity.class);
                break;
            case R.id.request_feature:
                StartActivityUtil.skipAnotherActivity(this, RequestFeatureActivity.class);
                break;
            case R.id.tool_bar:
                HashMap<String, Object> map = new HashMap<>();
                TestBean testBean = new TestBean();
                testBean.name = "chy";
                testBean.password = "123456";
                map.put("name", testBean);
                StartActivityUtil.skipAnotherActivity(this, ToolbarActivity.class, map);
                break;
            case R.id.test_thread:
                StartActivityUtil.skipAnotherActivity(this, TestThreadActivity.class);
                break;
            case R.id.animator:
                StartActivityUtil.skipAnotherActivity(this, AnimatorActivity.class);
                break;
            case R.id.launch_mode:
                StartActivityUtil.skipAnotherActivity(this, MTDYActivity.class);
                break;
        }
    }
}
