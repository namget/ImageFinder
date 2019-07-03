package com.example.imagefinder.ui.searchimage;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;
import com.example.imagefinder.R;
import com.example.imagefinder.adapter.ThumbnailPagedListAdapter;
import com.example.imagefinder.data.model.Thumbnail;
import com.example.imagefinder.databinding.FragmnetSearchImageBinding;
import com.example.imagefinder.ui.base.BaseFragment;
import com.example.imagefinder.ui.detail.DetailDialogFragment;

import static com.example.imagefinder.commons.Constants.GRID_SPAN_COUNT;

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

        searchImageViewModel = getFragmentScopeViewModel(SearchImageViewModel.class);

        getBinding().setVm(searchImageViewModel);

        setupRecyclerView();

        registerEvent();
    }

    private void registerEvent() {
        if (searchImageViewModel != null) {
            searchImageViewModel.pagedListLiveData.observe(this, this::nullCheckSubmitList);

            searchImageViewModel.getIsLoading().observe(this, isLoading -> {
                        if (isLoading) {
                            getBinding().pgSearchImage.setVisibility(View.VISIBLE);
                        } else {
                            getBinding().pgSearchImage.setVisibility(View.INVISIBLE);
                        }
                    }
            );
        }
    }

    private void setupRecyclerView() {
        getBinding().rvSearchedImage.setAdapter(
                new ThumbnailPagedListAdapter((item, position) -> {
                    DetailDialogFragment detailDialogFragment = DetailDialogFragment.newInstance(item);
                    detailDialogFragment.ifNotAddedShow(requireFragmentManager());
                })
        );

        getBinding().rvSearchedImage.setLayoutManager(new GridLayoutManager(getContext(), GRID_SPAN_COUNT));
    }

    private void nullCheckSubmitList(@NonNull PagedList<Thumbnail> pagedList) {
        ThumbnailPagedListAdapter adapter
                = (ThumbnailPagedListAdapter) getBinding().rvSearchedImage.getAdapter();
        if (adapter != null) {
            adapter.submitList(pagedList);
        }
    }
}
