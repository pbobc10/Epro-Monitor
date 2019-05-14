package com.example.cbpierre.epromonitor.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.cbpierre.epromonitor.models.Titre;
import com.example.cbpierre.epromonitor.repositories.TitreRepository;

import java.util.List;

public class TitreViewModel extends AndroidViewModel {
    private TitreRepository titreRepository;
    private LiveData<List<Titre>> mAllTitre;

    public TitreViewModel(@NonNull Application application) {
        super(application);
        titreRepository = new TitreRepository(application);
        mAllTitre = titreRepository.getmAllTitre();
    }

    public LiveData<List<Titre>> getmAllTitre() {
        return mAllTitre;
    }

    public void insertTitre(Titre titre) {
        titreRepository.insertTitre(titre);
    }
}
