package com.example.imagefinder.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.example.imagefinder.R;
import com.example.imagefinder.data.model.Thumbnail;

import java.util.ArrayList;
import java.util.List;

public class ThumbnailAdapter extends RecyclerView.Adapter<ThumbnailViewHolder> {

    @NonNull
    private final List<Thumbnail> item = new ArrayList<>();

    @Nullable
    private OnItemClickListener onItemClickListener;

    public ThumbnailAdapter(@Nullable OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
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
        holder.getBinding().setThumbnail(item.get(position));
        holder.getBinding().executePendingBindings();

        holder.getBinding().getRoot().setOnClickListener(__ -> {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClickListener(item.get(position), position);
                    }
                }
        );
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public void updateItem(List<Thumbnail> item) {
        this.item.clear();
        this.item.addAll(item);
    }

    public void setOnStoreButtonClickListener(@NonNull OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClickListener(Thumbnail thumbnail, int position);
    }
}
