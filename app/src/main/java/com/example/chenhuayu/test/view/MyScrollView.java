package com.example.chenhuayu.test.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by chenhuayu on 2017/9/1.
 */

public class MyScrollView extends ScrollView{
    private static final String TAG = "MyScrollView";
    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2
//                , MeasureSpec.AT_MOST);
//        super.onMeasure(widthMeasureSpec, expandSpec);
//        int heightSpecMode = MeasureSpec.getMode(widthMeasureSpec);
//        Log.e(TAG, "onMeasure: "+  heightSpecMode);
//    }
    private boolean parentNeedDown(){
        //如果满足，返回true

        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.e("My", "onTouchEvent: 111" );
        return super.onTouchEvent(ev);
    }
}
