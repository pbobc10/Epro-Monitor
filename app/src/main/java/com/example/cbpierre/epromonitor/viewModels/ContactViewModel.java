package com.example.cbpierre.epromonitor.viewModels;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;

import com.example.cbpierre.epromonitor.models.CompleteContact;
import com.example.cbpierre.epromonitor.models.Contact;
import com.example.cbpierre.epromonitor.repositories.ContactRepository;
import com.example.cbpierre.epromonitor.repositories.EtablissementRepository;

import java.util.List;

public class ContactViewModel extends AndroidViewModel {
    private ContactRepository contactRepository;
    private LiveData<List<Contact>> mAllContact;
    private LiveData<List<Contact>> mAllNewContact;
    private MutableLiveData<String> contactByNom = new MutableLiveData<>();
    private LiveData<List<CompleteContact>> completeContact;
    private ContactRepository.OnNewcontactListener contactListener;


    public ContactViewModel(@NonNull Application application) {
        super(application);
        contactRepository = new ContactRepository(application);
        mAllNewContact = contactRepository.getAllNewContacts();
        mAllContact = contactRepository.getmAllContacts();
        completeContact = Transformations.switchMap(contactByNom, new Function<String, LiveData<List<CompleteContact>>>() {
            @Override
            public LiveData<List<CompleteContact>> apply(String input) {
                if (input == null || input.equals(""))
                    return contactRepository.getCompleteContact();
                else
                    return contactRepository.getContactNomRepo(input);
            }
        });
    }

    public void setContactByNom(String nom) {
        contactByNom.setValue(nom);
    }

    public LiveData<List<Contact>> getAllContacts() {
        return mAllContact;
    }

    public LiveData<List<Contact>> getAllNewContacts() {
        return mAllNewContact;
    }

    public LiveData<List<CompleteContact>> getCompleteContact() {
        return completeContact;
    }


    public void insertContact(Contact contact) {
        contactRepository.insertContact(contact);
    }

    public void setOnNewContactListener(ContactRepository.OnNewcontactListener contactListener) {
        this.contactListener = contactListener;
    }

    public void getNewContacts() {
        contactRepository.setOnNewContact(contactListener);
        contactRepository.getNewContact();
    }
}
