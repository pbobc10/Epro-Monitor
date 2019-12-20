package com.example.cbpierre.epromonitor.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.cbpierre.epromonitor.EproMonitorRoomDatabase;
import com.example.cbpierre.epromonitor.dao.GHJourContactProduitDao;
import com.example.cbpierre.epromonitor.models.GHJourContactProduit;
import com.example.cbpierre.epromonitor.models.JoinProduitAcceptabliliteGHProduit;

import java.util.List;

public class GHJourContactProduitRepository {
    private GHJourContactProduitDao ghJourContactProduitDao;
    private static OnGHJourContactProduitListener onGHJourContactProduitListener;

    public GHJourContactProduitRepository(Application application) {
        EproMonitorRoomDatabase database = EproMonitorRoomDatabase.getDatabase(application);
        ghJourContactProduitDao = database.ghJourContactProduitDao();
    }

    public LiveData<List<JoinProduitAcceptabliliteGHProduit>> getProduitAcceptabliliteGHProduit(int ghId, int conId, String jour) {
        return ghJourContactProduitDao.getProduitAcceptabliliteGHProduit(ghId, conId, jour);
    }

    public void insertGHJourContactProduit(GHJourContactProduit ghJourContactProduit) {
        new InsertGHJourContactProduit(ghJourContactProduitDao).execute(ghJourContactProduit);
    }

    public void deleteGHJourContactProduit() {
        new DeleteGHJourContactProduit(ghJourContactProduitDao).execute();
    }

    public void getGHJourContactProduitList() {
        new GHJourContactProduitListTask(ghJourContactProduitDao).execute();
    }

    /**
     * All Async Task
     */
    private static class InsertGHJourContactProduit extends AsyncTask<GHJourContactProduit, Void, Void> {
        private GHJourContactProduitDao ghJourContactProduitDao;

        public InsertGHJourContactProduit(GHJourContactProduitDao ghJourContactProduitDao) {
            this.ghJourContactProduitDao = ghJourContactProduitDao;
        }

        @Override
        protected Void doInBackground(GHJourContactProduit... ghJourContactProduits) {
            ghJourContactProduitDao.insertGHJourContactProduit(ghJourContactProduits[0]);
            return null;
        }
    }

    private static class DeleteGHJourContactProduit extends AsyncTask<Void, Void, Void> {
        private GHJourContactProduitDao ghJourContactProduitDao;

        public DeleteGHJourContactProduit(GHJourContactProduitDao ghJourContactProduitDao) {
            this.ghJourContactProduitDao = ghJourContactProduitDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            ghJourContactProduitDao.deleteGHJourContactProduit();
            return null;
        }
    }

    private static class GHJourContactProduitListTask extends AsyncTask<Void, Void, List<GHJourContactProduit>> {
        private GHJourContactProduitDao ghJourContactProduitDao;

        public GHJourContactProduitListTask(GHJourContactProduitDao ghJourContactProduitDao) {
            this.ghJourContactProduitDao = ghJourContactProduitDao;
        }

        @Override
        protected List<GHJourContactProduit> doInBackground(Void... voids) {
            return ghJourContactProduitDao.ghJourContactProduitList();
        }

        @Override
        protected void onPostExecute(List<GHJourContactProduit> ghJourContactProduits) {
            super.onPostExecute(ghJourContactProduits);
            if (onGHJourContactProduitListener != null)
                onGHJourContactProduitListener.OnGHJourContactProduit(ghJourContactProduits);
        }
    }

    public interface OnGHJourContactProduitListener {
        void OnGHJourContactProduit(List<GHJourContactProduit> ghJourContactProduits);
    }

    public void setOnGHJourContactProduitListener(OnGHJourContactProduitListener onGHJourContactProduitListener) {
        GHJourContactProduitRepository.onGHJourContactProduitListener = onGHJourContactProduitListener;
    }
}
