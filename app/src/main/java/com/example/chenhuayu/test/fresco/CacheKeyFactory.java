package com.example.chenhuayu.test.fresco;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.SimpleCacheKey;
import com.facebook.imagepipeline.cache.BitmapMemoryCacheKey;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.Postprocessor;

/**
 * Created by chenhuayu on 2018/3/8.
 */

public class CacheKeyFactory implements com.facebook.imagepipeline.cache.CacheKeyFactory {
    private static CacheKeyFactory sInstance = null;

    private CacheKeyFactory() {
    }

    public static synchronized CacheKeyFactory getInstance() {
        if (sInstance == null) {
            sInstance = new CacheKeyFactory();
        }
        return sInstance;
    }

    public Uri getCacheKeySourceUri(Uri sourceUri) {
        if (sourceUri.toString().contains("mss_814dc1610cda4b2e8febd6ea2c809db5")) {
            //云盘图片
            String uriString = sourceUri.getEncodedPath();
            return TextUtils.isEmpty(uriString) ? sourceUri : Uri.parse(uriString);
        } else {
            return sourceUri;
        }
    }

    @Override
    public CacheKey getBitmapCacheKey(ImageRequest request, Object callerContext) {
        return new BitmapMemoryCacheKey(
                getCacheKeySourceUri(request.getSourceUri()).toString(),
                request.getResizeOptions(),
                request.getRotationOptions(),
                request.getImageDecodeOptions(),
                null,
                null,
                null);
    }

    @Override
    public CacheKey getPostprocessedBitmapCacheKey(ImageRequest request, Object callerContext) {
        Postprocessor postprocessor = request.getPostprocessor();
        CacheKey postprocessorCacheKey;
        String postprocessorName;
        if (postprocessor != null) {
            postprocessorCacheKey = postprocessor.getPostprocessorCacheKey();
            postprocessorName = postprocessor.getClass().getName();
        } else {
            postprocessorCacheKey = null;
            postprocessorName = null;
        }
        return new BitmapMemoryCacheKey(
                getCacheKeySourceUri(request.getSourceUri()).toString(),
                request.getResizeOptions(),
                request.getRotationOptions(),
                request.getImageDecodeOptions(),
                postprocessorCacheKey,
                postprocessorName,
                null);
    }

    @Override
    public CacheKey getEncodedCacheKey(ImageRequest request, Object callerContext) {
        return new SimpleCacheKey(getCacheKeySourceUri(request.getSourceUri()).toString());
    }

    @Override
    public CacheKey getEncodedCacheKey(ImageRequest request, Uri sourceUri, @Nullable Object callerContext) {
        return new SimpleCacheKey(getCacheKeySourceUri(request.getSourceUri()).toString());
    }
}
