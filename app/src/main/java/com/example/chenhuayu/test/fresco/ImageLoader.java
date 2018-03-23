package com.example.chenhuayu.test.fresco;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.facebook.common.references.CloseableReference;
import com.facebook.common.util.ByteConstants;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.producers.BaseNetworkFetcher;

import java.io.File;

/**
 * Created by chenhuayu on 2018/3/8.
 */

public class ImageLoader {
    private static final long MAX_HEAP_SIZE = (long) Runtime.getRuntime().maxMemory();
    public static final int MAX_MEMORY_CACHE_SIZE = (int) (MAX_HEAP_SIZE / 8); // 图片缓存最大内存空间
    public static final int IMAGE_LOADER_MAX_DISK_CACHE_SIZE = 200 * ByteConstants.MB; // 图片占用磁盘的最大空间

    private static FrescoImageLoaderInterface mLoader;

    public static void init(final Context context, final File dir, BaseNetworkFetcher fetcher, FrescoImageLoaderInterface loader) {
        mLoader = loader;
        mLoader.init(context, dir, fetcher);
    }

    //请勿更改此方法名
    public static boolean hasBeenInitialized(){
        return mLoader != null;
    }

    public static void shutDown() {
        if (mLoader != null) {
            mLoader.shutDown();
            mLoader = null;
        }
    }

    /**
     *  加载图片 使用LoadConfiguration
     * @param configuration
     */
    public static void displayImage(@NonNull LoadConfiguration configuration) {
        mLoader.displayImage(configuration);
    }

    /**
     * 最基本的加载图片
     * @param view
     * @param url
     */
    public static void displayImage(View view, String url) {
        mLoader.displayImage(LoadConfiguration
                .newBuilder()
                .setView(view)
                .setUrl(url)
                .create());
    }

    /**
     *  按缓存--磁盘--网络顺序取图
     *  注意返回的bitmap不允许赋值到当前函数作用域之外
     * @param uri
     * @param callback
     */
    public static void fetchImageWithCallback(String uri, FetchBitmapImageCallback callback) {
        mLoader.fetchImageWithCallback(uri, callback);
    }

    /**
     * 按缓存--磁盘--网络顺序取图返回CloseableReference 可以赋值到其他作用域
     * 但一定要关闭掉 CloseableReference.closeSafely(imageReference);
     * @param uri
     * @param callback
     */
    public static void fetchImageWithCallback(String uri, final FetchImageCallback callback) {
        mLoader.fetchImageWithCallback(uri, callback);
    }

    public static CloseableReference<CloseableImage> fetchCacheImage(String uri) {
        return mLoader.fetchCacheImage(uri);
    }

    /**
     * 从当前内存缓存中直接取图 没有就直接返回空
     * @param uri
     * @param callback
     */
    public static void fetchCacheImageWithCallback(String uri, final FetchImageCallback callback) {
        mLoader.fetchCacheImageWithCallback(uri, callback);
    }
}
