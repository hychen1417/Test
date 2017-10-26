//package com.example.chenhuayu.test.js.command;
//
//import android.net.Uri;
//import android.util.SparseArray;
//
//import com.example.chenhuayu.test.js.JsNativeBridge;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
///**
// * Created by mahaifeng on 16/2/15.
// */
//public abstract class BaseCommand {
//    static final String TAG = "jsCommand";
//    protected SparseArray<String> mMethodMap = new SparseArray<String>();
//
//    protected JsNativeBridge mJsNativeBridge;
//    protected static final String TRUE = "1";
//    protected static final String FALSE = "0";
//    private final String KEY_MEHTOD = "cb";
//
//    public abstract boolean exec(String url);
//    protected EventBus bus;
//    private boolean registered = false;
//
//    public void setJsNativeBridge(JsNativeBridge jsNativeBridge) {
//        this.mJsNativeBridge = jsNativeBridge;
//        if (!registered) {
////            RoboGuice.injectMembers(jsNativeBridge.getActivity(), this);
//            bus = EventBus.getDefault();
//            bus.register(this);
//            registered = true;
//        }
//
//    }
//
//    protected String getFunctionName(String url) {
//        try {
//            Uri uri = Uri.parse(url);
//            return uri.getQueryParameter(KEY_MEHTOD);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    protected String getAppKey(String url) {
//        try {
//            Uri uri = Uri.parse(url);
//            return uri.getQueryParameter("appId");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    protected void doDestory() {
//        mMethodMap.clear();
//    }
//
//    public String getJsCallBackParam(int rescode, JSONObject jsonObject) {
//        JSONObject callBack = jsonObject;
//        try {
//            if (callBack == null) {
//                callBack = new JSONObject();
//            }
//            callBack.putOpt("rescode", rescode);
//            return callBack.toString();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return String.format("{\"rescode\":%d}", JsBridge.FAIL);
//    }
//}
