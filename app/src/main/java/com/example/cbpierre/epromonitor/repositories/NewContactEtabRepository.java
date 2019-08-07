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

    public void deleteNewContactEtabById(Integer contactId, Integer etabId) {
        new DeleteNewContactEtabById(newContactEtabDao).execute(contactId, etabId);
    }

    public void deleteNewContactNewEtabById(Integer contactId, Integer etabId) {
        new DeleteNewContactNewEtabById(newContactEtabDao).execute(contactId, etabId);
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

    private static class DeleteNewContactEtabById extends AsyncTask<Integer, Void, Void> {
        private NewContactEtabDao newContactEtabDao;

        public DeleteNewContactEtabById(NewContactEtabDao newContactEtabDao) {
            this.newContactEtabDao = newContactEtabDao;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            newContactEtabDao.deleteNewContactEtabById(integers[0], integers[1]);
            return null;
        }
    }

    private static class DeleteNewContactNewEtabById extends AsyncTask<Integer, Void, Void> {
        private NewContactEtabDao newContactEtabDao;

        public DeleteNewContactNewEtabById(NewContactEtabDao newContactEtabDao) {
            this.newContactEtabDao = newContactEtabDao;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            newContactEtabDao.deleteNewContactNewEtabById(integers[0], integers[1]);
            return null;
        }
    }

}
