package com.example.cbpierre.epromonitor.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.cbpierre.epromonitor.EproMonitorRoomDatabase;
import com.example.cbpierre.epromonitor.dao.RecommandationDao;
import com.example.cbpierre.epromonitor.models.Recommandation;

import java.util.List;

public class RecommandationRepository {
    private RecommandationDao recommandationDao;
    private LiveData<List<Recommandation>> allRecommandation;

    public RecommandationRepository(Application application) {
        EproMonitorRoomDatabase database = EproMonitorRoomDatabase.getDatabase(application);
        recommandationDao = database.recommandationDao();
        allRecommandation = recommandationDao.allRecommandation();
    }

    public LiveData<List<Recommandation>> getAllRecommandation() {
        return allRecommandation;
    }

    public void insertRecommandation(Recommandation... recommandations) {
        new InsertRecommandationAsyncTask(recommandationDao).execute(recommandations);
    }

    public void deleteRecommandation(Recommandation recommandation) {
        new DeleteRecommandationAsyncTask(recommandationDao).execute(recommandation);
    }

    /**
     * Async Task
     */
    public static class InsertRecommandationAsyncTask extends AsyncTask<Recommandation, Void, Void> {
        private RecommandationDao recommandationDao;

        public InsertRecommandationAsyncTask(RecommandationDao recommandationDao) {
            this.recommandationDao = recommandationDao;
        }

        @Override
        protected Void doInBackground(Recommandation... recommandations) {
            recommandationDao.insertRecommandation(recommandations);
            return null;
        }
    }

    public static class DeleteRecommandationAsyncTask extends AsyncTask<Recommandation, Void, Void> {
        private RecommandationDao recommandationDao;

        public DeleteRecommandationAsyncTask(RecommandationDao recommandationDao) {
            this.recommandationDao = recommandationDao;
        }

        @Override
        protected Void doInBackground(Recommandation... recommandations) {
            recommandationDao.deleteRecommandation(recommandations[0]);
            return null;
        }
    }

}
