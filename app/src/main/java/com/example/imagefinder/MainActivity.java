package com.example.imagefinder;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import com.example.imagefinder.data.remote.RemoteDataSourceImpl;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tv_test).setOnClickListener(v ->
                RemoteDataSourceImpl.getInstance().getThumbnailsByAllSource(1, "AOA")
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(ap -> Log.d("테스트", ap.toString()),
                                error -> Log.e("테스트", error.getMessage()))
        );
    }
}
