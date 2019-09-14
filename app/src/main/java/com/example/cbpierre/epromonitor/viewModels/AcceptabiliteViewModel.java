package com.example.cbpierre.epromonitor.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.cbpierre.epromonitor.models.AcceptabiliteRef;
import com.example.cbpierre.epromonitor.repositories.AcceptabiliteRepository;

public class AcceptabiliteViewModel extends AndroidViewModel {
    private AcceptabiliteRepository acceptabiliteRepository;

    public AcceptabiliteViewModel(@NonNull Application application) {
        super(application);
        acceptabiliteRepository = new AcceptabiliteRepository(application);
    }

    public void incertAcceptabilite(AcceptabiliteRef acceptabiliteRef) {
        acceptabiliteRepository.insertAcceptabilite(acceptabiliteRef);
    }

    public void deleteAcceptabilite() {
        acceptabiliteRepository.deleteAcceptabilite();
    }
}
