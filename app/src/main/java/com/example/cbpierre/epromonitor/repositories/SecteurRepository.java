package com.example.cbpierre.epromonitor.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.cbpierre.epromonitor.EproMonitorRoomDatabase;
import com.example.cbpierre.epromonitor.dao.SecteurDao;
import com.example.cbpierre.epromonitor.models.Secteur;

import java.util.List;

public class SecteurRepository {
    private SecteurDao secteurDao;
    private LiveData<List<Secteur>> sAllSecteur;


    public SecteurRepository(Application application) {
        EproMonitorRoomDatabase database = EproMonitorRoomDatabase.getDatabase(application);
        this.secteurDao = database.secteurDao();
        this.sAllSecteur = secteurDao.getAllSecteur();
    }

    public LiveData<List<Secteur>> getmAllSecteur() {
        return sAllSecteur;
    }

    public void insertSecteur(Secteur secteur) {
        new InsertSecteurAsynckTask(secteurDao).execute(secteur);
    }


    private static class InsertSecteurAsynckTask extends AsyncTask<Secteur, Void, Void> {

        private SecteurDao sSecteurDao;

        public InsertSecteurAsynckTask(SecteurDao secteurDao) {
            this.sSecteurDao = secteurDao;
        }

        @Override
        protected Void doInBackground(Secteur... secteurs) {
            sSecteurDao.insert(secteurs[0]);
            return null;
        }
    }

}
