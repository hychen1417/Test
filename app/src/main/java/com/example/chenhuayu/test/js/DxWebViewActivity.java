//
//package com.example.chenhuayu.test.js;
//
//import android.annotation.TargetApi;
//import android.content.ActivityNotFoundException;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.res.Configuration;
//import android.graphics.Bitmap;
//import android.graphics.PixelFormat;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.view.KeyEvent;
//import android.view.View;
//import android.view.WindowManager;
//import android.widget.LinearLayout;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.meituan.rhino.sdk.MBoxAgent;
//import com.sankuai.xm.appbase.dxbase.ChatType;
//import com.sankuai.xm.appbase.dxbase.DxId;
//import com.sankuai.xm.login.util.LogRecordUtils;
//import com.sankuai.xm.tools.utils.FileUtils;
//import com.sankuai.xm.tools.utils.ReflectUtils;
//import com.sankuai.xm.uikit.dialog.DownToUpSlideDialog;
//import com.sankuai.xm.uikit.dialog.XMAlertDialog;
//import com.sankuai.xm.uikit.titlebar.LeftDoubleRightimageTitleBar;
//import com.sankuai.xmpp.BaseFragmentActivity;
//import com.sankuai.xmpp.ChatActivity;
//import com.sankuai.xmpp.ChatListFragment;
//import com.sankuai.xmpp.DxContext;
//import com.sankuai.xmpp.PubChatActivity;
//import com.sankuai.xmpp.R;
//import com.sankuai.xmpp.controller.ControllerManager;
//import com.sankuai.xmpp.controller.appconfig.AppConfigController;
//import com.sankuai.xmpp.controller.microapp.MicroAppsController;
//import com.sankuai.xmpp.controller.microapp.entity.MicroAppInfo;
//import com.sankuai.xmpp.js.command.JsInvokePFeatureCommand;
//import com.sankuai.xmpp.js.entity.Menu;
//import com.sankuai.xmpp.js.event.OnDestroyEvent;
//import com.sankuai.xmpp.js.event.OnPauseEvent;
//import com.sankuai.xmpp.js.event.OnResumeEvent;
//import com.sankuai.xmpp.js.event.Result;
//import com.sankuai.xmpp.js.view.CustomerTouchListener;
//import com.sankuai.xmpp.sdk.entity.message.MsgType;
//import com.sankuai.xmpp.sdk.entity.message.messagebody.BaseMessageBody;
//import com.sankuai.xmpp.sdk.entity.message.messagebody.DxLinkInfo;
//import com.sankuai.xmpp.sdk.entity.message.messagebody.DxPubNoticeInfo;
//import com.sankuai.xmpp.sdk.entity.message.messagebody.DxTextInfo;
//import com.sankuai.xmpp.transmit.SelectPeersActivity;
//import com.sankuai.xmpp.utils.ClipboardUtils;
//import com.sankuai.xmpp.utils.DxLog;
//import com.sankuai.xmpp.utils.DxUriUtils;
//import com.sankuai.xmpp.views.MenuDialog;
//import com.tencent.smtt.export.external.interfaces.GeolocationPermissionsCallback;
//import com.tencent.smtt.export.external.interfaces.JsPromptResult;
//import com.tencent.smtt.export.external.interfaces.SslError;
//import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
//import com.tencent.smtt.export.external.interfaces.WebResourceResponse;
//import com.tencent.smtt.sdk.DownloadListener;
//import com.tencent.smtt.sdk.ValueCallback;
//import com.tencent.smtt.sdk.WebChromeClient;
//import com.tencent.smtt.sdk.WebView;
//import com.tencent.smtt.sdk.WebViewClient;
//
//import org.greenrobot.eventbus.EventBus;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//
///**
// * Created with IntelliJ IDEA.
// * User: luodandan
// * Date: 13-4-27
// * Time: 上午10:43
// * To change this template use File | Settings | File Templates.
// */
//
//public class DxWebViewActivity extends BaseFragmentActivity implements DownloadListener, JsViewListener {
//    private static final String TAG = "DxWebViewActivity ";
//    public final static String KEY_TITLE = "title";
//    public final static String KEY_LINK = "link_url";
//    public final static String KEY_BODY = "body";
//    public final static String KEY_BODY_TYPE = "body_type";
//    public final static String KEY_EXTEND = "extend";
//    public final static String KEY_WHITEBAR = "key_whitebar";
//    public final static String KEY_SHOW_TITLE = "show_title";
//    public final static String KEY_COMEFROM = "comefrom";
//    public final static String KEY_CONTACTOR = "contactor";
//    public final static String KEY_MICRO_APP_ID = "microappid";
//
//    public final static String VALUE_COMEFROM_WORKBENCH = "workbench";
//    private final static long mLoadingDelay = 5 * 1000;
//    private String schemeUrl;
//    private MenuDialog downToUpSlideDialog;
//    protected LeftDoubleRightimageTitleBar titleBar;
//    private ProgressBar progressBar;
//    //    private String currentIconUrl;
//    private List<Menu> mMenus;
//    private boolean mShowTitle;
//    private boolean isShowTitleBar = true;
//    private boolean isShowProgress = true;
//
//    private JsNativeBridge mJsBridge;
//    private AppConfigController appConfigController = (AppConfigController) ControllerManager.getInstance().getController(AppConfigController.class);
//    public MicroAppsController microAppsController = (MicroAppsController) ControllerManager.getInstance().getController(MicroAppsController.class);
//
//    private LinearLayout mFloatView;
//    private CustomerTouchListener mCustomerTouchListener;
//    private String appId;
//
//    private CustomWebView mWebView;
//
//    private LinearLayout mErrorContainerView;
//    private TextView mErrorTip;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        getWindow().setFormat(PixelFormat.TRANSLUCENT);
//        super.onCreate(savedInstanceState);
//        final String title = getIntent().getStringExtra(KEY_TITLE);
//        mShowTitle = getIntent().getBooleanExtra(KEY_SHOW_TITLE, true);
//        String currentUrl = getIntent().getStringExtra(KEY_LINK);
//        if (getIntent().getData() != null) {
//            Uri uri = getIntent().getData();
//            if (!TextUtils.isEmpty(uri.getQueryParameter(KEY_LINK))) {
//                currentUrl = uri.getQueryParameter(KEY_LINK);
//            }
//        }
//        if (TextUtils.isEmpty(currentUrl)) {
//            finish();
//            return;
//        }
//        if (MBoxAgent.getInstance().isSupportedShareLink(currentUrl)) {
//            MBoxAgent.getInstance().startShareLinkActivity(this, currentUrl);
//            finish();
//            return;
//        }
//        if (currentUrl != null) {
//            currentUrl = WebViewUtils.processLink(currentUrl);
//        }
//        final BaseMessageBody baseMessageBody = (BaseMessageBody) getIntent().getSerializableExtra(KEY_BODY);
//        final int bodyType = getIntent().getIntExtra(KEY_BODY_TYPE, 0);
//        schemeUrl = getString(R.string.internal_uri_scheme);
//        //title bar
//        titleBar = new LeftDoubleRightimageTitleBar(this);
//
//        titleBar.onRreActivityLayout();
//        setContentView(R.layout.activity_webview);
//        titleBar.onPostActivityLayout();
//        Uri uri = Uri.parse(currentUrl);
////        TextView textView = (TextView) findViewById(R.id.tv_provider);
////        textView.setText(getString(R.string.webview_provider, uri.getHost()));
//        String showTitle = uri.getQueryParameter("showTitle");
//        if ("0".equals(showTitle)) {
//            titleBar.getMiddleView().setVisibility(View.GONE);
//        }
//        if (!TextUtils.isEmpty(title)) {
//            updateTitle(title);
//        }
//        if (!getIntent().getBooleanExtra("isShowDelete", true)) {
//            titleBar.hideSecondLeftView();
//        } else {
//            titleBar.showSecondLeftView();
//        }
//        findViewById(R.id.progress_bar).setVisibility(View.GONE);
//        titleBar.setOnFirstLeftViewClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                back();
//            }
//        });
//
//        titleBar.setOnSecondLeftViewClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
//        initView(currentUrl);
//        if (!TextUtils.isEmpty(currentUrl)) {
//            downToUpSlideDialog = new MenuDialog(this);
//            downToUpSlideDialog.initItem(getResources().getStringArray(R.array.webpage_dialog_items_with_transmit));
//            downToUpSlideDialog.setOnMenuDialogItemlickListener(new DownToUpSlideDialog.OnMenuDialogItemClickListener() {
//                @Override
//                public void onMenuDialogItemClickListener(int which) {
//                    downToUpSlideDialog.dismiss();
//                    switch (which) {
//                        case -3:
//                        case -2:
//                        case -1:
//                            int index = -1 * which - 1;
//                            if (mMenus.size() > index) {
//                                mJsBridge.invokeJs(mMenus.get(index).cb, String.format("{\"result\":\"%s\"}", mMenus.get(index).id));
//                            }
//                            break;
//                        case 0:
//                            Intent intentForward = new Intent(DxWebViewActivity.this, SelectPeersActivity.class);
//                            intentForward.setAction(SelectPeersActivity.ACTION_FORWARD);
//                            Bundle mBundle = new Bundle();
//                            if (bodyType == MsgType.MSG_TYPE_PUB_NOTICE) {
//                                mBundle.putInt("msg_type", MsgType.MSG_TYPE_LINK);
//                                DxPubNoticeInfo dxPubNoticeInfo = (DxPubNoticeInfo) baseMessageBody;
//                                DxLinkInfo dxLinkInfo = new DxLinkInfo();
//                                dxLinkInfo.title = dxPubNoticeInfo.title;
//                                dxLinkInfo.image = dxPubNoticeInfo.image;
//                                dxLinkInfo.content = dxPubNoticeInfo.content;
//                                dxLinkInfo.link = dxPubNoticeInfo.link;
//                                mBundle.putSerializable(SelectPeersActivity.content, dxLinkInfo);
//                            } else if (baseMessageBody != null) {
//                                mBundle.putSerializable(SelectPeersActivity.content, baseMessageBody);
//                                if (baseMessageBody instanceof DxLinkInfo) {
//                                    DxLinkInfo linkInfo = (DxLinkInfo) baseMessageBody;
//                                    if (titleBar.getTitle() != null) {
//                                        linkInfo.setTitle(titleBar.getTitle());
//                                    }
//
//                                    if (!TextUtils.isEmpty(mWebView.getUrl())) {
//                                        linkInfo.setLink(mWebView.getUrl());
//                                        linkInfo.setContent(mWebView.getUrl());
//                                    }
//
//                                    if (TextUtils.isEmpty(linkInfo.getImage())) {
//                                        linkInfo.setImage(getFaviconUrl());
//                                    }
//                                }
//                                mBundle.putInt("msg_type", bodyType);
//                            } else {
//                                if (titleBar.getTitle() != null) {
//                                    DxLinkInfo dxLinkInfo = new DxLinkInfo();
//                                    mBundle.putInt("msg_type", MsgType.MSG_TYPE_LINK);
//                                    dxLinkInfo.setLink(mWebView.getUrl());
//                                    dxLinkInfo.setImage(getFaviconUrl());
//                                    dxLinkInfo.setTitle(titleBar.getTitle());
//                                    mBundle.putSerializable(SelectPeersActivity.content, dxLinkInfo);
//                                } else {
//                                    DxTextInfo textInfo = new DxTextInfo();
//                                    textInfo.setText(mWebView.getUrl());
//                                    mBundle.putSerializable(SelectPeersActivity.content, textInfo);
//                                }
//                            }
//                            if (getIntent().getSerializableExtra(KEY_EXTEND) != null) {
//                                mBundle.putSerializable("extend", getIntent().getSerializableExtra(KEY_EXTEND));
//                            }
//                            intentForward.putExtras(mBundle);
//                            startActivity(intentForward);
//                            break;
//                        case 1:
//                            ClipboardUtils.setText(DxWebViewActivity.this, mWebView.getUrl());
//                            Toast.makeText(DxWebViewActivity.this, R.string.webpage_copy_link_prompt, Toast.LENGTH_SHORT).show();
//                            break;
//                        case 2:
//                            try {
//                                Uri uri = Uri.parse(mWebView.getUrl());
//                                Intent intentBrowser = new Intent(Intent.ACTION_VIEW, uri);
//                                startActivity(intentBrowser);
//                            } catch (ActivityNotFoundException e) {
//                            }
//                            break;
//                        case 3:
//                            // 刷新
//                            mWebView.loadUrl(mWebView.getUrl());
//                            break;
//                    }
//                }
//            });
//            titleBar.setRightImage(R.drawable.ic_title_more);
//            if (!(currentUrl.startsWith("http") || currentUrl.startsWith("https"))) {
//                titleBar.hideImageButton();
//            } else {
//                if (DxUriUtils.isClosedDxMore(currentUrl)) {
//                    titleBar.hideImageButton();
//                } else {
//                    titleBar.showImageButton();
//                }
//
//                titleBar.setOnRightClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        downToUpSlideDialog.show();
//                    }
//                });
//            }
//        } else {
//            titleBar.hideImageButton();
//        }
//
//        initCustomer();
//
//        mErrorContainerView = (LinearLayout) findViewById(R.id.errorContainer);
//        mErrorTip = (TextView) findViewById(R.id.tip);
//        findViewById(R.id.btn_tryagain).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mErrorContainerView.setVisibility(View.GONE);
//                mWebView.reload();
//            }
//        });
//    }
//
//    private String getFaviconUrl() {
//        return "http://" + Uri.parse(mWebView.getUrl()).getHost() + "/favicon.ico";
//    }
//
//    private void initCustomer() {
//        String comeFrom = getIntent().getStringExtra(KEY_COMEFROM);
//        if (VALUE_COMEFROM_WORKBENCH.equals(comeFrom)) {
//            appId = getIntent().getStringExtra(KEY_MICRO_APP_ID);
//            LogRecordUtils.asyncLogEventStart("ui_stay_in_micro_app", appId);
//            final MicroAppInfo.Contractor contractor = MicroAppInfo.getContractor(getIntent().getStringExtra(KEY_CONTACTOR));
//            if (contractor != null) {
//                if (contractor instanceof MicroAppInfo.User) {
//                    MicroAppInfo.User user = (MicroAppInfo.User) contractor;
//                    if (user.userId == 0) {
//                        return;
//                    }
//                } else if (contractor instanceof MicroAppInfo.Pub) {
//                    MicroAppInfo.Pub pub = (MicroAppInfo.Pub) contractor;
//                    if (pub.pubId == 0) {
//                        return;
//                    }
//                } else if (contractor instanceof MicroAppInfo.Phone) {
//                    MicroAppInfo.Phone phone = (MicroAppInfo.Phone) contractor;
//                    if (TextUtils.isEmpty(phone.number)) {
//                        return;
//                    }
//                }
//            } else {
//                return;
//            }
//
//            if (mCustomerTouchListener == null) {
//                mFloatView = (LinearLayout) findViewById(R.id.floatview);
//                mFloatView.setVisibility(View.VISIBLE);
//                mCustomerTouchListener = new CustomerTouchListener(this);
//                mFloatView.setOnTouchListener(mCustomerTouchListener);
//                mFloatView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        if (contractor != null) {
//                            if (contractor instanceof MicroAppInfo.User) {
//                                MicroAppInfo.User user = (MicroAppInfo.User) contractor;
//                                Intent intent = new Intent();
//                                intent.setClass(DxWebViewActivity.this, ChatActivity.class);
//                                DxId dxId = new DxId(user.userId, 0, 0, ChatType.chat, DxContext.APPID);
//                                intent.putExtra(ChatListFragment.DXID, dxId);
//                                intent.putExtra("backUrl", "back");
//                                startActivity(intent);
//                            } else if (contractor instanceof MicroAppInfo.Pub) {
//                                MicroAppInfo.Pub pub = (MicroAppInfo.Pub) contractor;
//                                Intent intent = new Intent();
//                                intent.setClass(DxWebViewActivity.this, PubChatActivity.class);
//                                DxId dxId = new DxId(pub.pubId, 0, 0, ChatType.pubchat, DxContext.APPID);
//                                intent.putExtra(ChatListFragment.DXID, dxId);
//                                intent.putExtra("backUrl", "back");
//                                startActivity(intent);
//                            } else if (contractor instanceof MicroAppInfo.Phone) {
//                                MicroAppInfo.Phone phone = (MicroAppInfo.Phone) contractor;
//                                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone.number));
//                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                startActivity(intent);
//                            }
//                        }
//
//                    }
//                });
//            } else {
//                if (titleBar.isTitleBarShowing()) {
//                    titleBar.getTitleContainer().measure(0, 0);
//                    mCustomerTouchListener.setOffsetY(titleBar.getTitleContainer().getMeasuredHeight());
//                } else {
//                    mCustomerTouchListener.setOffsetY(0);
//                }
//            }
//        }
//    }
//
//    @Override
//    public void updateMenu(List<Menu> menus) {
//        if (mMenus == null) {
//            mMenus = new ArrayList<Menu>(MenuDialog.MAX_NUMBER_OF_EXTRA_MENU);
//        }
//        mMenus.clear();
//        for (int i = 0; i < menus.size(); i++) {
//            Menu menu = menus.get(i);
//            downToUpSlideDialog.showExtraItem(i, menu.name);
//            if (i == MenuDialog.MAX_NUMBER_OF_EXTRA_MENU) {
//                break;
//            }
//        }
//
//        mMenus.addAll(menus);
//
//    }
//
//    @Override
//    public void updateTitle(String title) {
//        if (mShowTitle) {
//            titleBar.setTitle(title);
//        }
//    }
//
//    private void initView(final String url) {
//        mWebView = (CustomWebView) findViewById(R.id.webview);
//        String cacheMode = Uri.parse(url).getQueryParameter("cacheMode");
//        if (!TextUtils.isEmpty(cacheMode)) {
//            mWebView.updateCacheMode(Integer.valueOf(cacheMode));
//        }
//        mWebView.setDownloadListener(this);
//        final XMWebViewClient client = new XMWebViewClient();
//        mWebView.setWebViewClient(client);
////        webView.addJavascriptInterface(new HandIcon(), "handIcon");
//        mWebView.setWebChromeClient(new XMWebChromeClient());
//        long delayTime = 0;
//        if (mWebView.isSsoTrustRunning()) {
//            delayTime = mLoadingDelay;
//        }
//        DxLog.d(this, "delayTime:" + delayTime);
//        mWebView.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if (isFinishing()) {
//                    return;
//                }
//                try {
//                    DxLog.d(this, "postDelayed:" + url);
//                    if (!TextUtils.isEmpty(url)) {
//                        if (url.startsWith(schemeUrl)) {
//                            client.shouldOverrideUrlLoading(mWebView, url);
//                            finish();
//                        } else {
//                            showTitleBarAndToolBar(url);
//                            if (!TextUtils.isEmpty(url) && appConfigController.isInAuthWhiteList(url)) {//查看是否在认证白名单中
//                                WebViewUtils.writeCookie(url);
//                            }
//
//                            mWebView.loadUrl(url);
//                        }
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    DxLog.d(this, "load exception =" + e.getMessage());
//                }
//            }
//        }, delayTime);
//        mJsBridge = new JsBridge(this, mWebView);
//        mJsBridge.setJsViewLister(this);
//    }
//
//    private void showTitleBarAndToolBar(String url) {
//        try {
//            DxLog.d(this, url);
//            Uri uri = Uri.parse(url);
//            String titleBarVisible = uri.getQueryParameter("titlebarVisible");
//            if (titleBarVisible != null) {
//                this.isShowTitleBar = titleBarVisible.equals("1");
//            }
//            String progressVisiable = uri.getQueryParameter("showProgress");
//            if (progressVisiable != null) {
//                this.isShowProgress = progressVisiable.equals("1");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        titleBar.setTilteBarShowing(isShowTitleBar);
//        progressBar.setVisibility(isShowProgress ? View.VISIBLE : View.GONE);
//        initCustomer();
//    }
//
//
//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        } else {
//            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        }
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        WebView webView = (WebView) findViewById(R.id.webview);
//        if (webView != null) {
//            // WebView的onResume方法，在Android3.0之前是hide的
//            ReflectUtils.reflectInvokeNoException(webView, "onResume", null, null);
//        }
//        EventBus.getDefault().post(new OnResumeEvent());
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        WebView webView = (WebView) findViewById(R.id.webview);
//        if (webView != null) {
//            webView.onPause();
//        }
//        EventBus.getDefault().post(new OnPauseEvent());
//    }
//
//    @Override
//    protected void onDestroy() {
//        // 下面的操作，据说可以解决“BadTokenException: Unable to add window”的问题
//        WebView webView = (WebView) findViewById(R.id.webview);
//        if (webView != null) {
////            ((FrameLayout) findViewById(R.id.frameLayout_webview)).removeView(webView);
//            webView.destroy();
//        }
//        String comeFrom = getIntent().getStringExtra(KEY_COMEFROM);
//        if (VALUE_COMEFROM_WORKBENCH.equals(comeFrom)) {
//            if (appId != null && TextUtils.isDigitsOnly(appId)) {
//                HashMap<String, Object> params = new HashMap<>();
//                params.put("microappid", Integer.parseInt(appId));
//                LogRecordUtils.asyncLogEventEnd("ui_stay_in_micro_app", appId, params);
//            }
//        }
//        super.onDestroy();
//        EventBus.getDefault().post(new OnDestroyEvent());
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
//        EventBus.getDefault().post(new Result(requestCode, resultCode, intent));
//    }
//
//    private void disableOperations() {
//        titleBar.setRightEnabled(false);
//    }
//
//
//    private void enableOperations() {
//        titleBar.setRightEnabled(true);
//    }
//
//    @Override
//    public void onDownloadStart(final String url, String userAgent, String contentDisposition, String mimeType, long contentLength) {
//        if (!TextUtils.isEmpty(url) && url.endsWith("m3u8") && url.startsWith("http")) {
//            return;
//        }
//        if (isFinishing()) {
//            return;
//        }
//        final WebView webView = (WebView) findViewById(R.id.webview);
//        final String name = WebViewUtils.guessFileName(url, contentDisposition, mimeType);
//        new XMAlertDialog.Builder(this)
//                .setTitle(R.string.download_confirm_title)
//                .setMessage(String.format("文件名: %1$s\n大小: %2$s",
//                        name, contentLength > 0 ? FileUtils.fileSize(contentLength) : "未知"))
//                .setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        WebViewUtils.downloadUsingSystemDownloader(DxWebViewActivity.this, url, name);
//                        if (webView != null && !webView.canGoBack()) {
//                            finish();
//                        }
//                    }
//                }).setNegativeButton(R.string.uikit_dialog_btn_cancel, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                if (webView != null && !webView.canGoBack()) {
//                    finish();
//                }
//            }
//        }).setCancelable(false).create().show();
//    }
//
//
//    @Override
//    public void onBackPressed() {
//        finish();
//    }
//
//    private void back() {
//        mErrorContainerView.setVisibility(View.GONE);
//        WebView mWebview = (WebView) findViewById(R.id.webview);
//        if (mWebview != null) {
//            if (mWebview.canGoBack()) {
//                mWebview.goBack();
//            } else {
//                finish();
//            }
//        }
//    }
//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            back();
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }
//
//    public boolean canGestureDetect() {
//        return true;
//    }
//
////    class HandIcon {
////        @JavascriptInterface
////        public void getIconUrl(String data) {
////            Document document = Jsoup.parse(data);
////            Element element = document.getElementsByTag("img").first();
////            currentIconUrl = element.attr("src");
////        }
////    }
//
//    private class XMWebViewClient extends WebViewClient {
//        private boolean isLoading = false;
//
//        @Override
//        public void onPageStarted(WebView view, String url, Bitmap favicon) {
//            isLoading = true;
//            super.onPageStarted(view, url, favicon);
//            disableOperations();
//            if (mMenus != null && !mMenus.isEmpty()) {
//                mMenus.clear();
//                downToUpSlideDialog.hideAllExtraItems();
//            }
//        }
//
//        @Override
//        public void onPageFinished(final WebView view, String url) {
//            super.onPageFinished(view, url);
//            enableOperations();
////            view.loadUrl("javascript:window.handIcon.getIconUrl(document.body.innerHTML);");
//            if (!TextUtils.isEmpty(view.getTitle())) {
//                updateTitle(view.getTitle());
//            }
//            isLoading = false;
//
//            String comeFrom = getIntent().getStringExtra(KEY_COMEFROM);
//            if (VALUE_COMEFROM_WORKBENCH.equals(comeFrom)) {
//                String script = microAppsController.getScript();
//                if (!TextUtils.isEmpty(script)) {
//                    view.loadUrl("javascript:" + script);
//                    DxLog.d(TAG, "script->" + script.length() + ";url:" + url);
//                }
//            }
//        }
//
//
//        @Override
//        public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
//            DxLog.d(this, "执行了shouldInterceptRequest");
//            // 由于在4.4以上的Android系统中，页面加载时也会触发shouldInterceptRequest()方法，所以我们需要把onPageFinished()之前的排除掉
//            if (!isLoading && !TextUtils.isEmpty(url) && url.endsWith("m3u8") && url.startsWith("http")) {
//                try {
//                    DxLog.d(this, "进入了shouldInterceptRequest");
//                    Intent intent = new Intent(Intent.ACTION_VIEW);
//                    intent.setDataAndType(Uri.parse(url), "audio/x-mpegurl");
//                    startActivity(intent);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//            return super.shouldInterceptRequest(view, url);
//        }
//
//        @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            DxLog.d(TAG, url);
//            if (!TextUtils.isEmpty(url) && appConfigController.isInAuthWhiteList(url)) {//查看是否在认证白名单中
//                WebViewUtils.writeCookie(url);
//            }
//            showTitleBarAndToolBar(url);
//            boolean result = mJsBridge.handleMessageFromJs(url);
//            return result ? result : super.shouldOverrideUrlLoading(view, url);
//        }
//
//        @Override
//        public void onReceivedSslError(final WebView view, final SslErrorHandler handler,
//                                       final SslError error) {
//            if (!DxWebViewActivity.this.isFinishing()) {
//                new XMAlertDialog.Builder(DxWebViewActivity.this)
//                        .setTitle(R.string.webview_security_warning_title)
//                        .setMessage(R.string.webview_security_warning_tips)
//                        .setPositiveButton(R.string.webview_security_warning_continue, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                handler.proceed();
//                            }
//                        })
//                        .setNegativeButton(R.string.webview_security_warning_go_back, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                XMWebViewClient.super.onReceivedSslError(view, handler, error);
//                            }
//                        }).create().show();
//            }
//
//        }
//
//        @Override
//        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
//            super.onReceivedError(view, errorCode, description, failingUrl);
//            mErrorContainerView.setVisibility(View.VISIBLE);
//            if (errorCode == ERROR_CONNECT||errorCode==ERROR_TIMEOUT) {
//                mErrorTip.setText(getString(R.string.tip_network_error));
//            } else {
//                mErrorTip.setText(getString(R.string.tip_openurl_error));
//            }
//            Map<String, Object> params = new HashMap<>();
//            params.put("value0", errorCode);
//            params.put("value1", description);
//            params.put("value2", failingUrl);
//            LogRecordUtils.logEvent("urlLoadError", params);
//            DxLog.d(TAG, "errorcode=" + errorCode + ";description=" + description + ";failingurl=" + failingUrl);
//        }
//    }
//
//    private class XMWebChromeClient extends WebChromeClient {
//
//        @Override
//        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
//            return super.onJsPrompt(view, url, message, defaultValue, result);
//        }
//
//        @Override
//        public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissionsCallback callback) {
//            super.onGeolocationPermissionsShowPrompt(origin, callback);
//            callback.invoke(origin, true, true);
//        }
//
//        @Override
//        public void onReceivedTitle(WebView view, String title) {
//            super.onReceivedTitle(view, title);
//            if (!TextUtils.isEmpty(title)) {
//                updateTitle(title);
//            }
//        }
//
//        @Override
//        public void onProgressChanged(WebView view, int newProgress) {
//            if (newProgress == 100) {
//                progressBar.setVisibility(View.GONE);
//            } else {
//                if (progressBar.getVisibility() == View.GONE && isShowProgress) {
//                    progressBar.setVisibility(View.VISIBLE);
//                }
//                progressBar.setProgress(newProgress);
//            }
//        }
//
//        public void openFileChooser(ValueCallback<Uri> uploadMsg) {
//            this.openFileChooser(uploadMsg, null);
//        }
//
//        public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
//            this.openFileChooser(uploadMsg, acceptType, null);
//        }
//
//        public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
//            JsInvokePFeatureCommand.getInstance().setJsNativeBridge(mJsBridge);
//            JsInvokePFeatureCommand.getInstance().openFileChooser(uploadMsg, acceptType, capture);
//        }
//
//        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//        @Override
//        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
//            JsInvokePFeatureCommand.getInstance().setJsNativeBridge(mJsBridge);
//            return JsInvokePFeatureCommand.getInstance().showFileChooser(filePathCallback, fileChooserParams.createIntent());
//        }
//    }
//}
