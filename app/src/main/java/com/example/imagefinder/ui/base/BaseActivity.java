package com.example.imagefinder.ui.base;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.example.imagefinder.ui.ViewModelFactory;

public abstract class BaseActivity<B extends ViewDataBinding> extends AppCompatActivity {

    private final int layoutId;

    protected B binding;

    protected BaseActivity(int layoutId) {
        this.layoutId = layoutId;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, layoutId);
        binding.setLifecycleOwner(this);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    protected <VM extends ViewModel> VM getViewModel(@NonNull Class<VM> vmClass) {
        return ViewModelProviders.of(this, ViewModelFactory.getInstance()).get(vmClass);
    }
}
