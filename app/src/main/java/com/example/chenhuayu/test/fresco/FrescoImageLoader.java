package com.example.chenhuayu.test.fresco;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.view.View;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.internal.Supplier;
import com.facebook.common.memory.MemoryTrimType;
import com.facebook.common.memory.MemoryTrimmable;
import com.facebook.common.memory.NoOpMemoryTrimmableRegistry;
import com.facebook.common.references.CloseableReference;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
import com.facebook.imagepipeline.common.TooManyBitmapsException;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.producers.BaseNetworkFetcher;

import java.io.File;

/**
 * Created by chenhuayu on 2018/3/8.
 */

public class FrescoImageLoader implements FrescoImageLoaderInterface{
    private Context mAppContext;
    private FailureHandler mDefaultFailureHandler;
    @Override
    public void init(final Context context, File dir, final BaseNetworkFetcher fetcher) {
        Context appContext = context.getApplicationContext();
        if (dir == null) {
            dir = getDefaultDir(context);
        }
        final File root = dir;
        mDefaultFailureHandler = new FailureHandler() {
            long lastRestartTime;

            @Override
            public void handle(String uri, View view, Throwable throwable) {
                if (throwable instanceof TooManyBitmapsException) {
                    if (System.currentTimeMillis() - lastRestartTime > 5000) {
                        lastRestartTime = System.currentTimeMillis();
                        ImagePipeline pipeline = Fresco.getImagePipeline();
                        pipeline.clearMemoryCaches();
                        Fresco.shutDown();
                        ImageLoader.init(context, root, fetcher, new FrescoImageLoader());
                    }
                }
            }
        };
        final MemoryCacheParams bitmapCacheParams = new MemoryCacheParams(
                ImageLoader.MAX_MEMORY_CACHE_SIZE, // Max total size of elements in the cache
                Integer.MAX_VALUE,                     // Max entries in the cache
                ImageLoader.MAX_MEMORY_CACHE_SIZE, // Max total size of elements in eviction queue
                Integer.MAX_VALUE,                     // Max length of eviction queue
                Integer.MAX_VALUE);                    // Max cache entry size

        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(appContext)
                .setMainDiskCacheConfig(DiskCacheConfig.newBuilder(appContext)
                        .setBaseDirectoryPath(root)
                        .setMaxCacheSize(ImageLoader.IMAGE_LOADER_MAX_DISK_CACHE_SIZE)
                        .build())
                .setDownsampleEnabled(true)
                .setNetworkFetcher(fetcher)
                .setResizeAndRotateEnabledForNetwork(true)
                .setCacheKeyFactory(CacheKeyFactory.getInstance())
                .setBitmapMemoryCacheParamsSupplier(
                        new Supplier<MemoryCacheParams>() {
                            public MemoryCacheParams get() {
                                return bitmapCacheParams;
                            }
                        })
                .build();
        // 增加缓存清空机制
        NoOpMemoryTrimmableRegistry.getInstance().registerMemoryTrimmable(new MemoryTrimmable() {
            @Override
            public void trim(MemoryTrimType trimType) {
                try {
                    final double suggestedTrimRatio = trimType.getSuggestedTrimRatio();
                    if (MemoryTrimType.OnCloseToDalvikHeapLimit.getSuggestedTrimRatio() == suggestedTrimRatio
                            || MemoryTrimType.OnSystemLowMemoryWhileAppInBackground.getSuggestedTrimRatio() == suggestedTrimRatio
                            || MemoryTrimType.OnSystemLowMemoryWhileAppInForeground.getSuggestedTrimRatio() == suggestedTrimRatio
                            ) {
                        ImagePipelineFactory.getInstance().getImagePipeline().clearMemoryCaches();
                    }
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        });

        Fresco.initialize(appContext, config);
        mAppContext = appContext;
    }

    @Override
    public void displayImage(LoadConfiguration configuration) {

    }

    @Override
    public void fetchImageWithCallback(String uri, FetchBitmapImageCallback callback) {

    }

    @Override
    public void fetchImageWithCallback(String uri, FetchImageCallback callback) {

    }

    @Override
    public void fetchCacheImageWithCallback(String uri, FetchImageCallback callback) {

    }

    @Override
    public CloseableReference<CloseableImage> fetchCacheImage(String uri) {
        return null;
    }

    @Override
    public void shutDown() {

    }
    public File getDefaultDir(Context context) {
        try {
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                return context.getExternalFilesDir(null);
            } else {
                return context.getFilesDir();
            }
        } catch (Exception e) {
            return context.getFilesDir();
        }
    }
}
