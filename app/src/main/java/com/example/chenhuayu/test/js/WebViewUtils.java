//package com.example.chenhuayu.test.js;
//
//import android.content.Context;
//import android.net.Uri;
//import android.text.TextUtils;
//import android.util.Log;
//import android.webkit.MimeTypeMap;
//
//import com.sankuai.xm.tools.utils.DxToast;
//import com.sankuai.xm.tools.utils.HashUtils;
//import com.sankuai.xmpp.DxContext;
//import com.sankuai.xmpp.R;
//import com.sankuai.xmpp.env.DevelopEnv;
//import com.sankuai.xmpp.env.PackageEnv;
//import com.sankuai.xmpp.env.PackageEnvFactory;
//import com.sankuai.xmpp.utils.DownloadManagerCompat;
//import com.sankuai.xmpp.utils.DxLog;
//import com.tencent.smtt.sdk.CookieManager;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.net.URLDecoder;
//import java.util.HashMap;
//import java.util.Locale;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//
///**
// * Created by mahaifeng on 16/2/17.
// */
//public class WebViewUtils {
//
//    public static void setCookie(CookieManager cookieManager, String url) {
//        if (cookieManager == null || TextUtils.isEmpty(url)) {
//            return;
//        }
//        DxContext dxContext = DxContext.getInstance();
//        long uid = dxContext.getLoginUid();
//        String cookie = dxContext.getToken();
//        long date = System.currentTimeMillis();
//        String token = HashUtils.MD5(uid + cookie + date);
//        String host = Uri.parse(url).getHost();
//        cookieManager.setCookie(host, String.format("u=%d", uid));
//        cookieManager.setCookie(host, String.format("ai=%d", DxContext.APPID));
//        cookieManager.setCookie(host, String.format("dt=%d", 1));
//        cookieManager.setCookie(host, String.format("deviceType=%d", 1));
//        cookieManager.setCookie(host, String.format("ck=%s", cookie));
//        cookieManager.setCookie(host, String.format("date=%d", date));
//        cookieManager.setCookie(host, String.format("token=%s", token));
//        cookieManager.setCookie(host, String.format("al=%s", dxContext.getAlToken()));
//        cookieManager.setCookie(host, String.format("uu=%s", DxContext.getInstance().getDeviceId()));
//        cookieManager.setCookie(host, String.format("cid=%s", DxContext.getInstance().getCid()));
//        DxLog.d("WebViewUtils", "altoken=" + dxContext.getAlToken());
//    }
//
//    public static String getHostAndPort(String url) {
//        String ret = null;
//        URL u = null;
//        try {
//            u = new URL(url);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        if (u != null) {
//            String host = u.getHost();
//            int port = u.getPort();
//            ret = host + ":" + port;
//        }
//        return ret;
//    }
//
//    public static String getCookie(String url) {
//        String host = "";
//        try {
//            host = new URL(url).getHost();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        return CookieManager.getInstance().getCookie(host);
//    }
//
//    public static String processLink(String url) {
//        if (Uri.parse(url).getScheme() == null) {
//            url = "http://" + url;
//        }
//
//        if (url.startsWith("http://task.sankuai.com")) {
//            ////////////////////解决task 添加sso 的return链接 405 的问题
//            boolean isAddSSO = true;
//            CookieManager cookieManager = CookieManager.getInstance();
//            String cookies = cookieManager.getCookie("task.sankuai.com");
//            if (cookies != null) {
//                String[] cookiesArray = cookies.split(";");
//                for (String str : cookiesArray) {
//                    if (str.contains("atlassian.xsrf.token") && str.contains("lin")) {
//                        isAddSSO = false;
//                        break;
//                    }
//                }
//            }
//            ////////////////end
//            if (isAddSSO) {
//                if (url.startsWith("http://task.sankuai.com/plugins/servlet/mobile#issue/")) {
//                    url = url.replace("http://task.sankuai.com/plugins/servlet/mobile#issue/", "http://task.sankuai.com/browse/");
//                }
//            }
//
//            if (isAddSSO) {
//                url = "https://sso.sankuai.com/auth?service=" + url;
//            }
//        } else if (url.startsWith("http://wiki.sankuai.com")) {
//
//            if (url.startsWith("http://wiki.sankuai.com/plugins/servlet/mobile#content/view/")) {
//                url = url.replace("http://wiki.sankuai.com/plugins/servlet/mobile#content/view/", "http://wiki.sankuai.com/pages/viewpage.action?pageId=");
//            }
//
//            if (url.startsWith("http://wiki.sankuai.com/plugins/servlet/mobile")) {//详情查看http://wiki.sankuai.com/pages/viewpage.action?pageId=359613635
//                //对url 不做处理
//            } else {
//                url = "https://sso.sankuai.com/auth?service=" + url;
//            }
//        } else if (url.startsWith("http://news.sankuai.com")) {
//
//            if (url.startsWith("http://news.sankuai.com/plugins/servlet/mobile#content/view/")) {
//                url = url.replace("http://news.sankuai.com/plugins/servlet/mobile#content/view/", "http://news.sankuai.com/pages/viewpage.action?pageId=");
//            }
//
//            if (url.startsWith("http://news.sankuai.com/plugins/servlet/mobile")) {//详情查看http://wiki.sankuai.com/pages/viewpage.action?pageId=359613635
//                //对url 不做处理
//            } else {
//                url = "https://sso.sankuai.com/auth?service=" + url;
//            }
//        }
//
//        if (url.contains("&amp;")) {
//            url = url.replaceAll("&amp;", "&");
//        }
//        return url;
//    }
//
//    public static void writeCookie(String url) {
//        CookieManager cookieManager = CookieManager.getInstance();
//        cookieManager.setAcceptCookie(true);
//        WebViewUtils.setCookie(cookieManager, url);
//    }
//
//    public static void downloadUsingSystemDownloader(Context context, String url, String name) {
//        DownloadManagerCompat.Request request = new DownloadManagerCompat.Request(Uri.parse(url), null);
//        request.showNotificationOnDownloading(true);
//        request.showNotificationOnDownloaded(true);
//        request.setFileName(name);
//        String cookie = WebViewUtils.getCookie(url);
//        if (!TextUtils.isEmpty(cookie)) {
//            HashMap<String, String> headers = new HashMap<String, String>();
//            headers.put("Cookie", cookie + "; AcSe=0");
//            request.setHeaders(headers);
//        }
//        long id = DownloadManagerCompat.getInstance(context).download(request);
//        if (id != -1) {
//            DxToast.show(context, R.string.file_download_message);
//        }
//    }
//
//    public static void plantSsoCookie(JSONObject cookies) {
//        CookieManager cookieManager = CookieManager.getInstance();
//        try {
//            if (0 == cookies.optInt("code", -1)) {
//                JSONArray array = cookies.getJSONArray("data");
//                for (int i = 0; i < array.length(); i++) {
//                    JSONObject object = array.getJSONObject(i);
//                    if (0 == object.optInt("code", -1)) {
//                        JSONObject subObject = object.getJSONObject("data");
//                        cookieManager.setCookie(subObject.optString("domain"), subObject.optString("value"));
//                    }
//                }
//            }
//            Log.v("plantSsoCookie", cookies.toString());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
//
//    //旧版本兼容
//    public static void plantSsoCookie(String cookies) {
//        if (!TextUtils.isEmpty(cookies)) {
//            CookieManager cookieManager = CookieManager.getInstance();
//            cookieManager.setCookie("sso.sankuai.com", cookies);
//        }
//    }
//
//    public static boolean isNeedInvokeSsoTrust() {
//        CookieManager cookieManager = CookieManager.getInstance();
//        PackageEnv env = PackageEnvFactory.getInstance();
//        String sso;
//        if (env instanceof DevelopEnv) {
//            sso = cookieManager.getCookie("develop.sso.test.sankuai.info");
//        } else {
//            sso = cookieManager.getCookie("sso.sankuai.com");
//        }
//        DxLog.v("ssoCookies", sso);
//        return TextUtils.isEmpty(sso) ? true : false;
//    }
//
//    public static final String guessFileName(String url, String contentDisposition, String mimeType) {
//        String filename = null;
//        String extension = null;
//
//        // If we couldn't do anything with the hint, move toward the content disposition
//        if (filename == null && contentDisposition != null) {
//            filename = parseContentDisposition(contentDisposition);
//            if (filename != null) {
//                int index = filename.indexOf("''");
//                if (index > -1) {
//                    try {
//                        //处理编码,举例:Content-Disposition: attachment; filename*=iso-8859-1''foo-%E4.html
//                        String charSetName = filename.substring(0, index);
//                        filename = filename.substring(index + 2);
//                        filename = URLDecoder.decode(filename, charSetName);
//                    } catch (Exception e) {
//                    }
//                }
//                index = filename.lastIndexOf('/') + 1;
//                if (index > 0) {
//                    filename = filename.substring(index);
//                }
//            }
//        }
//
//        // If all the other http-related approaches failed, use the plain uri
//        if (filename == null) {
//            String decodedUrl = Uri.decode(url);
//            if (decodedUrl != null) {
//                int queryIndex = decodedUrl.indexOf('?');
//                // If there is a query string strip it, same as desktop browsers
//                if (queryIndex > 0) {
//                    decodedUrl = decodedUrl.substring(0, queryIndex);
//                }
//                if (!decodedUrl.endsWith("/")) {
//                    int index = decodedUrl.lastIndexOf('/') + 1;
//                    if (index > 0) {
//                        filename = decodedUrl.substring(index);
//                    }
//                }
//            }
//        }
//
//        // Finally, if couldn't get filename from URI, get a generic filename
//        if (filename == null) {
//            filename = "downloadfile";
//        }
//
//        // Split filename between base and extension
//        // Add an extension if filename does not have one
//        int dotIndex = filename.indexOf('.');
//        if (dotIndex < 0) {
//            if (mimeType != null) {
//                extension = MimeTypeMap.getSingleton().getExtensionFromMimeType(mimeType);
//                if (extension != null) {
//                    extension = "." + extension;
//                }
//            }
//            if (extension == null) {
//                if (mimeType != null && mimeType.toLowerCase(Locale.ROOT).startsWith("text/")) {
//                    if (mimeType.equalsIgnoreCase("text/html")) {
//                        extension = ".html";
//                    } else {
//                        extension = ".txt";
//                    }
//                } else {
//                    extension = ".bin";
//                }
//            }
//        } else {
//            if (mimeType != null) {
//                // Compare the last segment of the extension against the mime type.
//                // If there's a mismatch, discard the entire extension.
//                int lastDotIndex = filename.lastIndexOf('.');
//                String typeFromExt = MimeTypeMap.getSingleton().getMimeTypeFromExtension(
//                        filename.substring(lastDotIndex + 1));
//                if (typeFromExt != null && !typeFromExt.equalsIgnoreCase(mimeType)) {
//                    extension = MimeTypeMap.getSingleton().getExtensionFromMimeType(mimeType);
//                    if (extension != null) {
//                        extension = "." + extension;
//                    }
//                }
//            }
//            if (extension == null) {
//                extension = filename.substring(dotIndex);
//            }
//            filename = filename.substring(0, dotIndex);
//        }
//
//        return URLDecoder.decode(filename + extension);
//    }
//
//    /**
//     * Regex used to parse content-disposition headers
//     */
//    private static final Pattern CONTENT_DISPOSITION_PATTERN =
//            Pattern.compile("attachment;\\s*filename\\*?\\s*=\\s*(\"?)([^\"]*)\\1;?\\s*",
//                    Pattern.CASE_INSENSITIVE);
//
//    /*
//     * Parse the Content-Disposition HTTP Header. The format of the header
//     * is defined here: http://www.w3.org/Protocols/rfc2616/rfc2616-sec19.html
//     * This header provides a filename for content that is going to be
//     * downloaded to the file system. We only support the attachment type.
//     * Note that RFC 2616 specifies the filename value must be double-quoted.
//     * Unfortunately some servers do not quote the value so to maintain
//     * consistent behaviour with other browsers, we allow unquoted values too.
//     *
//     * example:Content-Disposition: attachment;filename=foo-ä.html;filename*=iso-8859-1''foo-%E4.html
//     */
//    static String parseContentDisposition(String contentDisposition) {
//        try {
//            Matcher m = CONTENT_DISPOSITION_PATTERN.matcher(contentDisposition);
//            if (m.find()) {
//                return m.group(2);
//            }
//        } catch (IllegalStateException ex) {
//            // This function is defined as returning null when it can't parse the header
//        }
//        return null;
//    }
//}
