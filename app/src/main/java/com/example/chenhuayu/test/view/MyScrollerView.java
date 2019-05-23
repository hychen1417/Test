package com.example.chenhuayu.test.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ScrollView;

public class MyScrollerView extends ScrollView {
    private ListView listView;
    private boolean shouldIntecept = false;

    public MyScrollerView(Context context) {
        this(context, null);
    }

    public MyScrollerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyScrollerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        if (listView != null && checkArea(listView, ev)) {
//            return false;
//        }
//        if (listView != null) {
//            listView.setOnScrollListener(new AbsListView.OnScrollListener() {
//                @Override
//                public void onScrollStateChanged(AbsListView view, int scrollState) {
//
//                }
//
//                @Override
//                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//                    if (firstVisibleItem == 0) {
//                        View firstVisibleItemView = listView.getChildAt(0);
//                        if (firstVisibleItemView != null && firstVisibleItemView.getTop() == 0) {
//                            Log.d("ListView", "##### 滚动到顶部 #####");
//
//                        }
//                    } else if ((firstVisibleItem + visibleItemCount) == totalItemCount) {
//                        View lastVisibleItemView = listView.getChildAt(listView.getChildCount() - 1);
//                        if (lastVisibleItemView != null && lastVisibleItemView.getBottom() == listView.getHeight()) {
//                            Log.d("ListView", "##### 滚动到底部 ######");
//                        }
//                    }
//                }
//            });
//        }
//        return super.onInterceptTouchEvent(ev);
//    }

    private boolean checkArea(ListView v, MotionEvent event) {
        float x = event.getRawX();
        float y = event.getRawY();
        int[] locate = new int[2];
        v.getLocationOnScreen(locate);
        int l = locate[0];
        int r = l + v.getWidth();
        int t = locate[1];
        int b = t + v.getHeight();
        if (l < x && x < r && t < y && y < b) {
            return true;
        }
        return false;
    }

    public void setListView(ListView listView) {
        this.listView = listView;
    }

    public void setShouldIntecept(boolean shouldIntecept) {
        this.shouldIntecept = shouldIntecept;
    }
}
