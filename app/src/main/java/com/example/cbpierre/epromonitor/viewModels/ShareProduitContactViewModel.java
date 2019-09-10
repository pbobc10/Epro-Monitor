package com.example.cbpierre.epromonitor.viewModels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.cbpierre.epromonitor.models.JoinContactPaContact;

public class ShareProduitContactViewModel extends ViewModel {

    private final MutableLiveData<JoinContactPaContact> produitContact = new MutableLiveData<>();

    public void setProduitContact(JoinContactPaContact paContact) {
        produitContact.setValue(paContact);
    }

    public LiveData<JoinContactPaContact> getProduitContact() {
        return produitContact;
    }

}
