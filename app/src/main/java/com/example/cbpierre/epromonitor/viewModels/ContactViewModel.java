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
import com.example.cbpierre.epromonitor.models.JoinContactPaContact;
import com.example.cbpierre.epromonitor.repositories.ContactRepository;

import java.util.List;

public class ContactViewModel extends AndroidViewModel {
    private ContactRepository contactRepository;
    private LiveData<List<Contact>> mAllContact;
    private MutableLiveData<String> contactByNom = new MutableLiveData<>();
    private MutableLiveData<String> paContactByNom = new MutableLiveData<>();
    //private LiveData<List<Contact>> mNewContactById;
    private MutableLiveData<Integer> newContactById = new MutableLiveData<>();
    private LiveData<List<CompleteContact>> completeContact;
    private LiveData<List<JoinContactPaContact>> completePaContact;
    private ContactRepository.OnNewcontactListener contactListener;


    public ContactViewModel(@NonNull Application application) {
        super(application);
        contactRepository = new ContactRepository(application);
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

    public LiveData<List<CompleteContact>> getCompleteContact() {
        return completeContact;
    }

    public LiveData<List<Contact>> getNewContactById() {
        return Transformations.switchMap(newContactById, new Function<Integer, LiveData<List<Contact>>>() {
            @Override
            public LiveData<List<Contact>> apply(Integer input) {
                return contactRepository.getNewContactById(input);
            }
        });
    }

    public void setNewContactById(Integer contactById) {
        newContactById.setValue(contactById);
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

    public void deleteNewcontactAfterSyncTask() {
        contactRepository.deleteNewcontactAfterSyncTask();
    }

    public void updateNewContact(Contact contact) {
        contactRepository.updateNewContact(contact);
    }

    /**
     * PA
     */
    public LiveData<List<JoinContactPaContact>> getAllContactPA() {
        return completePaContact = Transformations.switchMap(paContactByNom, new Function<String, LiveData<List<JoinContactPaContact>>>() {
            @Override
            public LiveData<List<JoinContactPaContact>> apply(String input) {
                if (input == null || input.equals(""))
                    return contactRepository.getAllPaContact();
                else
                    return contactRepository.getAllPaContact(input);
            }
        });
    }

    public void setPaContactByNom(String nom) {
        paContactByNom.setValue(nom);
    }

}

