package com.example.cbpierre.epromonitor.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.cbpierre.epromonitor.models.GHJour;
import com.example.cbpierre.epromonitor.repositories.GHJourRepository;

public class GHJourViewModel extends AndroidViewModel {
    private GHJourRepository ghJourRepository;

    public GHJourViewModel(@NonNull Application application) {
        super(application);
        ghJourRepository = new GHJourRepository(application);
    }

    public void insertGHJour(GHJour ghJour) {
        ghJourRepository.insertGHJour(ghJour);
    }

    public void deleteGHJour() {
        ghJourRepository.deleteGHJour();
    }
}
