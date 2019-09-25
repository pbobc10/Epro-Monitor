package com.example.cbpierre.epromonitor.viewModels;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;

import com.example.cbpierre.epromonitor.models.GHJourContact;
import com.example.cbpierre.epromonitor.models.JoinContactGhSV;
import com.example.cbpierre.epromonitor.repositories.GHJourContactRepository;

import java.util.List;

public class GHJourContactViewModel extends AndroidViewModel {
    private GHJourContactRepository ghJourContactRepository;
    private MutableLiveData<String> allJourContact = new MutableLiveData<>();

    public GHJourContactViewModel(@NonNull Application application) {
        super(application);
        ghJourContactRepository = new GHJourContactRepository(application);
    }

    public void insertGHJourContact(GHJourContact ghJourContact) {
        ghJourContactRepository.insertGHJourContact(ghJourContact);
    }

    public void deleteGHJourContact() {
        ghJourContactRepository.deleteGHJourContact();
    }

    public LiveData<List<JoinContactGhSV>> getAllJourContact() {
        return Transformations.switchMap(allJourContact, new Function<String, LiveData<List<JoinContactGhSV>>>() {
            @Override
            public LiveData<List<JoinContactGhSV>> apply(String input) {
                return ghJourContactRepository.getAllJourConact(input);
            }
        });
    }

    public void setAllJourContactMutable(String jour) {
        allJourContact.setValue(jour);
    }

    public void deleteJourContact(Integer ghId, Integer conId) {
        ghJourContactRepository.deleteJourContact(ghId, conId);
    }

    public void updateGHJourContact(String statut, String note, String creePar, String creeLe, String modifiePar, String modifieLe, String jour, String ghId, String conId) {
        ghJourContactRepository.updateGHJourContact(statut, note, creePar, creeLe, modifiePar, modifieLe, jour, ghId, conId);
    }
}
