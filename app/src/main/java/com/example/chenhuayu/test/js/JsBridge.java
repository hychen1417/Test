//package com.example.chenhuayu.test.js;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.net.Uri;
//import android.text.TextUtils;
//import android.widget.Toast;
//
//import com.example.chenhuayu.test.R;
//import com.example.chenhuayu.test.js.command.BaseCommand;
//import com.example.chenhuayu.test.js.command.CommandManager;
//import com.example.chenhuayu.test.js.entity.AuthEntity;
//import com.tencent.smtt.sdk.WebView;
//
//import java.util.Arrays;
//import java.util.HashMap;
//
///**
// * Created by mahaifeng on 16/2/15.
// */
//public class JsBridge implements JsNativeBridge {
//    private Activity mActivity;
//    private WebView mWebView;
//    private CommandManager mCommandManager;
//    private JsViewListener mJsViewListener;
//
//    public JsBridge(Activity activity, WebView webView) {
//        this.mActivity = activity;
//        this.mWebView = webView;
//        mCommandManager = new CommandManager();
//    }
//
//    @Override
//    public boolean handleMessageFromJs(String url) {
//        try {
//            if (url.startsWith(WebView.SCHEME_TEL) || url.startsWith(WebView.SCHEME_MAILTO) || url.startsWith(WebView.SCHEME_GEO)) {
//                try {
//                    mActivity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
//                    return true;
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    return false;
//                }
//
//            }
//            if (hasAuth(url)) {
////                DxLog.d(this, "hasAuth");
//                BaseCommand command = mCommandManager.getCommand(url);
////                DxLog.d(this, "command=" + command);
//                if (command != null) {
////                    DxLog.d(this, "command=" + command.getClass().getSimpleName());
//                    command.setJsNativeBridge(this);
//                    statisticsData(url);
//                    return command.exec(url);
//                }
//            } else {
//                Toast.makeText(mActivity, mActivity.getString(R.string.auth_fail), Toast.LENGTH_SHORT).show();
//            }
//        } catch (Exception e) {
////            DxLog.d(this, "处理js链接地址失败:" + e.getMessage());
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    public boolean hasAuth(String url) {
//        if (Arrays.asList(CommandManager.WHITELISTURI).contains(url)
//                || url.startsWith(CommandManager.MICROAPPAUTH)) {
//            return true;
//        }
//        Uri uri = Uri.parse(url);
//        String appid = uri.getQueryParameter("appId");
//        String token = uri.getQueryParameter("token");
//        if (!TextUtils.isEmpty(appid) && !TextUtils.isEmpty(token)) {
//            AuthEntity authEntity = mAuthUrls.get(appid + token);
//            if (authEntity != null) {
//                return true;
//            }
//        } else {
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public Activity getActivity() {
//        return mActivity;
//    }
//
//    @Override
//    public WebView getWebView() {
//        return mWebView;
//    }
//
//    public void invokeJs(String callback, String param) {
////        DxLog.d(this, "++++++" + param);
//        final String content = "javascript:" + callback + "('" + param + "')";
//        if (mActivity.isFinishing()) {
//            return;
//        }
//        HashMap<String, Object> params = new HashMap<>();
//        params.put(LRConst.ReportInConst.VALUE1, mWebView.getUrl());
//        params.put(LRConst.ReportInConst.VALUE2, content);
//        LogRecordUtils.logEvent("jsApiCallBack", params);
//        if (TextUtils.isEmpty(callback)) {
//            return;
//        }
//        mWebView.post(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    mWebView.loadUrl(content);
//                } catch (Exception e) {
////                    DxLog.d(this, "invokeJs error message:" + e.getMessage());
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
//
//    @Override
//    public void setJsViewLister(JsViewListener jsViewLister) {
//        this.mJsViewListener = jsViewLister;
//    }
//
//    @Override
//    public JsViewListener getJsViewListener() {
//        return mJsViewListener;
//    }
//
//    private void statisticsData(String url) {
//        try {
//            Uri uri = Uri.parse(url);
//            String appId = uri.getQueryParameter("appId");
//            String token = uri.getQueryParameter("jsAccessToken");
//            String currentUrl = mWebView.getUrl();
//            String version = uri.getQueryParameter("version");
//
//            HashMap<String, Object> params = new HashMap<>();
//            params.put(LRConst.ReportInConst.VALUE1, appId);
//            params.put(LRConst.ReportInConst.VALUE2, token);
//            params.put(LRConst.ReportInConst.VALUE3, StringEscapeUtils.escapeJava(currentUrl));
//            params.put(LRConst.ReportInConst.VALUE4, version);
//            params.put(LRConst.ReportAttributeConst.TIME, System.currentTimeMillis());
//            params.put(LRConst.ReportInConst.VALUE5, BuildConfig.NEIXIN ? "neixin" : "daxiang");
//            params.put(LRConst.ReportInConst.CID, DxContext.getInstance().getCid());
//            params.put(LRConst.ReportInConst.VALUE6, StringEscapeUtils.escapeJava(url));
//            LogRecordUtils.logEvent("jsapi", params);
//        } catch (Exception e) {
//
//        }
//    }
//}
