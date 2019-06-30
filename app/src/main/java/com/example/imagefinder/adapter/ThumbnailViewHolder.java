package com.example.imagefinder.adapter;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.example.imagefinder.databinding.ItemThumbnailBinding;

import java.util.Objects;

class ThumbnailViewHolder extends RecyclerView.ViewHolder {

    @NonNull
    private final ItemThumbnailBinding binding;

    ThumbnailViewHolder(@NonNull View itemView) {
        super(itemView);
        binding = Objects.requireNonNull(DataBindingUtil.bind(itemView));
    }

    @NonNull
    ItemThumbnailBinding getBinding() {
        return binding;
    }
}
