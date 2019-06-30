package com.example.imagefinder.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import com.example.imagefinder.R;
import com.example.imagefinder.data.model.Thumbnail;

public class ThumbnailAdapter extends ListAdapter<Thumbnail, ThumbnailViewHolder> {

    public ThumbnailAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public ThumbnailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ThumbnailViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_thumbnail, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ThumbnailViewHolder holder, int position) {
        holder.getBinding().setThumbnail(getItem(position));
    }

    private static final DiffUtil.ItemCallback<Thumbnail> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Thumbnail>() {
                @Override
                public boolean areItemsTheSame(@NonNull Thumbnail oldItem, @NonNull Thumbnail newItem) {
                    return false;
                }

                @Override
                public boolean areContentsTheSame(@NonNull Thumbnail oldItem, @NonNull Thumbnail newItem) {
                    return false;
                }
            };
}
