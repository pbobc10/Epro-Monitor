package com.example.cbpierre.epromonitor.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.cbpierre.epromonitor.models.StatutVisiteRef;
import com.example.cbpierre.epromonitor.repositories.StatutVisiteRepository;

public class StatutVisiteViewModel extends AndroidViewModel {
    private StatutVisiteRepository statutVisiteRepository;

    public StatutVisiteViewModel(@NonNull Application application) {
        super(application);
        statutVisiteRepository = new StatutVisiteRepository(application);
    }

    public void insertStatutVisite(StatutVisiteRef statutVisiteRef) {
        statutVisiteRepository.insertStatutVisite(statutVisiteRef);
    }

    public void deleteStatutVisite() {
        statutVisiteRepository.deleteStatutVisite();
    }
}
