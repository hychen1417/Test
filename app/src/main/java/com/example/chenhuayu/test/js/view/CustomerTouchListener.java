//package com.example.chenhuayu.test.js.view;
//
///**
// * Created by mahaifeng on 17/2/8.
// */
//
//import android.content.Context;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.View.OnTouchListener;
//
//import com.sankuai.xmpp.utils.DxLog;
//
//import java.lang.reflect.Field;
//
//public class CustomerTouchListener implements OnTouchListener {
//    private int lastX;
//    private int lastY;
//    private int screenWidth;
//    private int screenHeight;
//    private int realHeight;
//    private Context mContext;
//    private int mStartX;
//    private int mStartY;
//    private int mDistance = 10;
//
//    public CustomerTouchListener(Context context) {
//        screenWidth = context.getResources().getDisplayMetrics().widthPixels;
//        screenHeight = context.getResources().getDisplayMetrics().heightPixels;
//        this.mContext = context;
//    }
//
//    public void setOffsetY(int offsetY) {
//        offsetY = getStatusBarHeight() + offsetY;
//        realHeight = screenHeight - offsetY;
//        DxLog.d(this, "offsetY" + offsetY);
//    }
//
//    /**
//     * 用于获取状态栏的高度
//     *
//     * @return 返回状态栏高度的像素值
//     */
//    private int getStatusBarHeight() {
//        int statusBarHeight = 0;
//        try {
//            Class<?> c = Class.forName("com.android.internal.R$dimen");
//            Object o = c.newInstance();
//            Field field = c.getField("status_bar_height");
//            int x = (Integer) field.get(o);
//            statusBarHeight = mContext.getResources().getDimensionPixelSize(x);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        DxLog.d(this, "=statusBarHeight" + statusBarHeight);
//        return statusBarHeight;
//    }
//
//    @Override
//    public boolean onTouch(View v, MotionEvent event) {
//        {
//            //获取Action
//
//            int ea = event.getAction();
//            switch (ea) {
//                case MotionEvent.ACTION_DOWN:   //按下
//                    lastX = (int) event.getRawX();
//                    lastY = (int) event.getRawY();
//                    mStartX = (int) event.getRawX();
//                    mStartY = (int) event.getRawY();
//                    break;
//                /**
//                 * layout(l,t,r,b)
//                 * l  Left position, relative to parent
//                 t  Top position, relative to parent
//                 r  Right position, relative to parent
//                 b  Bottom position, relative to parent
//                 * */
//                case MotionEvent.ACTION_MOVE:  //移动
//                    //移动中动态设置位置
//                    int dx = (int) event.getRawX() - lastX;
//                    int dy = (int) event.getRawY() - lastY;
//                    int left = v.getLeft() + dx;
//                    int top = v.getTop() + dy;
//                    int right = v.getRight() + dx;
//                    int bottom = v.getBottom() + dy;
//                    if (left < 0) {
//                        left = 0;
//                        right = left + v.getWidth();
//                    }
//                    if (right > screenWidth) {
//                        right = screenWidth;
//                        left = right - v.getWidth();
//                    }
//                    if (top < 0) {
//                        top = 0;
//                        bottom = top + v.getHeight();
//                    }
//                    if (bottom > realHeight) {
//                        bottom = realHeight;
//                        top = bottom - v.getHeight();
//                    }
//                    DxLog.d(this, left + "|" + top + "|" + right + "|" + bottom);
//                    DxLog.d(this, realHeight + "|" + v.getHeight());
//                    v.layout(left, top, right, bottom);
//                    //将当前的位置再次设置
//                    lastX = (int) event.getRawX();
//                    lastY = (int) event.getRawY();
//                    return true;
//                case MotionEvent.ACTION_UP:   //脱离
//                    if (Math.abs(mStartX - lastX) > mDistance || Math.abs(mStartY - lastY) > mDistance) {
//                        return true;
//                    }
//                    break;
//            }
//            return false;
//        }
//    }
//
//    ;
//}
