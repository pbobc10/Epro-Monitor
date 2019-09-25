package com.example.cbpierre.epromonitor.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.cbpierre.epromonitor.models.StatutVisiteRef;
import com.example.cbpierre.epromonitor.repositories.StatutVisiteRepository;

import java.util.List;

public class StatutVisiteViewModel extends AndroidViewModel {
    private StatutVisiteRepository statutVisiteRepository;

    public StatutVisiteViewModel(@NonNull Application application) {
        super(application);
        statutVisiteRepository = new StatutVisiteRepository(application);
    }

    public LiveData<List<StatutVisiteRef>> getAllStatutVisite() {
        return statutVisiteRepository.getAllStatutVisite();
    }

    public void insertStatutVisite(StatutVisiteRef statutVisiteRef) {
        statutVisiteRepository.insertStatutVisite(statutVisiteRef);
    }

    public void deleteStatutVisite() {
        statutVisiteRepository.deleteStatutVisite();
    }
}
