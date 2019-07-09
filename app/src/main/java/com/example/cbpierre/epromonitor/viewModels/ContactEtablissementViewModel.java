package com.example.cbpierre.epromonitor.viewModels;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;

import com.example.cbpierre.epromonitor.models.ContactEtablissement;
import com.example.cbpierre.epromonitor.models.JoinContactEtablissementData;
import com.example.cbpierre.epromonitor.repositories.ContactEtablissementRepository;

import java.util.List;

public class ContactEtablissementViewModel extends AndroidViewModel {
    private ContactEtablissementRepository contactEtablissementRepository;
    private MutableLiveData<Integer> etabsByContactId = new MutableLiveData<>();
    private MutableLiveData<Integer> newEtabsByContactId = new MutableLiveData<>();
    private LiveData<List<JoinContactEtablissementData>> getEtabByIdLiveData;
    private LiveData<List<JoinContactEtablissementData>> getNewEtabByIdLiveData;


    public ContactEtablissementViewModel(@NonNull Application application) {
        super(application);
        contactEtablissementRepository = new ContactEtablissementRepository(application);
    }

    /**
     * Etablissement by contact id
     */
    public LiveData<List<JoinContactEtablissementData>> getEtabsByContactId() {
        getEtabByIdLiveData = Transformations.switchMap(etabsByContactId, new Function<Integer, LiveData<List<JoinContactEtablissementData>>>() {
            @Override
            public LiveData<List<JoinContactEtablissementData>> apply(Integer input) {
                return contactEtablissementRepository.getEtabsByContactId(input);
            }
        });
        return getEtabByIdLiveData;
    }

    public void setEtabsByContactId(Integer conid) {
        etabsByContactId.setValue(conid);
    }

    /**
     * New Etablissement by New Contact id
     */
    public LiveData<List<JoinContactEtablissementData>> getNewEtabsByContactId() {
        getNewEtabByIdLiveData = Transformations.switchMap(newEtabsByContactId, new Function<Integer, LiveData<List<JoinContactEtablissementData>>>() {
            @Override
            public LiveData<List<JoinContactEtablissementData>> apply(Integer input) {
                return contactEtablissementRepository.getNewEtabsbyContactid(input);
            }
        });

        return getNewEtabByIdLiveData;
    }

    public void setNewEtabsByContactId(Integer conid) {
        newEtabsByContactId.setValue(conid);
    }

    /**
     * insert Contact Etablissement
     */
    public void insertContactEtablissement(ContactEtablissement contactEtablissement) {
        contactEtablissementRepository.insertContactEtablissement(contactEtablissement);
    }

}
