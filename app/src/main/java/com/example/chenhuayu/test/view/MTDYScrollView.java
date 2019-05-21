package com.example.chenhuayu.test.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;


public class MTDYScrollView extends ScrollView {

    private ScrollViewListener scrollViewListener = null;

    public MTDYScrollView(Context context) {
        this(context, null);
    }

    public MTDYScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MTDYScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, l, t, oldl, oldt);
        }
    }

    public interface ScrollViewListener {
        void onScrollChanged(MTDYScrollView ceshimy, int l, int t, int oldl, int oldt);
    }

}
