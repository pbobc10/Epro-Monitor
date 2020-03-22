package com.example.cbpierre.epromonitor.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.cbpierre.epromonitor.models.Recommandation;
import com.example.cbpierre.epromonitor.repositories.RecommandationRepository;

import java.util.List;

public class RecommandationViewModel extends AndroidViewModel {
    private RecommandationRepository recommandationRepository;
    private LiveData<List<Recommandation>> allRecommandation;

    public RecommandationViewModel(@NonNull Application application) {
        super(application);
        recommandationRepository = new RecommandationRepository(application);
        allRecommandation = recommandationRepository.getAllRecommandation();
    }

    public LiveData<List<Recommandation>> getAllRecommandation() {
        return allRecommandation;
    }

    public void insertRecommandation(Recommandation... recommandations) {
        recommandationRepository.insertRecommandation(recommandations);
    }

    public void deleteRecommandation(Recommandation recommandation) {
        recommandationRepository.deleteRecommandation(recommandation);
    }
}
