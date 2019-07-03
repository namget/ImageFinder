package com.example.imagefinder.ui.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.example.imagefinder.ui.ViewModelFactory;

public abstract class BaseDialogFragment<B extends ViewDataBinding> extends DialogFragment {

    @NonNull
    private final String TAG = getClass().getSimpleName();

    private final int layoutId;

    private B binding;

    public BaseDialogFragment(int layoutId) {
        this.layoutId = layoutId;
    }

    protected B getBinding() {
        return binding;
    }

    public void ifNotAddedShow(@NonNull FragmentManager fragmentManager) {
        Fragment fragment = fragmentManager.findFragmentByTag(TAG);

        if (fragment == null) {
            show(fragmentManager, TAG);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    protected <VM extends ViewModel> VM getFragmentScopeViewModel(@NonNull Class<VM> vmClass) {
        return ViewModelProviders.of(this, ViewModelFactory.getInstance()).get(vmClass);
    }

    protected <VM extends ViewModel> VM getActivityScopeViewModel(@NonNull Class<VM> vmClass) {
        return ViewModelProviders.of(requireActivity(), ViewModelFactory.getInstance()).get(vmClass);
    }
}
