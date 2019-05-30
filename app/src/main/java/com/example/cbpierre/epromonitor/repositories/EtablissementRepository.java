package com.example.cbpierre.epromonitor.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.cbpierre.epromonitor.EproMonitorRoomDatabase;
import com.example.cbpierre.epromonitor.dao.EtablissementDao;
import com.example.cbpierre.epromonitor.models.CompleteEtablissement;
import com.example.cbpierre.epromonitor.models.Etablissement;

import java.util.List;

public class EtablissementRepository {
    private LiveData<List<Etablissement>> allEtablissement;
    private LiveData<List<CompleteEtablissement>> allCompleteEtablissement;
    private EtablissementDao etablissementDao;

    public EtablissementRepository(Application application) {
        EproMonitorRoomDatabase database = EproMonitorRoomDatabase.getDatabase(application);
        this.etablissementDao = database.etablissementDao();
        this.allEtablissement = etablissementDao.getAllEtablissement();
        this.allCompleteEtablissement = etablissementDao.getAllCompleteEtablissement();
    }

    public LiveData<List<Etablissement>> getAllEtablissement() {
        return allEtablissement;
    }

    public LiveData<List<CompleteEtablissement>> getCompleteEtabsByNom(String nom) {
        return etablissementDao.getCompleteEtabsByNom(nom);
    }

    public LiveData<List<CompleteEtablissement>> getAllCompleteEtablissement() {
        return allCompleteEtablissement;
    }

    public void insertEtablissement(Etablissement etablissement) {
        new InsertAsyncTask(etablissementDao).execute(etablissement);
    }

    /**
     * ***  AsyncTask  ***
     */

    private static class InsertAsyncTask extends AsyncTask<Etablissement, Void, Void> {
        private EtablissementDao etablissementDao;

        public InsertAsyncTask(EtablissementDao etablissementDao) {
            this.etablissementDao = etablissementDao;
        }

        @Override
        protected Void doInBackground(Etablissement... etablissements) {
            etablissementDao.insert(etablissements[0]);
            return null;
        }
    }
}
