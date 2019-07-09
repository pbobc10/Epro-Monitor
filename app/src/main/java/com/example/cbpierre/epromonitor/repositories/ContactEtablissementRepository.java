package com.example.cbpierre.epromonitor.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import com.example.cbpierre.epromonitor.EproMonitorRoomDatabase;
import com.example.cbpierre.epromonitor.dao.ContactEtablissementDao;
import com.example.cbpierre.epromonitor.models.ContactEtablissement;
import com.example.cbpierre.epromonitor.models.JoinContactEtablissementData;

import java.util.List;

public class ContactEtablissementRepository {
    private ContactEtablissementDao contactEtablissementDao;


    public ContactEtablissementRepository(Application application) {
        EproMonitorRoomDatabase database = EproMonitorRoomDatabase.getDatabase(application);
        this.contactEtablissementDao = database.contactEtablissementDao();
    }


    public LiveData<List<JoinContactEtablissementData>> getNewEtabsbyContactid(Integer conId) {
        return contactEtablissementDao.getAllNewEtablissementByContactId(conId);
    }

    public LiveData<List<JoinContactEtablissementData>> getEtabsByContactId(Integer conId) {
        return contactEtablissementDao.getAllEtablissementByContactId(conId);
    }

    public void insertContactEtablissement(ContactEtablissement... contactEtablissement) {
        new InsertContactEtablissementTask(contactEtablissementDao).execute(contactEtablissement);
    }

    /**
     * Async Task
     */

    private static class InsertContactEtablissementTask extends AsyncTask<ContactEtablissement, Void, Void> {
        private ContactEtablissementDao dao;

        public InsertContactEtablissementTask(ContactEtablissementDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(ContactEtablissement... contactEtablissements) {
            dao.insert(contactEtablissements[0]);
            return null;
        }
    }

}
