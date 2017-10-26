package com.example.chenhuayu.test.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.chenhuayu.test.R;

/**
 * Created by chenhuayu on 2017/8/29.
 */

public class GuaGuaKaView extends View {
    String TAG = "MyView";
    private Bitmap mBgBitmap, mFgBitmap;
    private Paint mPaint;
    private Canvas mCanvas;
    private Path mPath;
    private float mBgBitmapWidth;
    private float LastX, dx, sumX;
    private boolean isFinish = false;
    boolean isdissallow = true;

    public GuaGuaKaView(Context context) {
        super(context);
        init();
    }

    public GuaGuaKaView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GuaGuaKaView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init() {
        //初始化透明画笔
        mPaint = new Paint();
        mPaint.setAlpha(0);
        mPaint.setXfermode(
                new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(50);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        //初始化路径
        mPath = new Path();
        //初始化底层图片
        mBgBitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.test);
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity)getContext()).getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidth=dm.widthPixels;
        if(mBgBitmap.getWidth()>screenWidth){
            mBgBitmap=Bitmap.createScaledBitmap(mBgBitmap, screenWidth, mBgBitmap.getHeight()*screenWidth/mBgBitmap.getWidth(), true);
        }
//        //获取底层宽度
        mBgBitmapWidth = mBgBitmap.getWidth();
        //创建顶层图片
        mFgBitmap = Bitmap.createBitmap(mBgBitmap.getWidth(),
                mBgBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        //创建顶层画布
        mCanvas = new Canvas(mFgBitmap);
        //顶层画布画上灰色
        mCanvas.drawColor(Color.GRAY);
    }
    //使用内部拦截法处理滑动冲突
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        if (isTouchPointInView(this, x, y)) {
            getParent().requestDisallowInterceptTouchEvent(true);
            return super.dispatchTouchEvent(event);
        }else {
            Log.e(TAG, "dispatchTouchEvent: isTouchPointInView" + false);
            return false;
        }
    }
    //(x,y)是否在view的区域内
    private boolean isTouchPointInView(View view, int x, int y) {
        if (view == null) {
            return false;
        }
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int left = location[0];
        int top = location[1];
        int right = left + view.getMeasuredWidth();
        int bottom = top + view.getMeasuredHeight();
        //view.isClickable() &&
        if (y >= top && y <= bottom && x >= left
                && x <= right) {
            return true;
        }
        return false;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "onTouchEvent: ACTION_DOWN");
                mPath.reset();
                mPath.moveTo(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "onTouchEvent: ACTION_MOVE");
                mPath.lineTo(event.getX(), event.getY());
                mCanvas.drawPath(mPath, mPaint);
                //重新绘制画面
                invalidate();

                dx = Math.abs(event.getX() - LastX);
                if (dx > 0) {
                    //监听左右滑
                    sumX += dx;
                }
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "onTouchEvent: ACTION_UP");
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            if (sumX > mBgBitmapWidth * 4) {
                                isFinish = true;
                                Thread.sleep(1000);
                                postInvalidate();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
                break;

        }
        LastX = event.getX();
        return true;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mBgBitmap, 0, 0, null);
        if (!isFinish) {
            canvas.drawBitmap(mFgBitmap, 0, 0, null);
        }
    }
}
