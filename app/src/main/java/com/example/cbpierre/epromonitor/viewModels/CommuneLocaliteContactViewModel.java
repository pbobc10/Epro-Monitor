package com.example.cbpierre.epromonitor.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.cbpierre.epromonitor.models.CommuneLocaliteContact;
import com.example.cbpierre.epromonitor.repositories.CommuneLocaliteContactRepository;

public class CommuneLocaliteContactViewModel extends AndroidViewModel {
    private CommuneLocaliteContactRepository communeLocaliteContactRepository;

    public CommuneLocaliteContactViewModel(@NonNull Application application) {
        super(application);
        communeLocaliteContactRepository = new CommuneLocaliteContactRepository(application);
    }

    public void insertCommuneLocaliteContact(CommuneLocaliteContact communeLocaliteContact) {
        communeLocaliteContactRepository.insertCommuneLocaliteContact(communeLocaliteContact);
    }

    public void deleteCommuneLocaliteContact() {
        communeLocaliteContactRepository.deleteCommuneLocaliteContact();
    }
}
