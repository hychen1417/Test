//package com.example.chenhuayu.test.js;
//
//import android.content.Context;
//import android.os.Build;
//import android.util.AttributeSet;
//import android.util.DisplayMetrics;
//
//import com.sankuai.xm.login.LoginConst;
//import com.sankuai.xmpp.BuildConfig;
//import com.sankuai.xmpp.DxContext;
//import com.sankuai.xmpp.controller.ControllerManager;
//import com.sankuai.xmpp.controller.appconfig.AppConfigController;
//import com.sankuai.xmpp.controller.microapp.event.SsoTrustRequest;
//import com.sankuai.xmpp.controller.microapp.event.SsoTrustResponse;
//import com.sankuai.xmpp.utils.DxLog;
//import com.sankuai.xmpp.utils.PackageUtils;
//import com.tencent.smtt.sdk.CookieManager;
//import com.tencent.smtt.sdk.WebSettings;
//import com.tencent.smtt.sdk.WebView;
//
//import org.greenrobot.eventbus.EventBus;
//import org.greenrobot.eventbus.Subscribe;
//import org.greenrobot.eventbus.ThreadMode;
//import org.json.JSONException;
//import org.json.JSONObject;
//
///**
// * Created by mahaifeng on 16/2/16.
// */
//public class CustomWebView extends WebView {
//    public final static String HEADER_CLIENT_AGENT_PREFIX = "XM/";
//    private boolean mSsoTrustRunning;
//    private AppConfigController appConfigController = (AppConfigController) ControllerManager.getInstance().getController(AppConfigController.class);
//
//    public CustomWebView(Context context) {
//        super(context);
//        init(context);
//    }
//
//    public boolean isSsoTrustRunning() {
//        return mSsoTrustRunning;
//    }
//
//    public CustomWebView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        init(context);
//    }
//
//    public void updateCacheMode(int cacheMode) {
//        final WebSettings settings = getSettings();
//        settings.setCacheMode(cacheMode);
//    }
//
//    private void init(Context context) {
//        if (Build.VERSION.SDK_INT >= 19 && BuildConfig.DEBUG) {
//            WebView.setWebContentsDebuggingEnabled(true);
//        }
//        final WebSettings settings = getSettings();
//        settings.setJavaScriptEnabled(true);
//        settings.setDefaultTextEncodingName("utf-8");
//        //多点触控
//        settings.setDefaultZoom(getZoomDensity());
//        //视图优化
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
//        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            settings.setMixedContentMode(WebSettings.LOAD_NORMAL);
//        }
//        settings.setDomStorageEnabled(true);
//        settings.setAppCacheEnabled(true);
//        //双击放大缩小
//        settings.setUseWideViewPort(true);
//        settings.setLoadWithOverviewMode(true);
//        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
//        //定位支持
//        settings.setGeolocationEnabled(true);
//        // This next one is crazy. It's the DEFAULT location for your app's cache
//        // But it didn't work for me without this line.
//        // UPDATE: no hardcoded path. Thanks to Kevin Hawkins
//        settings.setAppCachePath(context.getCacheDir().getAbsolutePath());
//
//        // Set cache size to 8 mb by default. should be more than enough
//        settings.setAppCacheMaxSize(1024 * 1024 * 8);
//        String ua = settings.getUserAgentString() + " " + HEADER_CLIENT_AGENT_PREFIX + PackageUtils.getVersionName(context);
//        settings.setUserAgentString(ua);
//
//        settings.setSavePassword(false);
//        settings.setSaveFormData(false);
//
//        CookieManager cookieManager = CookieManager.getInstance();
//        if (!cookieManager.acceptCookie()) {
//            cookieManager.setAcceptCookie(true);
//        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            cookieManager.setAcceptThirdPartyCookies(this, true);
//            settings.setMixedContentMode(WebSettings.LOAD_NORMAL);
//        }
//
//        DxLog.d("cid", "" + DxContext.getInstance().getCid());
//        if (DxContext.getInstance().getCid() == LoginConst.MEITUAN_CID && WebViewUtils.isNeedInvokeSsoTrust()) {
//            mSsoTrustRunning = true;
//            EventBus.getDefault().post(new SsoTrustRequest(DxContext.getInstance().getMidId()));
//        }
//        EventBus.getDefault().register(this);
//
//        WebViewUtils.setCookie(cookieManager, "http://.neixin.cn");
//    }
//
//    @Override
//    protected void onDetachedFromWindow() {
//        super.onDetachedFromWindow();
//        EventBus.getDefault().unregister(this);
//    }
//
//    private WebSettings.ZoomDensity getZoomDensity() {
//        int screenDensity = getResources().getDisplayMetrics().densityDpi;
//        WebSettings.ZoomDensity zoomDensity = WebSettings.ZoomDensity.MEDIUM;
//        switch (screenDensity) {
//            case DisplayMetrics.DENSITY_LOW:
//                zoomDensity = WebSettings.ZoomDensity.CLOSE;
//                break;
//            case DisplayMetrics.DENSITY_MEDIUM:
//                zoomDensity = WebSettings.ZoomDensity.MEDIUM;
//                break;
//            case DisplayMetrics.DENSITY_HIGH:
//                zoomDensity = WebSettings.ZoomDensity.FAR;
//                break;
//        }
//        return zoomDensity;
//    }
//
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void ssoTrust(SsoTrustResponse response) {
//        DxLog.d(this, "SsoTrustResponse:" + response.data);
//        mSsoTrustRunning = false;
//        if (response.data != null) {
//            try {
//                JSONObject jsonObject = new JSONObject(response.data.toString());
//                WebViewUtils.plantSsoCookie(jsonObject.getJSONObject("data"));
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//
//    }
//}
