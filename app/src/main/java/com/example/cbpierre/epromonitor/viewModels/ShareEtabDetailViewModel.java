package com.example.cbpierre.epromonitor.viewModels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.cbpierre.epromonitor.models.CompleteEtablissement;

public class ShareEtabDetailViewModel extends ViewModel {
    private final MutableLiveData<CompleteEtablissement> etabMutableLiveData = new MutableLiveData<>();

    public void setEtabMutableLiveData(CompleteEtablissement etab) {
        etabMutableLiveData.setValue(etab);
    }

    public LiveData<CompleteEtablissement> getEtabMutableLiveData() {
        return etabMutableLiveData;
    }
}
