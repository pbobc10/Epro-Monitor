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

    public MutableLiveData<List<JoinContactEtablissementData>> searchEtabsByContactId = new MutableLiveData<>();

    public MutableLiveData<List<JoinContactEtablissementData>> getSearchEtabsByContactId() {
        return searchEtabsByContactId;
    }

    public void setSearchEtabsByContactId(List<JoinContactEtablissementData> results) {
        searchEtabsByContactId.setValue(results);
    }

    public void insertContactEtablissement(ContactEtablissement... contactEtablissement) {
        new InsertContactEtablissementTask(contactEtablissementDao).execute(contactEtablissement);
    }

    public void findContactEtabs(int i) {
        GetEtablissementByContactTask task = new GetEtablissementByContactTask(contactEtablissementDao);
        task.delegate = this;
        task.execute(i);
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

    private static class GetEtablissementByContactTask extends AsyncTask<Integer, Void, List<JoinContactEtablissementData>> {
        private ContactEtablissementDao dao;
        private ContactEtablissementRepository delegate;

        public GetEtablissementByContactTask(ContactEtablissementDao dao) {
            this.dao = dao;
        }

        @Override
        protected List<JoinContactEtablissementData> doInBackground(Integer... integers) {
            return dao.getAllEtablissementByContactId(integers[0]);
        }

        @Override
        protected void onPostExecute(List<JoinContactEtablissementData> joinContactEtablissementData) {
            super.onPostExecute(joinContactEtablissementData);
            delegate.setSearchEtabsByContactId(joinContactEtablissementData);

        }
    }

}
