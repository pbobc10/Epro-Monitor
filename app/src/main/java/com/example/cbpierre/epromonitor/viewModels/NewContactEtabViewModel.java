package com.example.cbpierre.epromonitor.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.cbpierre.epromonitor.models.NewContactETab;
import com.example.cbpierre.epromonitor.repositories.NewContactEtabRepository;

public class NewContactEtabViewModel extends AndroidViewModel {
    private NewContactEtabRepository contactEtabRepository;

    public NewContactEtabViewModel(@NonNull Application application) {
        super(application);
        contactEtabRepository = new NewContactEtabRepository(application);
    }

    public void insert(NewContactETab contactETab) {
        contactEtabRepository.insert(contactETab);
    }
}
