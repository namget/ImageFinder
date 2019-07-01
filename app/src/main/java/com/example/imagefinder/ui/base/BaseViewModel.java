package com.example.imagefinder.ui.base;

import androidx.lifecycle.ViewModel;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

abstract public class BaseViewModel extends ViewModel {

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCleared() {
        compositeDisposable.clear();
        super.onCleared();
    }

    protected CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    protected void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }
}
