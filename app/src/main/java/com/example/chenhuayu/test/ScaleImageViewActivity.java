package com.example.chenhuayu.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.chenhuayu.test.view.ScaleView;
import com.example.chenhuayu.test.view.ZoomImageView;

public class ScaleImageViewActivity extends AppCompatActivity {

    private ZoomImageView scaleView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scale_image_view);
        scaleView = (ZoomImageView) findViewById(R.id.image);
    }
}
