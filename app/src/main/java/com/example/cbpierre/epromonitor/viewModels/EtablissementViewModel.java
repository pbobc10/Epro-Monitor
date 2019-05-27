package com.example.cbpierre.epromonitor.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.cbpierre.epromonitor.models.CompleteEtablissement;
import com.example.cbpierre.epromonitor.models.Etablissement;
import com.example.cbpierre.epromonitor.repositories.EtablissementRepository;

import java.util.List;

public class EtablissementViewModel extends AndroidViewModel {
    private LiveData<List<Etablissement>> allEtablissement;
    private LiveData<List<CompleteEtablissement>> allCompleteEtablissement;
    private EtablissementRepository etablissementRepository;

    public EtablissementViewModel(@NonNull Application application) {
        super(application);
        etablissementRepository = new EtablissementRepository(application);
        allEtablissement = etablissementRepository.getAllEtablissement();
        allCompleteEtablissement = etablissementRepository.getAllCompleteEtablissement();
    }

    public LiveData<List<Etablissement>> getAllEtablissement() {
        return allEtablissement;
    }

    public LiveData<List<CompleteEtablissement>> getAllCompleteEtablissement() {
        return allCompleteEtablissement;
    }

    public void insertEtablissement(Etablissement etablissement) {
        etablissementRepository.insertEtablissement(etablissement);
    }
}
