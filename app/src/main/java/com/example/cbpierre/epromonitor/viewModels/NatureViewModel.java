package com.example.cbpierre.epromonitor.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.cbpierre.epromonitor.models.Nature;
import com.example.cbpierre.epromonitor.repositories.NatureRepository;

import java.util.List;

public class NatureViewModel extends AndroidViewModel {
    private LiveData<List<Nature>> mGetAllNature;
    private NatureRepository natureRepository;

    public NatureViewModel(@NonNull Application application) {
        super(application);
        natureRepository = new NatureRepository(application);
        mGetAllNature = natureRepository.getmAllNature();
    }

    public LiveData<List<Nature>> getAllNature() {
        return mGetAllNature;
    }

    public void insertNature(Nature nature) {
        natureRepository.insertNature(nature);
    }
}
