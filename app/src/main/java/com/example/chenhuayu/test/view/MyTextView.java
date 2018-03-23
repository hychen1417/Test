package com.example.chenhuayu.test.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by chenhuayu on 2018/3/13.
 */

public class MyTextView extends View {
    private Paint paint;

    public MyTextView(Context context) {
        super(context);
        init();
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.GRAY);
        paint.setTextSize(34);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        float textWidth = paint.measureText("正在录音");
        float textHeight = paint.getTextSize();
        canvas.translate((width - textWidth) / 2, (height - textHeight) / 2);
        canvas.drawText("正在录音", 0, textHeight, paint);


    }
}
