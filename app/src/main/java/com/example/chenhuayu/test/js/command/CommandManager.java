//package com.example.chenhuayu.test.js.command;
//
//import com.sankuai.xmpp.utils.StringUtils;
//
//import java.io.File;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created by mahaifeng on 16/2/15.
// */
//public class CommandManager {
//    public final static String SCHEMEURL = "mtdaxiang";
//    public final static String HTTPURL = "http";
//    public static String HOST_URL = "www.meituan.com";
//    public static String SEPARATOR = "://";
//    public static final String CLOSEURL2 = "mtxm://close";//关闭webview
//    public static final String SCANQRCODE = "mtxm://scanqrcode";//扫描二维码
//    public static String URL_PATH = SCHEMEURL + SEPARATOR + HOST_URL + File.separator;
//    public static final String CLOSEURL = URL_PATH + "close";//关闭webview
//    public static final String SCANURL_JSCALLBACK = URL_PATH + "scan";
//    public static final String CHATURL = URL_PATH + "chat";
//    public static final String LOCATIONURL = URL_PATH + "location";
//    public static final String VOICE_RECORD_URL = URL_PATH + "voiceRecord";
//    public static final String VOICE_PLAY_URL = URL_PATH + "voicePlay";
//    public static final String UPLOAD_FILE = URL_PATH + "fileUpload";
//    public static final String SHAKEURL = URL_PATH + "shake";
//    public static final String PROFILEURL = URL_PATH + "profile";
//    public static final String IMAGEURL = URL_PATH + "image";
//    public static final String MICROAPPURL = URL_PATH + "microapp";
//    public static final String LOADMENUURL = URL_PATH + "topMenu";
//    public static final String UPLOADFILEFROMNATIVE = URL_PATH + "uploadFileFromNative";
//    public static final String SELECTPEERS = URL_PATH + "selectpeers";
//    public static final String SHOWPROGRESSDIALOG = URL_PATH + "showProgressDialog";
//    public static final String CANCELPROGRESSDIALOG = URL_PATH + "cancelProgressDialog";
//    public static final String MICROAPPAUTH = URL_PATH + "native_api/auth";
//    public static final String VIEWPHOTOS = URL_PATH + "viewPhotos";
//    public static final String SETWEBVIEWTITLE = URL_PATH + "setWebViewTitle";
//    public static final String CALLCREATECORP = URL_PATH + "create_corp";
//    public final static String[] WHITELISTURI = new String[]{CLOSEURL, CLOSEURL2, SCANQRCODE, SHOWPROGRESSDIALOG, CANCELPROGRESSDIALOG};
//    public final static String SENDTEMPLATEMSG = URL_PATH + "sendTemplateMsg";
//    public final static String UPLOADMULTFILEFROMNATIVE = URL_PATH + "uploadMultFileFromNative";
//    public final static String UPLOADFILE = URL_PATH + "uploadFile";
//    public final static String FILEPREVIEW = URL_PATH + "file/preview";
//    public final static String SELECT_DATE = URL_PATH + "selectDate";
//    public final static String RADIO_BOX = URL_PATH + "radioBox";
//    public final static String CHECK_BOX = URL_PATH + "checkBox";
//    public final static String APPINSTALLED = URL_PATH + "appInstalled";
//    public final static String JSMONITOR=URL_PATH+"perf/report";
//
//    private static Map<String, BaseCommand> mapCommand;
//
//    static {
//        mapCommand = new HashMap<>();
//
//        mapCommand.put(CLOSEURL, JsInvokeWViewCommand.getInstance());
//        mapCommand.put(CLOSEURL2, JsInvokeWViewCommand.getInstance());
//        mapCommand.put(LOADMENUURL, JsInvokeWViewCommand.getInstance());
//        mapCommand.put(SHOWPROGRESSDIALOG, JsInvokeWViewCommand.getInstance());
//        mapCommand.put(CANCELPROGRESSDIALOG, JsInvokeWViewCommand.getInstance());
//        mapCommand.put(MICROAPPAUTH, JsInvokeWViewCommand.getInstance());
//        mapCommand.put(SETWEBVIEWTITLE, JsInvokeWViewCommand.getInstance());
//        mapCommand.put(JSMONITOR,JsInvokeWViewCommand.getInstance());
//
//
//        mapCommand.put(CHATURL, JsInvokeDxModuleCommand.getInstance());
//        mapCommand.put(PROFILEURL, JsInvokeDxModuleCommand.getInstance());
//        mapCommand.put(MICROAPPURL, JsInvokeDxModuleCommand.getInstance());
//        mapCommand.put(SELECTPEERS, JsInvokeDxModuleCommand.getInstance());
//        mapCommand.put(VIEWPHOTOS, JsInvokeDxModuleCommand.getInstance());
//        mapCommand.put(SENDTEMPLATEMSG, JsInvokeDxModuleCommand.getInstance());
//        mapCommand.put(SELECT_DATE, JsInvokeDxModuleCommand.getInstance());
//        mapCommand.put(RADIO_BOX, JsInvokeDxModuleCommand.getInstance());
//        mapCommand.put(CHECK_BOX, JsInvokeDxModuleCommand.getInstance());
//
//        mapCommand.put(SCANQRCODE, JsInvokePFeatureCommand.getInstance());
//        mapCommand.put(SCANURL_JSCALLBACK, JsInvokePFeatureCommand.getInstance());
//        mapCommand.put(LOCATIONURL, JsInvokePFeatureCommand.getInstance());
//        mapCommand.put(VOICE_RECORD_URL, JsInvokePFeatureCommand.getInstance());
//        mapCommand.put(VOICE_PLAY_URL, JsInvokePFeatureCommand.getInstance());
//        mapCommand.put(UPLOAD_FILE, JsInvokePFeatureCommand.getInstance());
//        mapCommand.put(SHAKEURL, JsInvokePFeatureCommand.getInstance());
//        mapCommand.put(IMAGEURL, JsInvokePFeatureCommand.getInstance());
//        mapCommand.put(UPLOADFILEFROMNATIVE, JsInvokePFeatureCommand.getInstance());
//        mapCommand.put(CALLCREATECORP, JsInvokePFeatureCommand.getInstance());
//        mapCommand.put(UPLOADMULTFILEFROMNATIVE, JsInvokePFeatureCommand.getInstance());
//        mapCommand.put(APPINSTALLED, JsInvokePFeatureCommand.getInstance());
//
//    }
//
//    public BaseCommand getCommand(String url) {
//        BaseCommand baseCommand = mapCommand.get(url.split("\\?")[0]);
//        if (baseCommand == null && (!StringUtils.isHttpUrl(url) && !StringUtils.isFileUrl(url))) {
//            baseCommand = JsInvokePFeatureCommand.getInstance();
//        }
//        return baseCommand;
//    }
//}
