package com.example.imagefinder.commons;

import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import com.bumptech.glide.Glide;
import com.example.imagefinder.utils.TextUtils;

public class ImageViewBindingAdapters {

    @BindingAdapter("uriCenterCrop")
    public static void setImageByUriCenterCrop(
            @NonNull ImageView imageView,
            @Nullable String uri
    ) {
        if (TextUtils.isNotEmpty(uri)) {
            Glide.with(imageView)
                    .load(uri)
                    .centerCrop()
                    .into(imageView);
        }
    }

    @BindingAdapter("uri")
    public static void setImageByUri(
            @NonNull ImageView imageView,
            @Nullable String uri
    ) {
        if (TextUtils.isNotEmpty(uri)) {
            Glide.with(imageView)
                    .load(uri)
                    .into(imageView);
        }
    }
}
