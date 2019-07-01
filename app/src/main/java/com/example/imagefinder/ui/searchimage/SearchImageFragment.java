package com.example.imagefinder.ui.searchimage;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.imagefinder.R;
import com.example.imagefinder.adapter.ThumbnailAdapter;
import com.example.imagefinder.databinding.FragmnetSearchImageBinding;
import com.example.imagefinder.ui.base.BaseFragment;

public class SearchImageFragment extends BaseFragment<FragmnetSearchImageBinding> {

    @Nullable
    private SearchImageViewModel searchImageViewModel;

    public SearchImageFragment() {
        super(R.layout.fragmnet_search_image);
    }

    @NonNull
    public static SearchImageFragment getInstance() {
        return new SearchImageFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchImageViewModel = getActivityScopeViewModel(SearchImageViewModel.class);

        binding.setVm(searchImageViewModel);

        setupRecyclerView();

    }

    private void setupRecyclerView() {
        binding.rvSearchedImage.setAdapter(new ThumbnailAdapter());

        ThumbnailAdapter adapter = (ThumbnailAdapter) binding.rvSearchedImage.getAdapter();

        if (adapter != null && searchImageViewModel != null) {
            adapter.setOnStoreButtonClickListener((item, position) ->
                    searchImageViewModel.storeImages(position)
            );
        }
    }
}
