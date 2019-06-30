package com.example.imagefinder.utils;

public class TextUtils {

    public static boolean isEmpty(String str) {
        return android.text.TextUtils.isEmpty(str);
    }

    public static boolean isNotEmpty(String str) {
        return !android.text.TextUtils.isEmpty(str);
    }
}
