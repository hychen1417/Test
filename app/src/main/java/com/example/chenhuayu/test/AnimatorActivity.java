package com.example.chenhuayu.test;

import android.animation.Animator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import java.util.LinkedHashMap;
import java.util.Map;

public class AnimatorActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);
        findViewById(R.id.button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                TranslateAnimation animator = new TranslateAnimation(0,200,0,200);
                animator.setDuration(1000);
                findViewById(R.id.text).startAnimation(animator);
                break;
        }
    }


}
