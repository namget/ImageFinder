package com.example.imagefinder.utils;

public class TextUtils {

    public static boolean isEmpty(String str) {
        return android.text.TextUtils.isEmpty(str);
    }

    public static boolean isNotEmpty(String str) {
        return !android.text.TextUtils.isEmpty(str);
    }

    public static boolean isEquals(String str1, String str2) {
        return android.text.TextUtils.equals(str1, str2);
    }

    public static boolean isNotEquals(String str1, String str2) {
        return !android.text.TextUtils.equals(str1, str2);
    }
}
