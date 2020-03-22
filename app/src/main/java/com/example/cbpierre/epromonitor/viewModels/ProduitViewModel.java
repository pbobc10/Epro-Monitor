package com.example.cbpierre.epromonitor.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.cbpierre.epromonitor.models.Produit;
import com.example.cbpierre.epromonitor.repositories.ProduitRepository;

import java.util.List;

public class ProduitViewModel extends AndroidViewModel {
    private ProduitRepository produitRepository;
    private ProduitRepository.ProduiIdListener produiIdListener;
    private LiveData<List<Produit>> allProduit;

    public ProduitViewModel(@NonNull Application application) {
        super(application);
        produitRepository = new ProduitRepository(application);
        allProduit = produitRepository.getAllProduit();
    }

    public void insertProduit(Produit... produit) {
        produitRepository.insertProduit(produit);
    }

    public void deleteProduit() {
        produitRepository.deleteProduit();
    }

    public void produitIdList() {
        produitRepository.setProduiIdListener(produiIdListener);
        produitRepository.produitIdList();
    }

    public void setProduiIdListener(ProduitRepository.ProduiIdListener produiIdListener) {
        this.produiIdListener = produiIdListener;
    }
}
