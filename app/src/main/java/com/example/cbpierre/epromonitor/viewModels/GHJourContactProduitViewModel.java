package com.example.cbpierre.epromonitor.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.cbpierre.epromonitor.models.GHJourContactProduit;
import com.example.cbpierre.epromonitor.repositories.GHJourContactProduitRepository;

public class GHJourContactProduitViewModel extends AndroidViewModel {
    private GHJourContactProduitRepository ghJourContactProduitRepository;
    private GHJourContactProduitRepository.OnGHJourContactProduitListener onGHJourContactProduitListener;

    public GHJourContactProduitViewModel(@NonNull Application application) {
        super(application);
        ghJourContactProduitRepository = new GHJourContactProduitRepository(application);
    }

    public void insertGHJourContactProduit(GHJourContactProduit ghJourContactProduit) {
        ghJourContactProduitRepository.insertGHJourContactProduit(ghJourContactProduit);
    }

    public void deleteGHJourContactProduit() {
        ghJourContactProduitRepository.deleteGHJourContactProduit();
    }

    public void getGHJourContactProduit() {
        ghJourContactProduitRepository.setOnGHJourContactProduitListener(onGHJourContactProduitListener);
        ghJourContactProduitRepository.getGHJourContactProduitList();
    }

    public void setOnGHJourContactProduitListener(GHJourContactProduitRepository.OnGHJourContactProduitListener onGHJourContactProduitListener) {
        this.onGHJourContactProduitListener = onGHJourContactProduitListener;
    }
}
