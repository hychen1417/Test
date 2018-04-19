package com.example.chenhuayu.test.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Activity 跳转工具类
 * Created by chenhuayu on 2018/4/17.
 */

public class StartActivityUtil {
    public StartActivityUtil() {
        throw new UnsupportedOperationException("ActivitySkipUtil不能实例化");
    }

    /**
     * 功能描述:简单地 Activity 的跳转(不携带任何数据)
     *
     * @param activity 发起跳转的 Activity 实例
     * @param cls      目标 Activity 实例
     */
    public static void skipAnotherActivity(Activity activity,
                                           Class<? extends Activity> cls) {
        Intent intent = new Intent(activity, cls);
        activity.startActivity(intent);
    }

    /**
     * 功能描述：带数据的 Activity 之间的跳转
     *
     * @param activity 发起跳转的 Activity 实例
     * @param cls      目标 Activity 实例
     * @param hashMap  传递的数据
     */
    public static void skipAnotherActivity(Activity activity,
                                           Class<? extends Activity> cls,
                                           HashMap<String, ? extends Object> hashMap) {
        Intent intent = new Intent(activity, cls);
        Iterator<?> iterator = hashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            @SuppressWarnings("unchecked")
            Map.Entry<String, Object> entry = (Map.Entry<String, Object>) iterator
                    .next();
            String key = entry.getKey();
            Object value = entry.getValue();

            if (value instanceof Boolean) {
                intent.putExtra(key, (boolean) value);
            } else if (value instanceof Boolean[]) {
                intent.putExtra(key, (boolean[]) value);
            } else if (value instanceof Byte) {
                intent.putExtra(key, (byte) value);
            } else if (value instanceof Byte[]) {
                intent.putExtra(key, (byte[]) value);
            } else if (value instanceof Character) {
                intent.putExtra(key, (char) value);
            } else if (value instanceof Character[]) {
                intent.putExtra(key, (char[]) value);
            } else if (value instanceof Short) {
                intent.putExtra(key, (short) value);
            } else if (value instanceof Short[]) {
                intent.putExtra(key, (short[]) value);
            } else if (value instanceof Integer) {
                intent.putExtra(key, (int) value);
            } else if (value instanceof Integer[]) {
                intent.putExtra(key, (int[]) value);
            } else if (value instanceof Long) {
                intent.putExtra(key, (long) value);
            } else if (value instanceof Long[]) {
                intent.putExtra(key, (long[]) value);
            } else if (value instanceof Float) {
                intent.putExtra(key, (float) value);
            } else if (value instanceof Float[]) {
                intent.putExtra(key, (float[]) value);
            } else if (value instanceof Double) {
                intent.putExtra(key, (double) value);
            } else if (value instanceof Double[]) {
                intent.putExtra(key, (double[]) value);
            } else if (value instanceof String) {
                intent.putExtra(key, (String) value);
            } else if (value instanceof String[]) {
                intent.putExtra(key, (String[]) value);
            } else if (value instanceof CharSequence) {
                intent.putExtra(key, (CharSequence) value);
            } else if (value instanceof CharSequence[]) {
                intent.putExtra(key, (CharSequence[]) value);
            } else if (value instanceof Parcelable) {
                intent.putExtra(key, (Parcelable) value);
            } else if (value instanceof Parcelable[]) {
                intent.putExtra(key, (Parcelable[]) value);
            } else if (value instanceof Serializable) {
                intent.putExtra(key, (Serializable) value);
            } else if (value instanceof Bundle) {
                intent.putExtra(key, (Bundle) value);
            } else {
                Log.e("StartActivityUtil", value.getClass().getName().toString() + " is Not Supported");
                return;
            }
        }
        activity.startActivity(intent);
    }
}
