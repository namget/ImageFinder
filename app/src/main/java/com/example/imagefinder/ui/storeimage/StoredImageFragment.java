package com.example.imagefinder.ui.storeimage;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.imagefinder.R;
import com.example.imagefinder.adapter.ThumbnailAdapter;
import com.example.imagefinder.databinding.FragmnetStoredImageBinding;
import com.example.imagefinder.ui.base.BaseFragment;

public class StoredImageFragment extends BaseFragment<FragmnetStoredImageBinding> {

    @Nullable
    private StoredImageViewModel storedImageViewModel;

    public StoredImageFragment() {
        super(R.layout.fragmnet_stored_image);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        storedImageViewModel = getFragmentScopeViewModel(StoredImageViewModel.class);
        binding.setVm(storedImageViewModel);

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        binding.rvStoredImage.setAdapter(new ThumbnailAdapter());
    }

    @NonNull
    public static StoredImageFragment getInstance() {
        return new StoredImageFragment();
    }
}
