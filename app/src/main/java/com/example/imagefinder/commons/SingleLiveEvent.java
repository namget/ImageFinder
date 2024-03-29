package com.example.imagefinder.commons;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.concurrent.atomic.AtomicBoolean;

public class SingleLiveEvent<T> extends MutableLiveData<T> {

    @NonNull
    private final AtomicBoolean pending = new AtomicBoolean(false);

    @Override
    public void setValue(T value) {
        pending.set(true);
        super.setValue(value);
    }

    @Override
    public void postValue(T value) {
        synchronized (pending) {
            pending.set(true);
        }
        super.postValue(value);
    }

    @Override
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {
        super.observe(owner, t -> {
                    if (pending.compareAndSet(true, false)) {
                        observer.onChanged(t);
                    }
                }
        );
    }
}
