package com.example.cbpierre.epromonitor.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.cbpierre.epromonitor.models.ContactVisite;
import com.example.cbpierre.epromonitor.repositories.ContactVisiteRepository;

public class ContactVisiteViewModel extends AndroidViewModel {
    private ContactVisiteRepository contactVisiteRepository;

    public ContactVisiteViewModel(@NonNull Application application) {
        super(application);
        contactVisiteRepository = new ContactVisiteRepository(application);
    }

    public void insertContactVisite(ContactVisite contactVisite) {
        contactVisiteRepository.insetContactVisite(contactVisite);
    }

    public void deleteContactVisite() {
        contactVisiteRepository.deleteCotactVisite();
    }
}
