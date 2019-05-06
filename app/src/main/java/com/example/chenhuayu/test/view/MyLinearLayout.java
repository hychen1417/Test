package com.example.chenhuayu.test.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class MyLinearLayout extends LinearLayout {
    private final String TAG = "MyLinearLayout";

    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(TAG, "1:dispatchTouchEvent action:"+ev.getAction());
        return super.dispatchTouchEvent(ev);

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "1:onInterceptTouchEvent action:ACTION_DOWN");
                //return true;
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "1:onInterceptTouchEvent action:ACTION_MOVE");
                return true;
            //break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "1:onInterceptTouchEvent action:ACTION_UP");
                //return true;
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "1:onInterceptTouchEvent action:ACTION_CANCEL");
                break;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "1:onTouchEvent action:ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "1:onTouchEvent action:ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "1:onTouchEvent action:ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "1:onTouchEvent action:ACTION_CANCEL");
                break;
        }
        return true;
    }
}
