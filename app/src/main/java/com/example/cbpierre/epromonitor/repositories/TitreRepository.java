package com.example.cbpierre.epromonitor.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.cbpierre.epromonitor.EproMonitorRoomDatabase;
import com.example.cbpierre.epromonitor.dao.TitreDao;
import com.example.cbpierre.epromonitor.models.Titre;

import java.util.List;

public class TitreRepository {
    private TitreDao titreDao;
    private LiveData<List<Titre>> tAllTitre;

    public TitreRepository(Application application) {
        EproMonitorRoomDatabase database = EproMonitorRoomDatabase.getDatabase(application);
        this.titreDao = database.titreDao();
        this.tAllTitre = titreDao.getAllTitre();
    }

    public LiveData<List<Titre>> getmAllTitre() {
        return tAllTitre;
    }

    public void insertTitre(Titre titre) {
        new InsertTitreAsynckTask(titreDao).execute(titre);
    }


    private static class InsertTitreAsynckTask extends AsyncTask<Titre, Void, Void> {

        private TitreDao tTitreDao;

        public InsertTitreAsynckTask(TitreDao TitreDao) {
            this.tTitreDao = TitreDao;
        }

        @Override
        protected Void doInBackground(Titre... titres) {
            tTitreDao.insert(titres[0]);
            return null;
        }
    }
}
