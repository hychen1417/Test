package com.example.chenhuayu.test.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by chenhuayu on 2017/8/28.
 */

public class MyCardView extends LinearLayout {

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

    public void init(){

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.WHITE);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        int circleNumber = getWidth()/(2*(gap + radius));
        for (int i = 1;i <= circleNumber + 1;i++){
            canvas.drawCircle((gap + radius) * (2 * i - 1),0,radius,mPaint);
            canvas.drawCircle((gap + radius) * (2 * i - 1),getHeight(),radius,mPaint);
        }
    }
}
