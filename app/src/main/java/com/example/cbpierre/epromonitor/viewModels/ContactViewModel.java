package com.example.cbpierre.epromonitor.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.cbpierre.epromonitor.models.Contact;
import com.example.cbpierre.epromonitor.repositories.ContactRepository;

import java.util.List;

public class ContactViewModel extends AndroidViewModel {
    private ContactRepository contactRepository;
    private LiveData<List<Contact>> mAllContact;


    public ContactViewModel(@NonNull Application application) {
        super(application);
        contactRepository = new ContactRepository(application);
        mAllContact = contactRepository.getmAllContacts();
    }

    public LiveData<List<Contact>> getAllContacts() {
        return mAllContact;
    }

    public void insertContact(Contact contact){
        contactRepository.insertContact(contact);
    }
}
