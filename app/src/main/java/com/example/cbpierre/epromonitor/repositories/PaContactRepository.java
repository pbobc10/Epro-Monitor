package com.example.cbpierre.epromonitor.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.cbpierre.epromonitor.EproMonitorRoomDatabase;
import com.example.cbpierre.epromonitor.dao.PaContactDao;
import com.example.cbpierre.epromonitor.models.PaContact;

import java.util.List;

public class PaContactRepository {
    private PaContactDao paContactDao;
    private LiveData<List<PaContact>> allPacontact;

    public PaContactRepository(Application application) {
        EproMonitorRoomDatabase db = EproMonitorRoomDatabase.getDatabase(application);
        paContactDao = db.paContactDao();
        allPacontact = paContactDao.allPaContact();
    }

    public LiveData<List<PaContact>> getAllPacontact() {
        return allPacontact;
    }

    public void insertPaContact(PaContact paContact) {
        new InsertAlPaContactTask(paContactDao).execute(paContact);
    }

    /**
     * Async Task
     */

    private static class InsertAlPaContactTask extends AsyncTask<PaContact, Void, Void> {
        private PaContactDao paContactDao;

        public InsertAlPaContactTask(PaContactDao paContactDao) {
            this.paContactDao = paContactDao;
        }

        @Override
        protected Void doInBackground(PaContact... paContacts) {
            paContactDao.insertPaContact(paContacts[0]);
            return null;
        }
    }
}
