package com.example.imagefinder.ui.searchimage;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagedList;
import com.example.imagefinder.R;
import com.example.imagefinder.adapter.ThumbnailPagedListAdapter;
import com.example.imagefinder.data.model.Thumbnail;
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

        getBinding().setVm(searchImageViewModel);

        setupRecyclerView();

        registerEvent();
    }

    private void setupRecyclerView() {
        getBinding().rvSearchedImage.setAdapter(new ThumbnailPagedListAdapter((item, position) -> {
                    if (searchImageViewModel != null) {
                        searchImageViewModel.storeImages(item);
                    }
                })
        );
    }

    private void registerEvent() {
        if (searchImageViewModel != null) {
            getBinding().tvSearch.setOnClickListener(__ -> {
                        if (searchImageViewModel.pagedListLiveData != null &&
                                searchImageViewModel.pagedListLiveData.hasActiveObservers()) {
                            searchImageViewModel.pagedListLiveData.removeObservers(this);
                        }
                        searchImageViewModel.loadImages();
                        searchImageViewModel.pagedListLiveData.observe(this, this::nullCheckSubmitList);
                    }
            );
        }
    }

    private void nullCheckSubmitList(PagedList<Thumbnail> pagedList) {
        ThumbnailPagedListAdapter adapter
                = (ThumbnailPagedListAdapter) getBinding().rvSearchedImage.getAdapter();
        if (adapter != null) {
            adapter.submitList(pagedList);
        }
    }
}
