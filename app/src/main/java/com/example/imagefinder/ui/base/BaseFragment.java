package com.example.imagefinder.ui.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.example.imagefinder.ui.ViewModelFactory;

public class BaseFragment<B extends ViewDataBinding> extends Fragment {

    private final int layoutId;

    protected B binding;

    public BaseFragment(int layoutId) {
        this.layoutId = layoutId;
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    protected <VM extends ViewModel> VM getFragmentScopeViewModel(@NonNull Class<VM> vmClass) {
        return ViewModelProviders.of(this, ViewModelFactory.getInstance()).get(vmClass);
    }
}
