package com.example.chenhuayu.test;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;


public class WebViewActivity extends Activity {
    private WebView webView;
    private Button btnCallJs;
    private TextView textView;

    public String tag = "chy";

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);


        btnCallJs= (Button) findViewById(R.id.btn_call_js);
        textView= (TextView) findViewById(R.id.tv_js);
        // 进行全屏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        webView = (WebView) findViewById(R.id.webview);
        webView.loadUrl("file:///android_asset/a.html");
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new JSHook(), "hello");
//        webView.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                Log.d(tag, " url:" + url);
//                view.loadUrl(url);// 当打开新链接时，使用当前的 WebView，不会使用系统其他浏览器
//                return true;
//            }
//        });

        btnCallJs.setOnClickListener(new View.OnClickListener() {
            String info = "来自手机内的内容！！！";
            @Override
            public void onClick(View view) {
                webView.loadUrl("javascript:show('" + info + "')");
            }
        });
    }

    public class JSHook {
        @JavascriptInterface
        public void javaMethod(String p) {
            Log.d(String.valueOf(tag), "JSHook.JavaMethod() called! + " + p);
        }

        @JavascriptInterface
        public void showAndroid() {
            final String info = "来自手机内的内容！！！";
            WebViewActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    webView.loadUrl("javascript:show('" + info + "')");
                }
            });
        }

        public String getInfo() {
            return "获取手机内的信息！！";
        }
    }

    //设置回退
    //覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack(); //goBack()表示返回WebView的上一页面
            this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
