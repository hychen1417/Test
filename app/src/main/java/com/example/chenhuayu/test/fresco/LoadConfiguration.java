package com.example.chenhuayu.test.fresco;

import android.view.View;

/**
 * Created by chenhuayu on 2018/3/8.
 */

public class LoadConfiguration {
    // 图片资源地址
    private String url;
    // 目标view
    private View view;
    // 图片加载状态回调
    private DXImageLoaderListener listener;
    // 默认图片 PlaceholderImage
    private int defaultImgRes;
    // 加载失败的图片
    private int failureImgRes;
    // 设置fade 默认为不展示fade效果
    private boolean fadeDisplay;
    // 是否默认打开动画 gif webp
    private boolean displayAnim;
    // 设置resize
    private FrescoResizeOptions biResizeOptions;
    // 设置圆角 要大于0
    private float mCornersRadius = -1;
    // 是否圆形图标 与圆角互斥
    private boolean mCircle;

    public LoadConfiguration(Builder builder) {
        url = builder.url;
        view = builder.view;
        listener = builder.listener;
        defaultImgRes = builder.defaultImgRes;
        failureImgRes = builder.failureImgRes;
        fadeDisplay = builder.fadeDisplay;
        displayAnim = builder.displayAnim;
        biResizeOptions = builder.biResizeOptions;
        mCornersRadius = builder.cornersRadius;
        mCircle = builder.circle;
    }

    public String getUrl() {
        return url;
    }

    public View getView() {
        return view;
    }

    public DXImageLoaderListener getListener() {
        return listener;
    }

    public int getDefaultImgRes() {
        return defaultImgRes;
    }

    public int getFailureImgRes() {
        return failureImgRes;
    }

    public boolean isFadeDisplay() {
        return fadeDisplay;
    }

    public boolean isDisplayAnim() {
        return displayAnim;
    }

    public boolean isCircle() {
        return mCircle;
    }

    public float getCornersRadius() {
        return mCornersRadius;
    }

    public FrescoResizeOptions getBiResizeOptions() {
        return biResizeOptions;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private String url;
        private View view;
        private DXImageLoaderListener listener;
        private int defaultImgRes;
        private int failureImgRes;
        private boolean fadeDisplay = true;
        private boolean displayAnim = true;
        private FrescoResizeOptions biResizeOptions;
        private float cornersRadius;
        private boolean circle = false;

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setView(View view) {
            this.view = view;
            return this;
        }

        public Builder setListener(DXImageLoaderListener listener) {
            this.listener = listener;
            return this;
        }

        public Builder setDefaultImgRes(int defaultImgRes) {
            this.defaultImgRes = defaultImgRes;
            return this;
        }

        public Builder setFailureImgRes(int failureImgRes) {
            this.failureImgRes = failureImgRes;
            return this;
        }
        public Builder setRoundAsCircle(boolean circle) {
            this.circle = circle;
            return this;
        }

        public Builder setCornersRadius(float cornersRadius) {
            this.cornersRadius = cornersRadius;
            return this;
        }

        public Builder setFadeDisplay(boolean fadeDisplay) {
            this.fadeDisplay = fadeDisplay;
            return this;
        }

        public Builder setDisplayAnim(boolean displayAnim) {
            this.displayAnim = displayAnim;
            return this;
        }

        public Builder setDXResizeOptions(FrescoResizeOptions biResizeOptions) {
            this.biResizeOptions = biResizeOptions;
            return this;
        }

        public LoadConfiguration create() {
            return new LoadConfiguration(this);
        }
    }
}
