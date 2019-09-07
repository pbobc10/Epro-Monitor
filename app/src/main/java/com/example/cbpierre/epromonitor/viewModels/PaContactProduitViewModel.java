package com.example.cbpierre.epromonitor.viewModels;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;

import com.example.cbpierre.epromonitor.models.PaContactProduit;
import com.example.cbpierre.epromonitor.models.Produit;
import com.example.cbpierre.epromonitor.repositories.PaContactProduitRepository;

import java.util.List;

public class PaContactProduitViewModel extends AndroidViewModel {
    private PaContactProduitRepository paContactProduitRepository;
    private MutableLiveData<Integer> produitByContactId = new MutableLiveData<>();

    public PaContactProduitViewModel(@NonNull Application application) {
        super(application);
        paContactProduitRepository = new PaContactProduitRepository(application);
    }

    public LiveData<List<Produit>> getProduitByContactId() {
        return Transformations.switchMap(produitByContactId, new Function<Integer, LiveData<List<Produit>>>() {
            @Override
            public LiveData<List<Produit>> apply(Integer input) {
                return paContactProduitRepository.getAllProduit(input);
            }
        });
    }

    public void setProduitByContactId(Integer contactId) {
        produitByContactId.setValue(contactId);
    }

    public void insertPaContactProduit(PaContactProduit paContactProduit) {
        paContactProduitRepository.insertPaContactProduit(paContactProduit);
    }
}
