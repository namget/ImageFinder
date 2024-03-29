package com.example.imagefinder.ui.home;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.example.imagefinder.R;
import com.example.imagefinder.adapter.ViewPagerAdapter;
import com.example.imagefinder.databinding.ActivityHomeBinding;
import com.example.imagefinder.ui.base.BaseActivity;

public class HomeActivity extends BaseActivity<ActivityHomeBinding> {

    @Nullable
    private HomeViewModel homeViewModel;

    public HomeActivity() {
        super(R.layout.activity_home);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        homeViewModel = getViewModel(HomeViewModel.class);
        binding.setVm(homeViewModel);

        setupViewPager();
    }

    private void setupViewPager() {
        binding.vpCoinList.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        binding.vpCoinList.setOffscreenPageLimit(2);
        binding.tlCoinList.setupWithViewPager(binding.vpCoinList);
    }
}
