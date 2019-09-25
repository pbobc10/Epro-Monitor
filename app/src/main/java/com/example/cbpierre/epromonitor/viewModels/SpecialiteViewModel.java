package com.example.cbpierre.epromonitor.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.cbpierre.epromonitor.models.Specialite;
import com.example.cbpierre.epromonitor.models.SpecialiteGH;
import com.example.cbpierre.epromonitor.repositories.SpecialiteRepository;

import java.util.List;

public class SpecialiteViewModel extends AndroidViewModel {
    private SpecialiteRepository specialiteRepository;
    private LiveData<List<Specialite>> allSpecialite;

    public SpecialiteViewModel(@NonNull Application application) {
        super(application);
        specialiteRepository = new SpecialiteRepository(application);
        allSpecialite = specialiteRepository.getsAllSpecialite();
    }

    public LiveData<List<Specialite>> getAllSpecialite() {
        return allSpecialite;
    }

    public LiveData<List<SpecialiteGH>> getAllSpecialiteGH() {
        return specialiteRepository.getAllSpecialiteGH();
    }

    public void insertSpecialite(Specialite specialite) {
        specialiteRepository.insert(specialite);
    }
}
