package com.example.cbpierre.epromonitor.repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.example.cbpierre.epromonitor.EproMonitorRoomDatabase;
import com.example.cbpierre.epromonitor.dao.StatutJourDao;
import com.example.cbpierre.epromonitor.models.StatutJourRef;

public class StatutJourRepository {
    private StatutJourDao statutJourDao;

    public StatutJourRepository(Application application) {
        EproMonitorRoomDatabase database = EproMonitorRoomDatabase.getDatabase(application);
        statutJourDao = database.statutJourDao();
    }

    public void insertStatutJour(StatutJourRef... statutJourRef) {
        new InsertStatutJourTask(statutJourDao).execute(statutJourRef);
    }

    public void deleteStatutJour() {
        new DeleteStatutJourTask(statutJourDao).execute();
    }

    /**
     * All Async Task
     */
    private static class InsertStatutJourTask extends AsyncTask<StatutJourRef, Void, Void> {
        private StatutJourDao statutJourDao;

        public InsertStatutJourTask(StatutJourDao statutJourDao) {
            this.statutJourDao = statutJourDao;
        }

        @Override
        protected Void doInBackground(StatutJourRef... statutJourRefs) {
            statutJourDao.insertStatutJour(statutJourRefs);
            return null;
        }
    }

    private static class DeleteStatutJourTask extends AsyncTask<Void, Void, Void> {
        private StatutJourDao statutJourDao;

        public DeleteStatutJourTask(StatutJourDao statutJourDao) {
            this.statutJourDao = statutJourDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            statutJourDao.deleteStatutJour();
            return null;
        }
    }
}
