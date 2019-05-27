package com.example.cbpierre.epromonitor.viewModels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.cbpierre.epromonitor.models.CompleteContact;

public class SharedViewModel extends ViewModel {

    private final MutableLiveData<CompleteContact> contactMutableLiveData = new MutableLiveData<>();

    public void setContactMutableLiveData(CompleteContact completeContact) {
        contactMutableLiveData.setValue(completeContact);
    }

    public LiveData<CompleteContact> getContactMutableLiveData() {
        return contactMutableLiveData;
    }

}
