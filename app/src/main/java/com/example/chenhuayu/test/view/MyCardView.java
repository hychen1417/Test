package com.example.chenhuayu.test.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by chenhuayu on 2017/8/28.
 */

public class MyCardView extends LinearLayout {
    
    private final String TAG = "MyViewActivity";

    private int radius = 8;
    private int gap = 8;
    private Paint mPaint;

    public MyCardView(Context context) {
        super(context);
        init();
    }

    public MyCardView(Context context,AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.d(TAG, "onFinishInflate: ");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d(TAG, "onMeasure: ");
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        Log.d(TAG, "onLayout: ");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d(TAG, "onSizeChanged: ");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d(TAG, "onKeyDown: ");
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Log.d(TAG, "onKeyUp: ");
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        Log.d(TAG, "onWindowFocusChanged: ");
        super.onWindowFocusChanged(hasWindowFocus);
    }

    @Override
    protected void onAttachedToWindow() {
        Log.d(TAG, "onAttachedToWindow: ");
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        Log.d(TAG, "onDetachedFromWindow: ");
        super.onDetachedFromWindow();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(TAG, "2:dispatchTouchEvent action:"+ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch(action){
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG,"2:onInterceptTouchEvent action:ACTION_DOWN");
                break;
            //return true;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG,"2:onInterceptTouchEvent action:ACTION_MOVE");
                break;
            //return true;
            case MotionEvent.ACTION_UP:
                Log.d(TAG,"2:onInterceptTouchEvent action:ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG,"2:onInterceptTouchEvent action:ACTION_CANCEL");
                break;
        }
        return false;
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch(action){
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG,"2:onTouchEvent action:ACTION_DOWN");
                //return false;
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG,"2:onTouchEvent action:ACTION_MOVE");
                return false;
            //break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG,"2:onTouchEvent action:ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG,"2:onTouchEvent action:ACTION_CANCEL");
                break;
        }
        return true;
    }

    public void init(){
        Log.d(TAG, "init: ");
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.WHITE);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas){
        Log.d(TAG, "onDraw: ");
        super.onDraw(canvas);
        int circleNumber = getWidth()/(2*(gap + radius));
        for (int i = 1;i <= circleNumber + 1;i++){
            canvas.drawCircle((gap + radius) * (2 * i - 1),0,radius,mPaint);
            canvas.drawCircle((gap + radius) * (2 * i - 1),getHeight(),radius,mPaint);
        }
    }
}
