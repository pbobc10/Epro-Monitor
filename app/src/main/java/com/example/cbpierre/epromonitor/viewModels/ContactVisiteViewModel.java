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

    public void insertContactVisite(ContactVisite contactVisite) {
        contactVisiteRepository.insetContactVisite(contactVisite);
    }

    public void deleteContactVisite() {
        contactVisiteRepository.deleteCotactVisite();
    }

    static class ChoiceContactGhParam {
        final String specialite, commune, localite;

        public ChoiceContactGhParam(String specialite, String commune, String localite) {
            this.specialite = specialite;
            this.commune = commune;
            this.localite = localite;
        }
    }

    public void setContactGhParam(String specialite, String commune, String localite) {
        ChoiceContactGhParam choiceContactGhParam = new ChoiceContactGhParam(specialite, commune, localite);
        contactGhParamMutableLiveData.setValue(choiceContactGhParam);
    }

    public LiveData<List<ChoiceContactGH>> getChoiceContactGH() {
        return Transformations.switchMap(contactGhParamMutableLiveData, new Function<ChoiceContactGhParam, LiveData<List<ChoiceContactGH>>>() {
            @Override
            public LiveData<List<ChoiceContactGH>> apply(ChoiceContactGhParam input) {
                if (input.specialite != null && input.commune == null && input.localite == null)
                    return contactVisiteRepository.getAllChoiceContactGH(input.specialite);
                else if (input.specialite != null && input.commune != null && input.localite == null)
                    return contactVisiteRepository.getAllChoiceContactGH(input.specialite, input.commune);
                else // (input.specialite != null && input.commune != null && input.localite != null)
                    return contactVisiteRepository.getAllChoiceContactGH(input.specialite, input.commune, input.localite);
            }
        });
    }
}
