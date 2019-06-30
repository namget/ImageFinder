package com.example.imagefinder.ui.base;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.example.imagefinder.data.remote.RemoteDataSource;
import com.example.imagefinder.ui.ViewModelFactory;

abstract public class BaseActivity<B extends ViewDataBinding> extends AppCompatActivity {

    private final int layoutId;

    @NonNull
    private final RemoteDataSource remoteDataSource;

    protected B binding;

    protected BaseActivity(
            int layoutId,
            @NonNull RemoteDataSource remoteDataSource
    ) {
        this.layoutId = layoutId;
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, layoutId);
        binding.setLifecycleOwner(this);
    }

    protected <VM extends ViewModel> VM getViewModel(@NonNull Class<VM> vmClass) {
        return ViewModelProviders.of(
                this,
                ViewModelFactory.getInstance(remoteDataSource)
        ).get(vmClass);
    }
}
