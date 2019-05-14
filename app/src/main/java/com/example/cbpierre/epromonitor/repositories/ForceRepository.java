package com.example.cbpierre.epromonitor.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.cbpierre.epromonitor.EproMonitorRoomDatabase;
import com.example.cbpierre.epromonitor.dao.ForceDao;
import com.example.cbpierre.epromonitor.models.Force;


import java.util.List;

public class ForceRepository {
    private LiveData<List<Force>> fAllForce;
    private ForceDao forceDao;
   // private static OnSearchForceListener forceListener;

    public ForceRepository(Application application) {
        EproMonitorRoomDatabase database = EproMonitorRoomDatabase.getDatabase(application);
        this.forceDao = database.forceDao();
        this.fAllForce = forceDao.getAllForce();
    }

    public LiveData<List<Force>> getfAllForce() {
        return fAllForce;
    }

    /*public void setForceListener(OnSearchForceListener forceListener) {
        this.forceListener = forceListener;
    }*/

    public void insert(Force force) {
        new IsertForceAsyncTask(forceDao).execute(force);
    }

    private static class IsertForceAsyncTask extends AsyncTask<Force, Void, Void> {
        private ForceDao forceDao;

        public IsertForceAsyncTask(ForceDao forceDao) {
            this.forceDao = forceDao;
        }

        @Override
        protected Void doInBackground(Force... forces) {
            forceDao.insert(forces[0]);
            return null;
        }
    }

   /* private static class GetNomForceAsyncTask extends AsyncTask<Void, Void, List<Force>> {
        private ForceDao forceDao;

        public GetNomForceAsyncTask(ForceDao forceDao) {
            this.forceDao = forceDao;
        }

        @Override
        protected List<Force> doInBackground(Void... voids) {
            return forceDao.getNomForce();
        }

        @Override
        protected void onPostExecute(List<Force> forces) {
            super.onPostExecute(forces);
            if (forceListener != null)
                forceListener.OnSearchForce(forces);
        }
    }

    public interface OnSearchForceListener {
        void OnSearchForce(List<Force> forces);
    }*/
}
