package com.example.cbpierre.epromonitor.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
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
}
