package com.example.cbpierre.epromonitor.repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.example.cbpierre.epromonitor.EproMonitorRoomDatabase;
import com.example.cbpierre.epromonitor.dao.StatutVisiteDao;
import com.example.cbpierre.epromonitor.models.StatutVisiteRef;

public class StatutVisiteRepository {
    private StatutVisiteDao statutVisiteDao;

    public StatutVisiteRepository(Application application) {
        EproMonitorRoomDatabase database = EproMonitorRoomDatabase.getDatabase(application);
        statutVisiteDao = database.statutVisiteDao();
    }

    public void insertStatutVisite(StatutVisiteRef statutVisiteRef) {
        new InsertStatutVisiteTask(statutVisiteDao).execute(statutVisiteRef);
    }

    public void deleteStatutVisite() {
        new DeleteStatutVisiteTask(statutVisiteDao).execute();
    }

    /**
     * All Async Task
     */
    private static class InsertStatutVisiteTask extends AsyncTask<StatutVisiteRef, Void, Void> {
        private StatutVisiteDao statutVisiteDao;

        public InsertStatutVisiteTask(StatutVisiteDao statutVisiteDao) {
            this.statutVisiteDao = statutVisiteDao;
        }

        @Override
        protected Void doInBackground(StatutVisiteRef... statutVisiteRefs) {
            statutVisiteDao.insertStatutVisite(statutVisiteRefs[0]);
            return null;
        }
    }

    private static class DeleteStatutVisiteTask extends AsyncTask<Void, Void, Void> {
        private StatutVisiteDao statutVisiteDao;

        public DeleteStatutVisiteTask(StatutVisiteDao statutVisiteDao) {
            this.statutVisiteDao = statutVisiteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            statutVisiteDao.deleteStatutVisite();
            return null;
        }
    }
}
