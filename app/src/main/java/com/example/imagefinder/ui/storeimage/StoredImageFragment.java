package com.example.imagefinder.ui.storeimage;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import com.example.imagefinder.R;
import com.example.imagefinder.adapter.ThumbnailAdapter;
import com.example.imagefinder.databinding.FragmnetStoredImageBinding;
import com.example.imagefinder.ui.base.BaseFragment;
import com.example.imagefinder.ui.detail.DetailDialogFragment;
import com.example.imagefinder.ui.detail.DetailViewModel;

import static com.example.imagefinder.commons.Constants.GRID_SPAN_COUNT;

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
        getBinding().rvStoredImage.setAdapter(
                new ThumbnailAdapter(thumbnail -> {
                    DetailDialogFragment detailDialogFragment = DetailDialogFragment.newInstance(thumbnail);
                    detailDialogFragment.ifNotAddedShow(requireFragmentManager());
                })
        );

        getBinding().rvStoredImage.setLayoutManager(new GridLayoutManager(getContext(), GRID_SPAN_COUNT));
    }

    private void registerEvent() {
        if (detailViewModel != null && storedImageViewModel != null) {
            detailViewModel.getIsLocalDataUpdate().observe(this, isUpdate ->
                    storedImageViewModel.updateImages()
            );
        }
    }
}
