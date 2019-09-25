package com.example.cbpierre.epromonitor.viewModels;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;

import com.example.cbpierre.epromonitor.models.GHJour;
import com.example.cbpierre.epromonitor.models.JoinGHJourStatutRef;
import com.example.cbpierre.epromonitor.repositories.GHJourRepository;

import java.util.List;

public class GHJourViewModel extends AndroidViewModel {
    private GHJourRepository ghJourRepository;
    private MutableLiveData<Integer> ghId = new MutableLiveData<>();

    public GHJourViewModel(@NonNull Application application) {
        super(application);
        ghJourRepository = new GHJourRepository(application);
    }

    public LiveData<List<JoinGHJourStatutRef>> getAllJour() {
        return Transformations.switchMap(ghId, new Function<Integer, LiveData<List<JoinGHJourStatutRef>>>() {
            @Override
            public LiveData<List<JoinGHJourStatutRef>> apply(Integer input) {
                return ghJourRepository.getAllJour(input);
            }
        });
    }

    public void setGhId(Integer mGhId) {
        ghId.setValue(mGhId);
    }

    public void insertGHJour(GHJour ghJour) {
        ghJourRepository.insertGHJour(ghJour);
    }

    public void deleteGHJour() {
        ghJourRepository.deleteGHJour();
    }
}
