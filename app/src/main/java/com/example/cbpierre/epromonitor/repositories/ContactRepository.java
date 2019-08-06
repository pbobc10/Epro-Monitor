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
    private LiveData<List<Contact>> newContactById;
    private static OnNewcontactListener contactListener;


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

    public LiveData<List<Contact>> getNewContactById(Integer newContactId) {
        return contactDao.getNewContactById(newContactId);
    }

    public void insertContact(Contact newContact) {
        new InsertContactTask(contactDao).execute(newContact);
    }

    public void setOnNewContact(OnNewcontactListener contactListener) {
        this.contactListener = contactListener;
    }

    public void getNewContact() {
        new NewContactTask(contactDao).execute();
    }

    public void deleteNewcontactAfterSyncTask() {
        new DeleteNewcontactAfterSyncTask(contactDao).execute();
    }

    public void updateNewContact(Contact contact) {
        new UpdateNewContactTask(contactDao).execute(contact);
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

    /**
     * new Contact
     */
    private static class NewContactTask extends AsyncTask<Void, Void, List<Contact>> {
        private ContactDao contactDao;

        public NewContactTask(ContactDao contactDao) {
            this.contactDao = contactDao;
        }

        @Override
        protected List<Contact> doInBackground(Void... voids) {
            return contactDao.getNewContacts();
        }

        @Override
        protected void onPostExecute(List<Contact> contacts) {
            super.onPostExecute(contacts);
            if (contactListener != null)
                contactListener.newContact(contacts);
        }
    }

    private static class DeleteNewcontactAfterSyncTask extends AsyncTask<Void, Void, Void> {
        private ContactDao contactDao;

        public DeleteNewcontactAfterSyncTask(ContactDao contactDao) {
            this.contactDao = contactDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            contactDao.deleteNewcontactAfterSync();
            return null;
        }
    }

    private static class UpdateNewContactTask extends AsyncTask<Contact, Void, Void> {
        private ContactDao contactDao;

        public UpdateNewContactTask(ContactDao contactDao) {
            this.contactDao = contactDao;
        }

        @Override
        protected Void doInBackground(Contact... contacts) {
            contactDao.updateNewContact(contacts[0]);
            return null;
        }
    }

    public interface OnNewcontactListener {
        void newContact(List<Contact> contacts);
    }
}
