package com.example.cbpierre.epromonitor.repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.example.cbpierre.epromonitor.EproMonitorRoomDatabase;
import com.example.cbpierre.epromonitor.dao.CommuneLocaliteContactDao;
import com.example.cbpierre.epromonitor.models.CommuneLocaliteContact;

public class CommuneLocaliteContactRepository {
    private CommuneLocaliteContactDao communeLocaliteContactDao;

    public CommuneLocaliteContactRepository(Application application) {
        EproMonitorRoomDatabase database = EproMonitorRoomDatabase.getDatabase(application);
        communeLocaliteContactDao = database.communeLocaliteContactDao();
    }

    public void insertCommuneLocaliteContact(CommuneLocaliteContact communeLocaliteContact) {
        new InsertCommuneLocaliteContact(communeLocaliteContactDao).execute(communeLocaliteContact);
    }

    public void deleteCommuneLocaliteContact() {
        new DeleteCommuneLocaleContactTask(communeLocaliteContactDao).execute();
    }

    /**
     * All Async Task
     */
    private static class InsertCommuneLocaliteContact extends AsyncTask<CommuneLocaliteContact, Void, Void> {
        private CommuneLocaliteContactDao communeLocaliteContactDao;

        public InsertCommuneLocaliteContact(CommuneLocaliteContactDao communeLocaliteContactDao) {
            this.communeLocaliteContactDao = communeLocaliteContactDao;
        }

        @Override
        protected Void doInBackground(CommuneLocaliteContact... communeLocaliteContacts) {
            communeLocaliteContactDao.insertCommuneLocaliteContact(communeLocaliteContacts[0]);
            return null;
        }
    }

    private static class DeleteCommuneLocaleContactTask extends AsyncTask<Void, Void, Void> {
        private CommuneLocaliteContactDao communeLocaliteContactDao;

        public DeleteCommuneLocaleContactTask(CommuneLocaliteContactDao communeLocaliteContactDao) {
            this.communeLocaliteContactDao = communeLocaliteContactDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            communeLocaliteContactDao.deleteCommuneLocaliteContact();
            return null;
        }
    }
}
