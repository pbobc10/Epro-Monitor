package com.example.cbpierre.epromonitor.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.cbpierre.epromonitor.models.Secteur;
import com.example.cbpierre.epromonitor.repositories.SecteurRepository;

import java.util.List;

public class SecteurViewModel extends AndroidViewModel {
    private SecteurRepository secteurRepository;
    private LiveData<List<Secteur>> sAllSecteur;

    public SecteurViewModel(@NonNull Application application) {
        super(application);
        secteurRepository = new SecteurRepository(application);
        sAllSecteur = secteurRepository.getmAllSecteur();
    }

    public LiveData<List<Secteur>> getsAllSecteur() {
        return sAllSecteur;
    }

    public void insertSecteur(Secteur secteur) {
        secteurRepository.insertSecteur(secteur);
    }
}
