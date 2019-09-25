package com.example.cbpierre.epromonitor.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.cbpierre.epromonitor.models.GH;
import com.example.cbpierre.epromonitor.repositories.GHRepository;

import java.util.List;

public class GHViewModel extends AndroidViewModel {
    private GHRepository ghRepository;

    public GHViewModel(@NonNull Application application) {
        super(application);
        ghRepository = new GHRepository(application);
    }

    public LiveData<List<GH>> getAllGH() {
        return ghRepository.getAllGH();
    }

    public void insertGH(GH gh) {
        ghRepository.insertGH(gh);
    }

    public void deleteGH() {
        ghRepository.deleteGH();
    }
}
