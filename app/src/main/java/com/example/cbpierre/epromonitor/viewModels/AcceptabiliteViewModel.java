package com.example.cbpierre.epromonitor.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.cbpierre.epromonitor.models.AcceptabiliteRef;
import com.example.cbpierre.epromonitor.repositories.AcceptabiliteRepository;

import java.util.List;

public class AcceptabiliteViewModel extends AndroidViewModel {
    private AcceptabiliteRepository acceptabiliteRepository;

    public AcceptabiliteViewModel(@NonNull Application application) {
        super(application);
        acceptabiliteRepository = new AcceptabiliteRepository(application);
    }

    public LiveData<List<AcceptabiliteRef>> getAllAcceptabiliteRef() {
        return acceptabiliteRepository.getAllAcceptabiliteRef();
    }

    public void incertAcceptabilite(AcceptabiliteRef... acceptabiliteRef) {
        acceptabiliteRepository.insertAcceptabilite(acceptabiliteRef);
    }

    public void deleteAcceptabilite() {
        acceptabiliteRepository.deleteAcceptabilite();
    }
}
