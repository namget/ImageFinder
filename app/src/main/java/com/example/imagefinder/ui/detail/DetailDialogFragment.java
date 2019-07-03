package com.example.imagefinder.ui.detail;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.example.imagefinder.R;
import com.example.imagefinder.data.model.Thumbnail;
import com.example.imagefinder.databinding.DialogFragmnetDetailBinding;
import com.example.imagefinder.ui.base.BaseDialogFragment;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import static com.example.imagefinder.commons.Constants.THUMBNAIL_ARGUMENT_KEY;

public class DetailDialogFragment extends BaseDialogFragment<DialogFragmnetDetailBinding> {

    @Nullable
    private Thumbnail thumbnail;
    @Nullable
    private DetailViewModel detailViewModel;
    public DetailDialogFragment() {
        super(R.layout.dialog_fragmnet_detail);
    }

    @SuppressWarnings("WeakerAccess")
    public static DetailDialogFragment newInstance(@NonNull Thumbnail thumbnail) {
        DetailDialogFragment fragment = new DetailDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(THUMBNAIL_ARGUMENT_KEY, thumbnail);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            Window window = dialog.getWindow();
            if (window != null) {
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }
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
            getBinding().tvStoreButton.setOnClickListener(__ -> detailViewModel.storeImages());

            detailViewModel.getIsUpdateSuccess().observe(this, isUpdate -> {
                        if (isUpdate) {
                            Snackbar.make(
                                    getBinding().getRoot(),
                                    getString(R.string.db_insert_success_message),
                                    Snackbar.LENGTH_SHORT).show();
                        } else {
                            Snackbar.make(
                                    getBinding().getRoot(),
                                    getString(R.string.db_insert_fail_message),
                                    Snackbar.LENGTH_SHORT).show();
                        }
                    }
            );
        }

        getBinding().ivBackButton.setOnClickListener(__ -> dismiss());
    }
}
