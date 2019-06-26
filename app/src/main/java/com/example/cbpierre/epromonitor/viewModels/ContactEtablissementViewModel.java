package com.example.cbpierre.epromonitor.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.cbpierre.epromonitor.models.ContactEtablissement;
import com.example.cbpierre.epromonitor.models.JoinContactEtablissementData;
import com.example.cbpierre.epromonitor.repositories.ContactEtablissementRepository;

import java.util.List;

public class ContactEtablissementViewModel extends AndroidViewModel {
    private ContactEtablissementRepository contactEtablissementRepository;
    private MutableLiveData<List<JoinContactEtablissementData>> searchEtabsByContactId;


    public ContactEtablissementViewModel(@NonNull Application application) {
        super(application);
        contactEtablissementRepository = new ContactEtablissementRepository(application);
        searchEtabsByContactId = contactEtablissementRepository.getSearchEtabsByContactId();
    }

    public MutableLiveData<List<JoinContactEtablissementData>> getSearchEtabsByContactId() {
        return searchEtabsByContactId;
    }

    public void insertContactEtablissement(ContactEtablissement contactEtablissement) {
        contactEtablissementRepository.insertContactEtablissement(contactEtablissement);
    }

    public void findContactEtablissement(Integer conId) {
        contactEtablissementRepository.findContactEtabs(conId);
    }
}
