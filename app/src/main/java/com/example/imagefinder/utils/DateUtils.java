package com.example.imagefinder.utils;

import androidx.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    private static final SimpleDateFormat kakaoDateFormat =
            new SimpleDateFormat("yyyy-MM-dd'T'kk:mm:ss.SSSz", Locale.KOREA);

    private static final SimpleDateFormat yearMonthDayOrderFormat =
            new SimpleDateFormat("yy.MM.dd  kk:mm", Locale.KOREA);

    private DateUtils() {
    }

    @NonNull
    public static Date parseKakaoDateToDate(@NonNull String kakaoDate) {
        try {
            synchronized (kakaoDateFormat) {
                return kakaoDateFormat.parse(kakaoDate);
            }
        } catch (ParseException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Invalid kakaoDate parameter: " + kakaoDate);
        }
    }

    @NonNull
    public static String formatToYearMonthDay(@NonNull Date date) {
        return yearMonthDayOrderFormat.format(date);
    }

}
