package com.example.cbpierre.epromonitor.viewModels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class ShareProduitContactViewModel extends ViewModel {

    private final MutableLiveData<Integer> produitContact = new MutableLiveData<>();

    public void setProduitContact(Integer conId) {
        produitContact.setValue(conId);
    }

    public LiveData<Integer> getProduitContact() {
        return produitContact;
    }

}
