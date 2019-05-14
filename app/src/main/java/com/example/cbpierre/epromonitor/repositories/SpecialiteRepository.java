package com.example.cbpierre.epromonitor.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.cbpierre.epromonitor.EproMonitorRoomDatabase;
import com.example.cbpierre.epromonitor.dao.SpecialiteDao;
import com.example.cbpierre.epromonitor.models.Specialite;

import java.util.List;

public class SpecialiteRepository {
    private LiveData<List<Specialite>> sAllSpecialite;
    private SpecialiteDao specialiteDao;

    public SpecialiteRepository(Application application) {
        EproMonitorRoomDatabase database = EproMonitorRoomDatabase.getDatabase(application);
        specialiteDao = database.specialiteDao();
        sAllSpecialite = specialiteDao.getAllSpecialite();
    }

    public LiveData<List<Specialite>> getsAllSpecialite() {
        return sAllSpecialite;
    }

    public void insert(Specialite specialite) {
        new InsertSpecialiteAsyncTask(specialiteDao).execute(specialite);
    }

    private static class InsertSpecialiteAsyncTask extends AsyncTask<Specialite, Void, Void> {
        private SpecialiteDao specialiteDao;

        public InsertSpecialiteAsyncTask(SpecialiteDao specialiteDao) {
            this.specialiteDao = specialiteDao;
        }

        @Override
        protected Void doInBackground(Specialite... specialites) {
            specialiteDao.insert(specialites[0]);
            return null;
        }
    }
}
