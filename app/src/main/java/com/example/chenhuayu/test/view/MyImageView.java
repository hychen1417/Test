package com.example.chenhuayu.test.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MyImageView extends ImageView implements View.OnClickListener, View.OnTouchListener {
    private static final String TAG = "Event";

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.d(TAG, "MyImageView init");
        setOnClickListener(this);
        setOnTouchListener(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d(TAG, "MyImageView dispatchTouchEvent "+ event.getAction());
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "MyImageView onTouchEvent "+ event.getAction());
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onTouch(View arg0, MotionEvent arg1) {
        Log.d(TAG, "MyImageView onTouch " + arg1.getAction());
        return false;
    }

    @Override
    public void onClick(View arg0) {
        Log.d(TAG, "MyImageView onClick");
        Toast.makeText(arg0.getContext(), "imageview click", Toast.LENGTH_SHORT).show();
    }
}
