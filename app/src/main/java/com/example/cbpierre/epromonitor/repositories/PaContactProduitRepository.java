package com.example.cbpierre.epromonitor.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.cbpierre.epromonitor.EproMonitorRoomDatabase;
import com.example.cbpierre.epromonitor.dao.PaContactProduitDao;
import com.example.cbpierre.epromonitor.models.PaContactProduit;
import com.example.cbpierre.epromonitor.models.Produit;

import java.util.List;

public class PaContactProduitRepository {
    private PaContactProduitDao paContactProduitDao;


    public PaContactProduitRepository(Application application) {
        EproMonitorRoomDatabase database = EproMonitorRoomDatabase.getDatabase(application);
        paContactProduitDao = database.paContactProduitDao();
    }

    public void insertPaContactProduit(PaContactProduit paContactProduit) {
        new InsertPaContactProduitTask(paContactProduitDao).execute(paContactProduit);
    }

    public void deletePaContactProduit() {
        new DeletePaContactProduitTask(paContactProduitDao).execute();
    }

    public LiveData<List<Produit>> getAllProduit(Integer contactId) {
        return paContactProduitDao.allPaContactProduit(contactId);
    }

    /**
     * Async Task
     */
    private static class InsertPaContactProduitTask extends AsyncTask<PaContactProduit, Void, Void> {
        private PaContactProduitDao paContactProduitDao;

        public InsertPaContactProduitTask(PaContactProduitDao paContactProduitDao) {
            this.paContactProduitDao = paContactProduitDao;
        }

        @Override
        protected Void doInBackground(PaContactProduit... paContactProduits) {
            paContactProduitDao.insertPaContactProduit(paContactProduits[0]);
            return null;
        }
    }

    private static class DeletePaContactProduitTask extends AsyncTask<Void, Void, Void> {
        private PaContactProduitDao paContactProduitDao;

        public DeletePaContactProduitTask(PaContactProduitDao paContactProduitDao) {
            this.paContactProduitDao = paContactProduitDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            paContactProduitDao.deleteAllPaContactProduit();
            return null;
        }
    }
}
