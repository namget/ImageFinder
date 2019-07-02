package com.example.imagefinder.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import com.example.imagefinder.R;
import com.example.imagefinder.data.model.Thumbnail;
import com.example.imagefinder.utils.TextUtils;

public class ThumbnailPagedListAdapter extends PagedListAdapter<Thumbnail, ThumbnailViewHolder> {

    private static final DiffUtil.ItemCallback<Thumbnail> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Thumbnail>() {
                @Override
                public boolean areItemsTheSame(@NonNull Thumbnail oldItem,
                                               @NonNull Thumbnail newItem) {
                    return TextUtils.isEquals(oldItem.getImageUri(), newItem.getImageUri());
                }

                @Override
                public boolean areContentsTheSame(@NonNull Thumbnail oldItem,
                                                  @NonNull Thumbnail newItem) {
                    return oldItem.equals(newItem);
                }
            };
    @Nullable
    private OnStoreButtonClickListener onStoreButtonClickListener;

    public ThumbnailPagedListAdapter(@Nullable OnStoreButtonClickListener onStoreButtonClickListener) {
        super(DIFF_CALLBACK);
        this.onStoreButtonClickListener = onStoreButtonClickListener;
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
        holder.getBinding().executePendingBindings();
    }

    public interface OnStoreButtonClickListener {
        void onStoreButtonClick(Thumbnail thumbnail, int position);
    }
}
