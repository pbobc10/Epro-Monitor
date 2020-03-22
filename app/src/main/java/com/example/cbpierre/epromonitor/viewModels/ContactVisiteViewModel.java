package com.example.cbpierre.epromonitor.viewModels;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;

import com.example.cbpierre.epromonitor.models.ChoiceContactGH;
import com.example.cbpierre.epromonitor.models.ContactVisite;
import com.example.cbpierre.epromonitor.repositories.ContactVisiteRepository;

import java.util.List;

public class ContactVisiteViewModel extends AndroidViewModel {
    private ContactVisiteRepository contactVisiteRepository;
    private MutableLiveData<ChoiceContactGhParam> contactGhParamMutableLiveData = new MutableLiveData<>();

    public ContactVisiteViewModel(@NonNull Application application) {
        super(application);
        contactVisiteRepository = new ContactVisiteRepository(application);
    }

    public void insertContactVisite(ContactVisite... contactVisite) {
        contactVisiteRepository.insetContactVisite(contactVisite);
    }

    public void deleteContactVisite() {
        contactVisiteRepository.deleteCotactVisite();
    }

    static class ChoiceContactGhParam {
        final String specialite, commune, localite, jour;
        final Integer ghId;

        public ChoiceContactGhParam(String specialite, String commune, String localite, Integer ghId, String jour) {
            this.specialite = specialite;
            this.commune = commune;
            this.localite = localite;
            this.jour = jour;
            this.ghId = ghId;
        }
    }

    public void setContactGhParam(String specialite, String commune, String localite, Integer ghId, String jour) {
        ChoiceContactGhParam choiceContactGhParam = new ChoiceContactGhParam(specialite, commune, localite, ghId, jour);
        contactGhParamMutableLiveData.setValue(choiceContactGhParam);
    }

    public LiveData<List<ChoiceContactGH>> getChoiceContactGH() {
        return Transformations.switchMap(contactGhParamMutableLiveData, new Function<ChoiceContactGhParam, LiveData<List<ChoiceContactGH>>>() {
            @Override
            public LiveData<List<ChoiceContactGH>> apply(ChoiceContactGhParam input) {
                if (input.specialite != null && input.commune == null && input.localite == null && input.ghId != null && input.jour != null)
                    return contactVisiteRepository.getAllChoiceContactGH(input.specialite, input.ghId, input.jour);
                else if (input.specialite != null && input.commune != null && input.localite == null && input.ghId != null && input.jour != null)
                    return contactVisiteRepository.getAllChoiceContactGH(input.specialite, input.commune, input.ghId, input.jour);
                else // (input.specialite != null && input.commune != null && input.localite != null)
                    return contactVisiteRepository.getAllChoiceContactGH(input.specialite, input.commune, input.localite, input.ghId, input.jour);
            }
        });
    }
}
