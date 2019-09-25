package com.example.cbpierre.epromonitor.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.cbpierre.epromonitor.EproMonitorRoomDatabase;
import com.example.cbpierre.epromonitor.dao.GHDao;
import com.example.cbpierre.epromonitor.models.GH;

import java.util.List;

public class GHRepository {
    private GHDao ghDao;
    private LiveData<List<GH>> gh;

    public GHRepository(Application application) {
        EproMonitorRoomDatabase database = EproMonitorRoomDatabase.getDatabase(application);
        ghDao = database.ghDao();
        gh = ghDao.allGH();
    }

    public LiveData<List<GH>> getAllGH() {
        return gh;
    }

    public void insertGH(GH gh) {
        new InsertGHTask(ghDao).execute(gh);
    }

    public void deleteGH() {
        new DeleteGHTask(ghDao).execute();
    }


    /**
     * All Async Task
     */

    private static class InsertGHTask extends AsyncTask<GH, Void, Void> {
        private GHDao ghDao;

        public InsertGHTask(GHDao ghDao) {
            this.ghDao = ghDao;
        }

        @Override
        protected Void doInBackground(GH... ghs) {
            ghDao.insertGH(ghs[0]);
            return null;
        }
    }

    private static class DeleteGHTask extends AsyncTask<Void, Void, Void> {
        private GHDao ghDao;

        public DeleteGHTask(GHDao ghDao) {
            this.ghDao = ghDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            ghDao.deleteGH();
            return null;
        }
    }
}
