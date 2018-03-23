package com.example.chenhuayu.test.fresco;

import com.facebook.common.util.HashCodeUtil;

import java.util.Locale;

/**
 * Created by chenhuayu on 2018/3/8.
 */

public class FrescoResizeOptions {
    /* target width (in pixels) */
    public final int width;

    /* target height (in pixels) */
    public final int height;

    public FrescoResizeOptions(
            int width,
            int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public int hashCode() {
        return HashCodeUtil.hashCode(
                width,
                height);
    }

    @Override
    public String toString() {
        return String.format((Locale) null, "%dx%d", width, height);
    }
}
