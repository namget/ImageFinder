package com.example.imagefinder.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.example.imagefinder.ui.searchimage.SearchImageFragment;
import com.example.imagefinder.ui.storeimage.StoredImageFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    @NonNull
    private final SearchImageFragment searchImageFragment;

    @NonNull
    private final StoredImageFragment storedImageFragment;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        this.searchImageFragment = SearchImageFragment.getInstance();
        this.storedImageFragment = StoredImageFragment.getInstance();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return searchImageFragment;
            case 1:
                return storedImageFragment;
            default:
                throw new IllegalStateException("Exceed pager count");
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "이미지 검색";
            case 1:
                return "보관함";
            default:
                throw new IllegalStateException("Exceed pager count");
        }
    }
}
