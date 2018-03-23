package com.example.chenhuayu.test.fresco;

import android.support.annotation.Nullable;

import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.image.CloseableImage;

/**
 * Created by chenhuayu on 2018/3/8.
 * 用于返回图片,需要手动释放引用 返回clone的图片引用
 */

public interface FetchImageCallback {
    void onSuccess(@Nullable CloseableReference<CloseableImage> image);

    void onFailure(Object reason);
}
