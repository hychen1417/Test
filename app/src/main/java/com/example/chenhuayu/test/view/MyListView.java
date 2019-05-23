package com.example.chenhuayu.test.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

public class MyListView extends ListView {

    private float lastY;

    public MyListView(Context context) {
        this(context, null);
    }

    public MyListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            getParent().getParent().requestDisallowInterceptTouchEvent(true);
        } else if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            if (lastY > ev.getY()) {
                // 如果是向上滑动，且不能滑动了，则让ScrollView处理
                if (!canScrollList(1)) {
                    getParent().getParent().requestDisallowInterceptTouchEvent(false);
                    return false;
                }
            } else if (ev.getY() > lastY) {
                // 如果是向下滑动，且不能滑动了，则让ScrollView处理
                if (!canScrollList(-1)) {
                    getParent().getParent().requestDisallowInterceptTouchEvent(false);
                    return false;
                }
            }
        }
        lastY = ev.getY();
        return super.dispatchTouchEvent(ev);
    }
}
