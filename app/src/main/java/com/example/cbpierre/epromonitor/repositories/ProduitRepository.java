package com.example.cbpierre.epromonitor.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.cbpierre.epromonitor.EproMonitorRoomDatabase;
import com.example.cbpierre.epromonitor.dao.ProduitDao;
import com.example.cbpierre.epromonitor.models.Produit;

import java.util.List;

public class ProduitRepository {
    private ProduitDao produitDao;
    private LiveData<List<Produit>> allProduit;

    public ProduitRepository(Application application) {
        EproMonitorRoomDatabase database = EproMonitorRoomDatabase.getDatabase(application);
        produitDao = database.produitDao();
        allProduit = produitDao.allProduit();
    }

    public LiveData<List<Produit>> getAllProduit() {
        return allProduit;
    }

    public void insertProduit(Produit produit) {
        new InsertProduitTask(produitDao).execute(produit);
    }

    public void deleteProduit() {
        new DeleteProduitTask(produitDao).execute();
    }

    /**
     * Async Task
     */
    private static class InsertProduitTask extends AsyncTask<Produit, Void, Void> {
        private ProduitDao produitDao;

        public InsertProduitTask(ProduitDao produitDao) {
            this.produitDao = produitDao;
        }

        @Override
        protected Void doInBackground(Produit... produits) {
            produitDao.insert(produits[0]);
            return null;
        }
    }

    private static class DeleteProduitTask extends AsyncTask<Void, Void, Void> {
        private ProduitDao produitDao;

        public DeleteProduitTask(ProduitDao produitDao) {
            this.produitDao = produitDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            produitDao.deletePrduit();
            return null;
        }
    }
}
