package com.example.cbpierre.epromonitor.repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.example.cbpierre.epromonitor.EproMonitorRoomDatabase;
import com.example.cbpierre.epromonitor.dao.GHJourContactProduitDao;
import com.example.cbpierre.epromonitor.models.GHJourContactProduit;

public class GHJourContactProduitRepository {
    private GHJourContactProduitDao ghJourContactProduitDao;

    public GHJourContactProduitRepository(Application application) {
        EproMonitorRoomDatabase database = EproMonitorRoomDatabase.getDatabase(application);
        ghJourContactProduitDao = database.ghJourContactProduitDao();
    }

    public void insertGHJourContactProduit(GHJourContactProduit ghJourContactProduit) {
        new InsertGHJourContactProduit(ghJourContactProduitDao).execute(ghJourContactProduit);
    }

    public void deleteGHJourContactProduit() {
        new DeleteGHJourContactProduit(ghJourContactProduitDao).execute();
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
}
