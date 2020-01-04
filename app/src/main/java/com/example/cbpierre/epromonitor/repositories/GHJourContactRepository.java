package com.example.cbpierre.epromonitor.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.cbpierre.epromonitor.EproMonitorRoomDatabase;
import com.example.cbpierre.epromonitor.dao.GHJourContactDao;
import com.example.cbpierre.epromonitor.models.GHJourContact;
import com.example.cbpierre.epromonitor.models.JoinContactGhSV;

import java.util.List;

public class GHJourContactRepository {
    private GHJourContactDao ghJourContactDao;
    private static OnGHJourContactListeListener onGHJourContactListeListener;

    public GHJourContactRepository(Application application) {
        EproMonitorRoomDatabase database = EproMonitorRoomDatabase.getDatabase(application);
        ghJourContactDao = database.ghJourContactDao();
    }

    public void insertGHJourContact(GHJourContact ghJourContact) {
        new InsertGHJourContactTask(ghJourContactDao).execute(ghJourContact);
    }

    public void deleteGHJourContact() {
        new DeleteGHJourContactTask(ghJourContactDao).execute();
    }

    public LiveData<List<JoinContactGhSV>> getAllJourConact(String jour) {
        return ghJourContactDao.allGhJourContact(jour);
    }

    public void deleteJourContact(Integer ghId, Integer conId) {
        new DeleteJourContactTask(ghJourContactDao).execute(ghId, conId);
    }

    public void updateGHJourContact(String statut, String note, String creePar, String creeLe, String modifiePar, String modifieLe, String jour, String ghId, String conId, String promotion, String livraison, String recouvrement, String autre, String debut, String fin, String lieu, String autreLieu) {
        new UpdateJourContactTask(ghJourContactDao).execute(statut, note, creePar, creeLe, modifiePar, modifieLe, jour, ghId, conId, promotion, livraison, recouvrement, autre, debut, fin, lieu, autreLieu);
    }

    public void getGHJourContactList() {
        new GHJourContactListTask(ghJourContactDao).execute();
    }

    /**
     * All Async Task
     */

    private static class InsertGHJourContactTask extends AsyncTask<GHJourContact, Void, Void> {
        private GHJourContactDao ghJourContactDao;

        public InsertGHJourContactTask(GHJourContactDao ghJourContactDao) {
            this.ghJourContactDao = ghJourContactDao;
        }

        @Override
        protected Void doInBackground(GHJourContact... ghJourContacts) {
            ghJourContactDao.insertGHJOurContact(ghJourContacts[0]);
            return null;
        }
    }

    private static class DeleteGHJourContactTask extends AsyncTask<Void, Void, Void> {
        private GHJourContactDao ghJourContactDao;

        public DeleteGHJourContactTask(GHJourContactDao ghJourContactDao) {
            this.ghJourContactDao = ghJourContactDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            ghJourContactDao.deleteGHJourContact();
            return null;
        }
    }

    private static class DeleteJourContactTask extends AsyncTask<Integer, Void, Void> {
        private GHJourContactDao ghJourContactDao;

        public DeleteJourContactTask(GHJourContactDao ghJourContactDao) {
            this.ghJourContactDao = ghJourContactDao;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            ghJourContactDao.deleteJourContact(integers[0], integers[1]);
            return null;
        }
    }

    private static class UpdateJourContactTask extends AsyncTask<String, Void, Void> {
        private GHJourContactDao ghJourContactDao;

        public UpdateJourContactTask(GHJourContactDao ghJourContactDao) {
            this.ghJourContactDao = ghJourContactDao;
        }

        @Override
        protected Void doInBackground(String... strings) {
            ghJourContactDao.updateGHJourContact(strings[0], strings[1], strings[2], strings[3], strings[4], strings[5], strings[6], Integer.parseInt(strings[7]), Integer.parseInt(strings[8]), Boolean.parseBoolean(strings[9]), Boolean.parseBoolean(strings[10]), Boolean.parseBoolean(strings[11]), Boolean.parseBoolean(strings[12]), strings[13], strings[14], strings[15], strings[16]);
            return null;
        }
    }

    private static class GHJourContactListTask extends AsyncTask<Void, Void, List<GHJourContact>> {
        private GHJourContactDao ghJourContactDao;

        public GHJourContactListTask(GHJourContactDao ghJourContactDao) {
            this.ghJourContactDao = ghJourContactDao;
        }

        @Override
        protected List<GHJourContact> doInBackground(Void... voids) {
            return ghJourContactDao.ghJourContactList();
        }

        @Override
        protected void onPostExecute(List<GHJourContact> ghJourContacts) {
            super.onPostExecute(ghJourContacts);
            if (onGHJourContactListeListener != null)
                onGHJourContactListeListener.onGHJourContact(ghJourContacts);

        }
    }

    public interface OnGHJourContactListeListener {
        void onGHJourContact(List<GHJourContact> ghJourContactList);
    }

    public void setOnGHJourContactListeListener(OnGHJourContactListeListener onGHJourContactListeListener) {
        GHJourContactRepository.onGHJourContactListeListener = onGHJourContactListeListener;
    }
}
