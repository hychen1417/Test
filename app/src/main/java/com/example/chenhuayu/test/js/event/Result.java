package com.example.chenhuayu.test.js.event;

import android.content.Intent;

/**
 * Created by mahaifeng on 16/2/15.
 */
public class Result {
    public int requestCode;
    public int resultCode;
    public Intent intent;

    public Result(int requestCode, int resultCode, Intent intent) {
        this.requestCode = requestCode;
        this.resultCode = resultCode;
        this.intent = intent;
    }
}
