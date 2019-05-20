package com.example.chenhuayu.test.util;

import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.chenhuayu.test.view.ExpandClickAreaView;

public class TouchFactory {
    /**
     * 扩展垂直方向点击区域尺寸
     */
    private static final int EXT_V_SIZE = 200;
    /**
     * 扩展水平方向点击区域尺寸
     */
    private static final int EXT_X_SIZE = 200;

    public static View.OnTouchListener creatTouchListener() {
        return new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (expendTouchSize(v, event)) {
                    return true;
                }
                return false;
            }
        };
    }

    public static boolean expendTouchSize(View root, MotionEvent event) {
        if (root instanceof ExpandClickAreaView) {
            ImageView view = ((ExpandClickAreaView) root).getMyImageView();
            if (view != null && view.getVisibility() == View.VISIBLE) {
                Rect touchRect = new Rect();
                view.getGlobalVisibleRect(touchRect);

                int action = event.getAction();
                float x = event.getRawX();
                float y = event.getRawY();

                if ((y >= touchRect.top - EXT_V_SIZE) && (y <= touchRect.bottom + EXT_V_SIZE)) {
                    if ((x >= touchRect.left - EXT_X_SIZE) && (x <= touchRect.right + EXT_X_SIZE)) {
                        if (action == MotionEvent.ACTION_UP) {
                            view.performClick();
                        }
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
