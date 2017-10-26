//package com.example.chenhuayu.test.js;
//
//import android.app.Activity;
//
//
//import com.example.chenhuayu.test.js.entity.AuthEntity;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created by mahaifeng on 16/2/15.
// */
//public interface JsNativeBridge {
//    int SUCCESS = 0;
//    int FAIL = 1;
//
//    Map<String, AuthEntity> mAuthUrls = new HashMap<>();
//
//    boolean handleMessageFromJs(String url);
//
//    Activity getActivity();
//
//    com.tencent.smtt.sdk.WebView getWebView();
//
//    void invokeJs(String callback, String param);
//
//    void setJsViewLister(JsViewListener jsViewLister);
//
//    JsViewListener getJsViewListener();
//}
