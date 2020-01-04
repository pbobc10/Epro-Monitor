package com.example.cbpierre.epromonitor.viewModels;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;

import com.example.cbpierre.epromonitor.models.GHJourContactProduit;
import com.example.cbpierre.epromonitor.models.JoinProduitAcceptabliliteGHProduit;
import com.example.cbpierre.epromonitor.models.Produit;
import com.example.cbpierre.epromonitor.repositories.GHJourContactProduitRepository;

import java.util.List;

public class GHJourContactProduitViewModel extends AndroidViewModel {
    private GHJourContactProduitRepository ghJourContactProduitRepository;
    private GHJourContactProduitRepository.OnGHJourContactProduitListener onGHJourContactProduitListener;
    private MutableLiveData<GHJouContactProduitParam> ghJouContactProduitParamMutable = new MutableLiveData<>();

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

    public void deleteGHJourContactProduitId(int ghId, int conId, String jour, int produitId) {
        ghJourContactProduitRepository.deleteGHJourContactProduitId(ghId, conId, jour, produitId);
    }

    public void getGHJourContactProduit() {
        ghJourContactProduitRepository.setOnGHJourContactProduitListener(onGHJourContactProduitListener);
        ghJourContactProduitRepository.getGHJourContactProduitList();
    }

    public void setOnGHJourContactProduitListener(GHJourContactProduitRepository.OnGHJourContactProduitListener onGHJourContactProduitListener) {
        this.onGHJourContactProduitListener = onGHJourContactProduitListener;
    }

    public void setGhJouContactProduitParamMutable(int ghId, int conId, String jour) {
        GHJouContactProduitParam produitParam = new GHJouContactProduitParam(ghId, conId, jour);
        ghJouContactProduitParamMutable.setValue(produitParam);
    }

    public LiveData<List<JoinProduitAcceptabliliteGHProduit>> getJoinProduitAcceptabliliteGHProduitt() {
        return Transformations.switchMap(ghJouContactProduitParamMutable, new Function<GHJouContactProduitParam, LiveData<List<JoinProduitAcceptabliliteGHProduit>>>() {
            @Override
            public LiveData<List<JoinProduitAcceptabliliteGHProduit>> apply(GHJouContactProduitParam input) {
                return ghJourContactProduitRepository.getProduitAcceptabliliteGHProduit(input.ghId, input.conId, input.jour);
            }
        });
    }

    public LiveData<List<Produit>> getAllGhJourContactProduit() {
        return Transformations.switchMap(ghJouContactProduitParamMutable, new Function<GHJouContactProduitParam, LiveData<List<Produit>>>() {
            @Override
            public LiveData<List<Produit>> apply(GHJouContactProduitParam input) {
                return ghJourContactProduitRepository.getAllGhJourContactProduit(input.ghId, input.jour, input.conId);
            }
        });
    }

   private static class GHJouContactProduitParam {
        private final int ghId, conId;
        private final String jour;

        private GHJouContactProduitParam(int ghId, int conId, String jour) {
            this.ghId = ghId;
            this.conId = conId;
            this.jour = jour;
        }
    }
}
