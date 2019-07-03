package com.example.cbpierre.epromonitor.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import com.example.cbpierre.epromonitor.EproMonitorRoomDatabase;
import com.example.cbpierre.epromonitor.dao.EtablissementDao;
import com.example.cbpierre.epromonitor.models.CompleteEtablissement;
import com.example.cbpierre.epromonitor.models.Etablissement;
import com.example.cbpierre.epromonitor.models.JoinNewEtabNewContact;
import com.example.cbpierre.epromonitor.models.OldEtablissement;

import java.util.List;

public class EtablissementRepository {
    private LiveData<List<Etablissement>> allEtablissement;
    private LiveData<List<CompleteEtablissement>> allCompleteEtablissement;
    private LiveData<List<Etablissement>> etabByLocalite;
    private EtablissementDao etablissementDao;
    private MutableLiveData<Integer> maxEtabMutable = new MutableLiveData<>();
    private static OnNewEtabListener onNewEtabListener;
    private static OnOldEtadListener onOldEtadListener;

    public EtablissementRepository(Application application) {
        EproMonitorRoomDatabase database = EproMonitorRoomDatabase.getDatabase(application);
        this.etablissementDao = database.etablissementDao();
        this.allEtablissement = etablissementDao.getAllEtablissement();
        this.allCompleteEtablissement = etablissementDao.getAllCompleteEtablissement();
    }

    public LiveData<List<Etablissement>> getEtabByLocalite(String localite) {
        return etablissementDao.getEtabByLocalite(localite);
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
     * @param max
     */
    public void setMaxEtabMutable(int max) {
        maxEtabMutable.setValue(max);
    }

    public MutableLiveData<Integer> getMaxEtabMutable() {
        return maxEtabMutable;
    }

    public void getMaxEtab() {
        GetMaxEtabAsyncTask task = new GetMaxEtabAsyncTask(etablissementDao);
        task.delegate = this;
        task.execute();
    }

    public void updateNewEtabsAfterSync() {
        new UpdateNewEtabsAfterSync(etablissementDao).execute();
    }

    /**
     * method interface
     */
    public void setOnNewEtabListener(OnNewEtabListener onNewEtabListener) {
        EtablissementRepository.onNewEtabListener = onNewEtabListener;
    }

    public void setOnOldEtadListener(OnOldEtadListener onOldEtadListener) {
        EtablissementRepository.onOldEtadListener = onOldEtadListener;
    }

    public void getNewEtabs() {
        new NewEtablissementByContactIdTask(etablissementDao).execute();
    }

    public void getOldEtabs() {
        new OldEtablissementByContactIdTask(etablissementDao).execute();
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

    private static class GetMaxEtabAsyncTask extends AsyncTask<Void, Void, Integer> {
        private EtablissementDao etablissementDao;
        private EtablissementRepository delegate;

        public GetMaxEtabAsyncTask(EtablissementDao etablissementDao) {
            this.etablissementDao = etablissementDao;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            return etablissementDao.getMaxEtablissement();
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            delegate.setMaxEtabMutable(integer);
        }
    }

    private static class NewEtablissementByContactIdTask extends AsyncTask<Void, Void, List<JoinNewEtabNewContact>> {
        private EtablissementDao etablissementDao;

        public NewEtablissementByContactIdTask(EtablissementDao etablissementDao) {
            this.etablissementDao = etablissementDao;
        }

        @Override
        protected List<JoinNewEtabNewContact> doInBackground(Void... voids) {
            return etablissementDao.getNewEtablissement();
        }

        @Override
        protected void onPostExecute(List<JoinNewEtabNewContact> joinNewEtabNewContacts) {
            super.onPostExecute(joinNewEtabNewContacts);
            if (onNewEtabListener != null)
                onNewEtabListener.getNewEtab(joinNewEtabNewContacts);
        }
    }

    private static class OldEtablissementByContactIdTask extends AsyncTask<Void, Void, List<OldEtablissement>> {
        private EtablissementDao etablissementDao;
        //private OnOldEtadListener onOldEtadListener;

        public OldEtablissementByContactIdTask(EtablissementDao etablissementDao) {
            this.etablissementDao = etablissementDao;
        }

        @Override
        protected List<OldEtablissement> doInBackground(Void... voids) {
            return etablissementDao.getOldEtab();
        }

        @Override
        protected void onPostExecute(List<OldEtablissement> oldEtablissements) {
            super.onPostExecute(oldEtablissements);
            if (onOldEtadListener != null)
                onOldEtadListener.getOldEtab(oldEtablissements);
        }
    }

    private static class UpdateNewEtabsAfterSync extends AsyncTask<Void, Void, Void> {
        private EtablissementDao etablissementDao;

        public UpdateNewEtabsAfterSync(EtablissementDao etablissementDao) {
            this.etablissementDao = etablissementDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            etablissementDao.updateNewEtabsAfterSync();
            return null;
        }
    }

    /**
     * Interfaces
     */
    public interface OnNewEtabListener {
        void getNewEtab(List<JoinNewEtabNewContact> newEtabs);
    }

    public interface OnOldEtadListener {
        void getOldEtab(List<OldEtablissement> contactsIds);
    }

}
