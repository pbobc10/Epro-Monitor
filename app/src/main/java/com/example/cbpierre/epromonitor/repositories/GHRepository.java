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
    private static OnGHListListener onGHListListener;

    public GHRepository(Application application) {
        EproMonitorRoomDatabase database = EproMonitorRoomDatabase.getDatabase(application);
        ghDao = database.ghDao();
        gh = ghDao.allGH();
    }

    public LiveData<List<GH>> getAllGH() {
        return gh;
    }

    public void insertGH(GH... gh) {
        new InsertGHTask(ghDao).execute(gh);
    }

    public void deleteGH() {
        new DeleteGHTask(ghDao).execute();
    }

    public void getGHList() {
        new GHListTask(ghDao).execute();
    }

    public void updateGH(String ghId,String modifiePar,String modifieLe) {
        new UpdateGHTask(ghDao).execute(ghId,modifiePar,modifieLe);
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
            ghDao.insertGH(ghs);
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

    private static class GHListTask extends AsyncTask<Void, Void, List<GH>> {
        private GHDao ghDao;

        public GHListTask(GHDao ghDao) {
            this.ghDao = ghDao;
        }

        @Override
        protected List<GH> doInBackground(Void... voids) {
            return ghDao.ghList();
        }

        @Override
        protected void onPostExecute(List<GH> ghs) {
            super.onPostExecute(ghs);
            if (onGHListListener != null)
                onGHListListener.onGHListe(ghs);
        }

    }

    public static class UpdateGHTask extends AsyncTask<String, Void, Void> {
        private GHDao ghDao;

        public UpdateGHTask(GHDao ghDao) {
            this.ghDao = ghDao;
        }

        @Override
        protected Void doInBackground(String... strings) {
            ghDao.updateGH(strings[0],strings[1],strings[2]);
            return null;
        }
    }

    public interface OnGHListListener {
        void onGHListe(List<GH> ghList);
    }

    public void setOnGHListListener(OnGHListListener onGHListListener) {
        GHRepository.onGHListListener = onGHListListener;
    }

}
