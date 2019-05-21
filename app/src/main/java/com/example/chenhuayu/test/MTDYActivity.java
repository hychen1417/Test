package com.example.chenhuayu.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.chenhuayu.test.view.MTDYScrollView;

public class MTDYActivity extends AppCompatActivity {

    private RelativeLayout xiaoshi;
    private MTDYScrollView scrollView;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mtdy);
        xiaoshi = (RelativeLayout) findViewById(R.id.xiaoshi);
        scrollView = (MTDYScrollView) findViewById(R.id.scrollView);
        iv = (ImageView) findViewById(R.id.iv);
        scrollView.setScrollViewListener(new MTDYScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(MTDYScrollView ceshimy, int l, int t, int oldl, int oldt) {
                if (t > iv.getHeight()) {
                    xiaoshi.setVisibility(View.VISIBLE);
                } else {
                    xiaoshi.setVisibility(View.GONE);
                }
            }
        });
    }
}
