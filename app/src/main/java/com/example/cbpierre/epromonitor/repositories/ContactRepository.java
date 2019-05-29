package com.example.cbpierre.epromonitor.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import com.example.cbpierre.epromonitor.EproMonitorRoomDatabase;
import com.example.cbpierre.epromonitor.dao.ContactDao;
import com.example.cbpierre.epromonitor.models.CompleteContact;
import com.example.cbpierre.epromonitor.models.Contact;

import java.util.List;

public class ContactRepository {
    private ContactDao contactDao;
    private LiveData<List<Contact>> mAllContact;
    private LiveData<List<CompleteContact>> completeContact;


    public ContactRepository(Application application) {
        EproMonitorRoomDatabase db = EproMonitorRoomDatabase.getDatabase(application);
        contactDao = db.contactDao();
        mAllContact = contactDao.getAllContacts();
        completeContact = contactDao.getAllCompleteContacts();
    }


    public LiveData<List<Contact>> getmAllContacts() {
        return mAllContact;
    }

    public LiveData<List<CompleteContact>> getCompleteContact() {
        return completeContact;
    }

    public LiveData<List<CompleteContact>> getContactNomRepo(String nom) {
        return contactDao.getContactByNom(nom);
    }

    public void insertContact(Contact newContact) {
        new InsertContactTask(contactDao).execute(newContact);
    }


    //****************************** ASYNC CLASS*******************************************
    private static class InsertContactTask extends AsyncTask<Contact, Void, Void> {

        private ContactDao mContact;

        public InsertContactTask(ContactDao mContactDao) {
            this.mContact = mContactDao;
        }

        @Override
        protected Void doInBackground(Contact... contacts) {
            mContact.insert(contacts[0]);
            return null;
        }
    }
}
