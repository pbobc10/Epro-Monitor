package com.example.cbpierre.epromonitor.repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.example.cbpierre.epromonitor.EproMonitorRoomDatabase;
import com.example.cbpierre.epromonitor.dao.NewContactEtabDao;
import com.example.cbpierre.epromonitor.models.NewContactETab;

public class NewContactEtabRepository {
    private NewContactEtabDao newContactEtabDao;

    public NewContactEtabRepository(Application application) {
        EproMonitorRoomDatabase db = EproMonitorRoomDatabase.getDatabase(application);
        newContactEtabDao = db.newContactEtabDao();
    }

    public void insert(NewContactETab contactETab) {
        new NewContactEtabTask(newContactEtabDao).execute(contactETab);
    }

    public void deleteNewContactEtabAfterSync() {
        new DeleteNewContactEtabAfterSync(newContactEtabDao).execute();
    }

    /**
     * AsyncTask
     */

    private static class NewContactEtabTask extends AsyncTask<NewContactETab, Void, Void> {
        private NewContactEtabDao contactEtabDao;

        public NewContactEtabTask(NewContactEtabDao contactEtabDao) {
            this.contactEtabDao = contactEtabDao;
        }

        @Override
        protected Void doInBackground(NewContactETab... newContactETabs) {
            contactEtabDao.insert(newContactETabs[0]);
            return null;
        }
    }

    private static class DeleteNewContactEtabAfterSync extends AsyncTask<Void, Void, Void> {
        private NewContactEtabDao newContactEtabDao;

        public DeleteNewContactEtabAfterSync(NewContactEtabDao newContactEtabDao) {
            this.newContactEtabDao = newContactEtabDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            newContactEtabDao.deleteNewContactEtabAfterSync();
            return null;
        }
    }

}
