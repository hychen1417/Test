package com.example.chenhuayu.test;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Window;

public class RequestFeatureActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        startActivity(new Intent(RequestFeatureActivity.this, MockApplicationActivity.class));
        Log.e("RequestFeatureActivity", "onCreate: ");
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_request_feature);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("RequestFeatureActivity", "onStop: " );
    }
}
