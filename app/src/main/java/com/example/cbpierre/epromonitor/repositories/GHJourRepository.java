package com.example.cbpierre.epromonitor.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.cbpierre.epromonitor.EproMonitorRoomDatabase;
import com.example.cbpierre.epromonitor.dao.GHJourDao;
import com.example.cbpierre.epromonitor.models.GHJour;
import com.example.cbpierre.epromonitor.models.JoinGHJourStatutRef;

import java.util.List;

public class GHJourRepository {
    private GHJourDao ghJourDao;
    private static OnGHJourListListener onGHJourListListener;

    public GHJourRepository(Application application) {
        EproMonitorRoomDatabase database = EproMonitorRoomDatabase.getDatabase(application);
        ghJourDao = database.ghJourDao();
    }

    public LiveData<List<JoinGHJourStatutRef>> getAllJour(Integer ghId) {
        return ghJourDao.allJour(ghId);
    }

    public void insertGHJour(GHJour ghJour) {
        new InsertGHJourTask(ghJourDao).execute(ghJour);
    }

    public void deleteGHJour() {
        new DeleteGHJourTask(ghJourDao).execute();
    }

    public void getGHJourList() {
        new GHJourListTask(ghJourDao).execute();
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

    private static class GHJourListTask extends AsyncTask<Void, Void, List<GHJour>> {
        private GHJourDao ghJourDao;

        public GHJourListTask(GHJourDao ghJourDao) {
            this.ghJourDao = ghJourDao;
        }

        @Override
        protected List<GHJour> doInBackground(Void... voids) {
            return ghJourDao.ghJourList();
        }

        @Override
        protected void onPostExecute(List<GHJour> ghJours) {
            super.onPostExecute(ghJours);
            if (onGHJourListListener != null)
                onGHJourListListener.onGHJourListe(ghJours);
        }
    }

    public interface OnGHJourListListener {
        void onGHJourListe(List<GHJour> ghJourList);
    }

    public void setOnGHJourListListener(OnGHJourListListener onGHJourListListener) {
        GHJourRepository.onGHJourListListener = onGHJourListListener;
    }
}
