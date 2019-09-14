package com.example.cbpierre.epromonitor.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.cbpierre.epromonitor.models.PaContact;
import com.example.cbpierre.epromonitor.repositories.PaContactRepository;

import java.util.List;

public class PaContactViewModel extends AndroidViewModel {
    private PaContactRepository paContactRepository;
    private LiveData<List<PaContact>> allPaContact;

    public PaContactViewModel(@NonNull Application application) {
        super(application);
        paContactRepository = new PaContactRepository(application);
        allPaContact = paContactRepository.getAllPacontact();
    }

    public void insertPaContact(PaContact paContact) {
        paContactRepository.insertPaContact(paContact);
    }

    public void deletePaContact() {
        paContactRepository.deletePaContact();
    }
}
