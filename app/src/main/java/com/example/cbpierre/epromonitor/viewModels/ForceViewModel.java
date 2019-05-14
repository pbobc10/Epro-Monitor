package com.example.cbpierre.epromonitor.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.cbpierre.epromonitor.models.Force;
import com.example.cbpierre.epromonitor.repositories.ForceRepository;


import java.util.List;

public class ForceViewModel extends AndroidViewModel {
    private LiveData<List<Force>> allForce;
    private ForceRepository forceRepository;
   // private ForceRepository.OnSearchForceListener forceListener;

    public ForceViewModel(@NonNull Application application) {
        super(application);
        forceRepository = new ForceRepository(application);
        allForce = forceRepository.getfAllForce();
    }

    public LiveData<List<Force>> getAllForce() {
        return allForce;
    }

    public void insert(Force force) {
        forceRepository.insert(force);
    }

    /*public void getNomForce(ForceRepository.OnSearchForceListener forceListener) {
        this.forceListener = forceListener;
    }*/
}
