package com.example.cbpierre.epromonitor.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.cbpierre.epromonitor.models.GHJourContact;
import com.example.cbpierre.epromonitor.repositories.GHJourContactRepository;

public class GHJourContactViewModel extends AndroidViewModel {
    private GHJourContactRepository ghJourContactRepository;

    public GHJourContactViewModel(@NonNull Application application) {
        super(application);
        ghJourContactRepository = new GHJourContactRepository(application);
    }

    public void insertGHJourContact(GHJourContact ghJourContact) {
        ghJourContactRepository.insertGHJourContact(ghJourContact);
    }

    public void deleteGHJourContact() {
        ghJourContactRepository.deleteGHJourContact();
    }
}
