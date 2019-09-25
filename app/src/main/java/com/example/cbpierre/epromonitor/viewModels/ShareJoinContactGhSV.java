package com.example.cbpierre.epromonitor.viewModels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.cbpierre.epromonitor.models.JoinContactGhSV;

public class ShareJoinContactGhSV extends ViewModel {
    private MutableLiveData<JoinContactGhSV> joinContactGhSVMutableLiveData = new MutableLiveData<>();

    public void setJoinContactGhSVMutableLiveData(JoinContactGhSV joinContactGhSV) {
        joinContactGhSVMutableLiveData.setValue(joinContactGhSV);
    }

    public LiveData<JoinContactGhSV> getShareJoinContactGhSV() {
        return joinContactGhSVMutableLiveData;
    }
}
