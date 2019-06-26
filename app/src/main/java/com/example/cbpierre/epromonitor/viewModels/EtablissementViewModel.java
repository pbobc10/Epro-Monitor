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
import com.example.cbpierre.epromonitor.models.JoinNewEtabNewContact;
import com.example.cbpierre.epromonitor.repositories.EtablissementRepository.*;
import com.example.cbpierre.epromonitor.repositories.EtablissementRepository;

import java.util.List;

public class EtablissementViewModel extends AndroidViewModel {
    private LiveData<List<Etablissement>> allEtablissement;
    private LiveData<List<CompleteEtablissement>> allCompleteEtablissement;
    private LiveData<List<Etablissement>> etabsLocalite;
    private MutableLiveData<Integer> maxEtab;
    private EtablissementRepository etablissementRepository;
    private MutableLiveData<String> completeEtabsByNom = new MutableLiveData<>();
    private MutableLiveData<String> etabByLocalite = new MutableLiveData<>();
    private MutableLiveData<Integer> newEtabByContactId = new MutableLiveData<>();
    private MutableLiveData<Integer> oldEtabByContactId = new MutableLiveData<>();
    private EtablissementRepository.OnOldEtadListener onOldEtadListener;
    private EtablissementRepository.OnNewEtabListener onNewEtabListener;

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

        //maxetab
        maxEtab = etablissementRepository.getMaxEtabMutable();
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

   /* //new Etab MutableLiveData
    public LiveData<List<JoinNewEtabNewContact>> getNewEtabByContactId() {
        return Transformations.switchMap(newEtabByContactId, new Function<Integer, LiveData<List<JoinNewEtabNewContact>>>() {
            @Override
            public LiveData<List<JoinNewEtabNewContact>> apply(Integer input) {
                return etablissementRepository.getNewEtablissementByContactId(input);
            }
        });
    }

    public void setNewEtabByContactId(Integer contactId) {
        newEtabByContactId.setValue(contactId);
    }
    //Old Etab MutableLiveData
    public LiveData<List<Integer>> getOldEtabByContactId() {
        return Transformations.switchMap(oldEtabByContactId, new Function<Integer, LiveData<List<Integer>>>() {
            @Override
            public LiveData<List<Integer>> apply(Integer input) {
                return getOldEtabByContactId();
            }
        });
    }

    public void setOldEtabByContactId(Integer contactId) {
        oldEtabByContactId.setValue(contactId);
    }*/

    public void insertEtablissement(Etablissement etablissement) {
        etablissementRepository.insertEtablissement(etablissement);
    }


    //maxetab
    public void finMaxEtab() {
        etablissementRepository.getMaxEtab();
    }

    public MutableLiveData<Integer> getMaxEtab() {
        return maxEtab;
    }

    /**
     * method interface
     */
    public void setOnOldEtadListener(EtablissementRepository.OnOldEtadListener onOldEtadListener) {
        this.onOldEtadListener = onOldEtadListener;
    }

    public void setOnNewEtabListener(EtablissementRepository.OnNewEtabListener onNewEtabListener) {
        this.onNewEtabListener = onNewEtabListener;
    }

    public void getNewEtabs() {
        etablissementRepository.setOnNewEtabListener(onNewEtabListener);
        etablissementRepository.getNewEtabs();
    }

    public void getOldEtabs() {
        etablissementRepository.setOnOldEtadListener(onOldEtadListener);
        etablissementRepository.getOldEtabs();
    }
}
