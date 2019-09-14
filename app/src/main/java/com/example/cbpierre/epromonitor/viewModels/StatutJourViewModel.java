package com.example.cbpierre.epromonitor.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.cbpierre.epromonitor.models.StatutJourRef;
import com.example.cbpierre.epromonitor.repositories.StatutJourRepository;

public class StatutJourViewModel extends AndroidViewModel {
    private StatutJourRepository statutJourRepository;

    public StatutJourViewModel(@NonNull Application application) {
        super(application);
        statutJourRepository = new StatutJourRepository(application);
    }

    public void insertStatutJour(StatutJourRef statutJourRef) {
        statutJourRepository.insertStatutJour(statutJourRef);
    }

    public void deleteStatutJour() {
        statutJourRepository.deleteStatutJour();
    }
}
