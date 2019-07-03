package com.example.imagefinder.commons;

import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import com.example.imagefinder.utils.DateUtils;

import java.util.Date;

public class TextViewBindingAdapters {

    @BindingAdapter("text")
    public static void setTextByDate(@NonNull TextView textView, @Nullable Date date) {
        if (date != null) {
            textView.setText(DateUtils.formatToYearMonthDay(date));
        }
    }
}
