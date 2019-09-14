package com.example.cbpierre.epromonitor.repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.example.cbpierre.epromonitor.EproMonitorRoomDatabase;
import com.example.cbpierre.epromonitor.dao.GHJourContactDao;
import com.example.cbpierre.epromonitor.models.GHJourContact;

public class GHJourContactRepository {
    private GHJourContactDao ghJourContactDao;

    public GHJourContactRepository(Application application) {
        EproMonitorRoomDatabase database = EproMonitorRoomDatabase.getDatabase(application);
        ghJourContactDao = database.ghJourContactDao();
    }

    public void insertGHJourContact(GHJourContact ghJourContact) {
        new InsertGHJourContactTask(ghJourContactDao).execute(ghJourContact);
    }

    public void deleteGHJourContact() {
        new DeleteGHJourContactTask(ghJourContactDao).execute();
    }

    /**
     * All Async Task
     */

    private static class InsertGHJourContactTask extends AsyncTask<GHJourContact, Void, Void> {
        private GHJourContactDao ghJourContactDao;

        public InsertGHJourContactTask(GHJourContactDao ghJourContactDao) {
            this.ghJourContactDao = ghJourContactDao;
        }

        @Override
        protected Void doInBackground(GHJourContact... ghJourContacts) {
            ghJourContactDao.insertGHJOurContact(ghJourContacts[0]);
            return null;
        }
    }

    private static class DeleteGHJourContactTask extends AsyncTask<Void, Void, Void> {
        private GHJourContactDao ghJourContactDao;

        public DeleteGHJourContactTask(GHJourContactDao ghJourContactDao) {
            this.ghJourContactDao = ghJourContactDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            ghJourContactDao.deleteGHJourContact();
            return null;
        }
    }
}
