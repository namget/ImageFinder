package com.example.imagefinder.ui.detail;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.example.imagefinder.R;
import com.example.imagefinder.data.model.Thumbnail;
import com.example.imagefinder.databinding.DialogFragmnetDetailBinding;
import com.example.imagefinder.ui.base.BaseDialogFragment;

import java.util.Objects;

import static com.example.imagefinder.commons.Constants.THUMBNAIL_ARGUMENT_KEY;

public class DetailDialogFragment extends BaseDialogFragment<DialogFragmnetDetailBinding> {

    public DetailDialogFragment() {
        super(R.layout.dialog_fragmnet_detail);
    }

    @Nullable
    private Thumbnail thumbnail;
    @Nullable
    private DetailViewModel detailViewModel;

    @SuppressWarnings("WeakerAccess")
    public static DetailDialogFragment newInstance(@NonNull Thumbnail thumbnail) {
        DetailDialogFragment fragment = new DetailDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(THUMBNAIL_ARGUMENT_KEY, thumbnail);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            thumbnail = Objects.requireNonNull(
                    getArguments().getParcelable(THUMBNAIL_ARGUMENT_KEY),
                    "Illegal Argument State");
        }

        detailViewModel = getActivityScopeViewModel(DetailViewModel.class);
        getBinding().setVm(detailViewModel);

        if (detailViewModel != null && thumbnail != null) {
            detailViewModel.setThumbnail(thumbnail);
        }

        registerEvent();
    }

    private void registerEvent() {
        if (detailViewModel != null) {
            detailViewModel.storeImages();
        }

        getBinding().ivBackButton.setOnClickListener(__ -> dismiss());
    }
}
