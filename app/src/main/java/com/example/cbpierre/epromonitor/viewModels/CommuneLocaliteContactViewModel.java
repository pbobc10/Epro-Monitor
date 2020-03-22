package com.example.cbpierre.epromonitor.viewModels;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;

import com.example.cbpierre.epromonitor.models.CommuneGH;
import com.example.cbpierre.epromonitor.models.CommuneLocaliteContact;
import com.example.cbpierre.epromonitor.models.LocaliteGH;
import com.example.cbpierre.epromonitor.repositories.CommuneLocaliteContactRepository;

import java.util.List;

public class CommuneLocaliteContactViewModel extends AndroidViewModel {
    private CommuneLocaliteContactRepository communeLocaliteContactRepository;
    private MutableLiveData<String> localiteGHMutable = new MutableLiveData<>();

    public CommuneLocaliteContactViewModel(@NonNull Application application) {
        super(application);
        communeLocaliteContactRepository = new CommuneLocaliteContactRepository(application);
    }

    public void insertCommuneLocaliteContact(CommuneLocaliteContact... communeLocaliteContact) {
        communeLocaliteContactRepository.insertCommuneLocaliteContact(communeLocaliteContact);
    }

    public void deleteCommuneLocaliteContact() {
        communeLocaliteContactRepository.deleteCommuneLocaliteContact();
    }

    public LiveData<List<CommuneGH>> getAllCommuneGH() {
        return communeLocaliteContactRepository.getAllCommuneGH();
    }

    public LiveData<List<LocaliteGH>> getAllLocaliteGH() {
        return Transformations.switchMap(localiteGHMutable, new Function<String, LiveData<List<LocaliteGH>>>() {
            @Override
            public LiveData<List<LocaliteGH>> apply(String input) {
                return communeLocaliteContactRepository.getAllLocaliteGH(input);
            }
        });
    }

    public void setLocaliteGHMutable(String localite) {
        localiteGHMutable.setValue(localite);
    }
}
