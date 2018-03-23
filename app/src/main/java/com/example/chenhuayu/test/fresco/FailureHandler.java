package com.example.chenhuayu.test.fresco;

import android.view.View;

/**
 * Created by chenhuayu on 2018/3/8.
 */

public interface FailureHandler {
    void handle(String uri, View view, Throwable throwable);
}
