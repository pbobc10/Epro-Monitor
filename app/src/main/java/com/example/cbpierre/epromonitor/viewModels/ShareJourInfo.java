package com.example.cbpierre.epromonitor.viewModels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.cbpierre.epromonitor.models.JoinGHJourStatutRef;

public class ShareJourInfo extends ViewModel {
    private final MutableLiveData<JoinGHJourStatutRef> ghJourInfoMutableLiveData = new MutableLiveData<>();

    public void setGhJourInfo(JoinGHJourStatutRef ghJourInfo) {
        ghJourInfoMutableLiveData.setValue(ghJourInfo);
    }

    public LiveData<JoinGHJourStatutRef> getGHJourInfoMutableLiveData() {
        return ghJourInfoMutableLiveData;
    }
}

