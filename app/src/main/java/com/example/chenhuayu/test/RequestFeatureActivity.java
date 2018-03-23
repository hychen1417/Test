package com.example.chenhuayu.test;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Window;

public class RequestFeatureActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("RequestFeatureActivity", "onCreate: ");
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_request_feature);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("RequestFeatureActivity", "onStop: " );
    }
}
