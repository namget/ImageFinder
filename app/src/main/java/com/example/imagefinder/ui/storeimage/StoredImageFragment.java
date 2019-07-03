package com.example.imagefinder.ui.storeimage;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.imagefinder.R;
import com.example.imagefinder.adapter.ThumbnailAdapter;
import com.example.imagefinder.databinding.FragmnetStoredImageBinding;
import com.example.imagefinder.ui.base.BaseFragment;
import com.example.imagefinder.ui.detail.DetailViewModel;
import com.example.imagefinder.ui.searchimage.SearchImageViewModel;

public class StoredImageFragment extends BaseFragment<FragmnetStoredImageBinding> {

    @Nullable
    private StoredImageViewModel storedImageViewModel;

    @Nullable
    private DetailViewModel detailViewModel;

    public StoredImageFragment() {
        super(R.layout.fragmnet_stored_image);
    }

    @NonNull
    public static StoredImageFragment getInstance() {
        return new StoredImageFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        storedImageViewModel = getFragmentScopeViewModel(StoredImageViewModel.class);
        detailViewModel = getActivityScopeViewModel(DetailViewModel.class);

        getBinding().setVm(storedImageViewModel);

        setupRecyclerView();

        registerEvent();
    }

    private void setupRecyclerView() {
        getBinding().rvStoredImage.setAdapter(new ThumbnailAdapter());

        ThumbnailAdapter adapter = (ThumbnailAdapter) getBinding().rvStoredImage.getAdapter();

        if (adapter != null && storedImageViewModel != null) {
            adapter.setOnStoreButtonClickListener((item, position) ->
                    storedImageViewModel.deleteImages(position)
            );
        }
    }

    private void registerEvent() {
        if (detailViewModel != null && storedImageViewModel != null) {
            detailViewModel.getIsLocalDataUpdate().observe(this, t ->
                    storedImageViewModel.updateImages());
        }
    }
}
