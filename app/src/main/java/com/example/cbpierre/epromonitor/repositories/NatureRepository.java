package com.example.cbpierre.epromonitor.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.cbpierre.epromonitor.EproMonitorRoomDatabase;
import com.example.cbpierre.epromonitor.dao.NatureDao;
import com.example.cbpierre.epromonitor.models.Nature;

import java.util.List;

public class NatureRepository {
    private NatureDao natureDao;
    private LiveData<List<Nature>> mAllNature;

    public NatureRepository(Application application) {
        EproMonitorRoomDatabase db = EproMonitorRoomDatabase.getDatabase(application);
        natureDao = db.natureDao();
        mAllNature = natureDao.getAllNature();
    }

   public LiveData<List<Nature>> getmAllNature() {
        return mAllNature;
    }

    public void insertNature(Nature nature) {
        new InsertNatureAsyncTask(natureDao).execute(nature);
    }

    private static class InsertNatureAsyncTask extends AsyncTask<Nature, Void, Void> {
        private NatureDao natureDao;

        public InsertNatureAsyncTask(NatureDao natureDao) {
            this.natureDao = natureDao;
        }

        @Override
        protected Void doInBackground(Nature... natures) {
            natureDao.insert(natures[0]);
            return null;
        }
    }
}
