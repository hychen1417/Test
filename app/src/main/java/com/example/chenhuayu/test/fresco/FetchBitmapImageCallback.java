package com.example.chenhuayu.test.fresco;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;

/**
 * Created by chenhuayu on 2018/3/8.
 */

public interface FetchBitmapImageCallback {
    void onSuccess(@Nullable Bitmap bitmap);

    void onFailure(Object reason);
}
