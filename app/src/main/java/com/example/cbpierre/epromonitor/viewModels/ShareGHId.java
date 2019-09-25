package com.example.cbpierre.epromonitor.viewModels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class ShareGHId extends ViewModel {
    private final MutableLiveData<Integer> ghIdMutable = new MutableLiveData<>();

    public void setGhId(Integer mGhId) {
        ghIdMutable.setValue(mGhId);
    }

    public LiveData<Integer> getGHId() {
        return ghIdMutable;
    }
}
