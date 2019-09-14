package com.example.cbpierre.epromonitor.repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.example.cbpierre.epromonitor.EproMonitorRoomDatabase;
import com.example.cbpierre.epromonitor.dao.GHJourDao;
import com.example.cbpierre.epromonitor.models.GHJour;

public class GHJourRepository {
    private GHJourDao ghJourDao;

    public GHJourRepository(Application application) {
        EproMonitorRoomDatabase database = EproMonitorRoomDatabase.getDatabase(application);
        ghJourDao = database.ghJourDao();
    }

    public void insertGHJour(GHJour ghJour) {
        new InsertGHJourTask(ghJourDao).execute(ghJour);
    }

    public void deleteGHJour() {
        new DeleteGHJourTask(ghJourDao).execute();
    }

    /**
     * All Async Task
     */

    private static class InsertGHJourTask extends AsyncTask<GHJour, Void, Void> {
        private GHJourDao ghJourDao;

        public InsertGHJourTask(GHJourDao ghJourDao) {
            this.ghJourDao = ghJourDao;
        }

        @Override
        protected Void doInBackground(GHJour... ghJours) {
            ghJourDao.insertGHJour(ghJours[0]);
            return null;
        }
    }

    private static class DeleteGHJourTask extends AsyncTask<Void, Void, Void> {
        private GHJourDao ghJourDao;

        public DeleteGHJourTask(GHJourDao ghJourDao) {
            this.ghJourDao = ghJourDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            ghJourDao.deleteGHJour();
            return null;
        }
    }
}
