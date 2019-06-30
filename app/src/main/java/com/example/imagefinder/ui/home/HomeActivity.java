package com.example.imagefinder.ui.home;

import android.os.Bundle;
import com.example.imagefinder.R;
import com.example.imagefinder.data.remote.RemoteDataSourceImpl;
import com.example.imagefinder.ui.base.BaseActivity;

public class HomeActivity extends BaseActivity {

    public HomeActivity() {
        super(R.layout.activity_main, RemoteDataSourceImpl.getInstance());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
