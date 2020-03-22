package com.example.cbpierre.epromonitor.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.cbpierre.epromonitor.EproMonitorRoomDatabase;
import com.example.cbpierre.epromonitor.dao.AcceptabiliteDao;
import com.example.cbpierre.epromonitor.models.AcceptabiliteRef;

import java.util.List;

public class AcceptabiliteRepository {
    private AcceptabiliteDao acceptabiliteDao;

    public AcceptabiliteRepository(Application application) {
        EproMonitorRoomDatabase database = EproMonitorRoomDatabase.getDatabase(application);
        acceptabiliteDao = database.acceptabiliteDao();
    }

    public LiveData<List<AcceptabiliteRef>> getAllAcceptabiliteRef() {
        return acceptabiliteDao.getAllAcceptabiliteRef();
    }

    public void insertAcceptabilite(AcceptabiliteRef... acceptabiliteRef) {
        new InsertAcceptabiliteTask(acceptabiliteDao).execute(acceptabiliteRef);
    }

    public void deleteAcceptabilite() {
        new DeleteAcceptabiliteTask(acceptabiliteDao).execute();
    }

    /**
     * All Async Task
     */
    private static class InsertAcceptabiliteTask extends AsyncTask<AcceptabiliteRef, Void, Void> {
        private AcceptabiliteDao acceptabiliteDao;

        public InsertAcceptabiliteTask(AcceptabiliteDao acceptabiliteDao) {
            this.acceptabiliteDao = acceptabiliteDao;
        }

        @Override
        protected Void doInBackground(AcceptabiliteRef... acceptabiliteRefs) {
            acceptabiliteDao.insertAcceptabilite(acceptabiliteRefs);
            return null;
        }
    }

    private static class DeleteAcceptabiliteTask extends AsyncTask<Void, Void, Void> {
        private AcceptabiliteDao acceptabiliteDao;

        public DeleteAcceptabiliteTask(AcceptabiliteDao acceptabiliteDao) {
            this.acceptabiliteDao = acceptabiliteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            acceptabiliteDao.delteAcceptabilite();
            return null;
        }
    }
}
