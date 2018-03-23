package com.example.chenhuayu.test.fresco;

import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by chenhuayu on 2018/3/8.
 * 可以设置监听事件来处理图片加载状态
 */

public interface DXImageLoaderListener {
    void onStart(String uri, View view);

    void onCompleted(String uri, View view, FrescoImageInfo imageInfo);

    void onFailure(String uri, View view, @Nullable FrescoImageInfo imageInfo, Object extra);
}
