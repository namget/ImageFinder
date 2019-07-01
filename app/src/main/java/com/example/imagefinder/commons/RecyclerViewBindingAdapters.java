package com.example.imagefinder.commons;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.example.imagefinder.adapter.ThumbnailAdapter;
import com.example.imagefinder.data.model.Thumbnail;

import java.util.List;

public class RecyclerViewBindingAdapters {

    @BindingAdapter("item")
    public static void setThumbnailItems(
            @NonNull RecyclerView recyclerView,
            @Nullable List<Thumbnail> items
    ) {
        ThumbnailAdapter adapter = (ThumbnailAdapter) recyclerView.getAdapter();
        if (adapter != null && items != null) {
            adapter.submitList(items);
        }
    }
}
