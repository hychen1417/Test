package com.example.chenhuayu.test.fresco;

import android.graphics.Bitmap;

/**
 * Created by chenhuayu on 2018/3/8.
 */

public class FrescoImageInfo {
    private int width;
    private int height;
    private boolean isAnim; // 是否动图
    private Bitmap previewBitmap; // 预览图，如果是动态图，可能为空
    private Object extra;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isAnim() {
        return isAnim;
    }

    public void setIsAnim(boolean isAnim) {
        this.isAnim = isAnim;
    }

    public Bitmap getPreviewBitmap() {
        return previewBitmap;
    }

    public void setPreviewBitmap(Bitmap previewBitmap) {
        this.previewBitmap = previewBitmap;
    }

    public Object getExtra() {
        return extra;
    }

    public void setExtra(Object extra) {
        this.extra = extra;
    }
}
