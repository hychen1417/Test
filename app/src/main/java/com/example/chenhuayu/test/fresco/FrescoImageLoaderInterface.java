package com.example.chenhuayu.test.fresco;

import android.content.Context;

import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.producers.BaseNetworkFetcher;

import java.io.File;

/**
 * Created by chenhuayu on 2018/3/8.
 */

public interface FrescoImageLoaderInterface {
    void init(final Context context, final File dir, BaseNetworkFetcher fetcher);

    void displayImage(LoadConfiguration configuration);

    void fetchImageWithCallback(String uri, final FetchBitmapImageCallback callback);

    void fetchImageWithCallback(String uri, final FetchImageCallback callback);

    void fetchCacheImageWithCallback(String uri, final FetchImageCallback callback);

    CloseableReference<CloseableImage> fetchCacheImage(String uri);

    void shutDown();
}
