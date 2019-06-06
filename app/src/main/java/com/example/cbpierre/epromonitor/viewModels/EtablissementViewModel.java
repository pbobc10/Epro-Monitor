package com.example.cbpierre.epromonitor.viewModels;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;

import com.example.cbpierre.epromonitor.models.CompleteEtablissement;
import com.example.cbpierre.epromonitor.models.Etablissement;
import com.example.cbpierre.epromonitor.repositories.EtablissementRepository;

import java.util.List;

public class EtablissementViewModel extends AndroidViewModel {
    private LiveData<List<Etablissement>> allEtablissement;
    private LiveData<List<CompleteEtablissement>> allCompleteEtablissement;
    private LiveData<List<Etablissement>> etabsLocalite;
    private EtablissementRepository etablissementRepository;
    private MutableLiveData<String> completeEtabsByNom = new MutableLiveData<>();
    private MutableLiveData<String> etabByLocalite = new MutableLiveData<>();

    public EtablissementViewModel(@NonNull Application application) {
        super(application);
        etablissementRepository = new EtablissementRepository(application);
        allEtablissement = etablissementRepository.getAllEtablissement();
        allCompleteEtablissement = Transformations.switchMap(completeEtabsByNom, new Function<String, LiveData<List<CompleteEtablissement>>>() {
            @Override
            public LiveData<List<CompleteEtablissement>> apply(String input) {
                if (input == null || input.equals(""))
                    return etablissementRepository.getAllCompleteEtablissement();
                else
                    return etablissementRepository.getCompleteEtabsByNom(input);
            }
        });

        etabsLocalite = Transformations.switchMap(etabByLocalite, new Function<String, LiveData<List<Etablissement>>>() {
            @Override
            public LiveData<List<Etablissement>> apply(String input) {
                return etablissementRepository.getEtabByLocalite(input);
            }
        });
    }

    public void setEtabByLocalite(String localite) {
        etabByLocalite.setValue(localite);
    }

    public LiveData<List<Etablissement>> getEtabByLocalite() {
        return etabsLocalite;
    }

    public void setCompleteEtabsByNom(String nom) {
        completeEtabsByNom.setValue(nom);
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
