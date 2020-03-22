package com.example.cbpierre.epromonitor.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.cbpierre.epromonitor.EproMonitorRoomDatabase;
import com.example.cbpierre.epromonitor.dao.PaContactDao;
import com.example.cbpierre.epromonitor.models.PaContact;

import java.util.List;

public class PaContactRepository {
    private PaContactDao paContactDao;
    private LiveData<List<PaContact>> allPacontact;

    public PaContactRepository(Application application) {
        EproMonitorRoomDatabase db = EproMonitorRoomDatabase.getDatabase(application);
        paContactDao = db.paContactDao();
        allPacontact = paContactDao.allPaContact();
    }

    public LiveData<List<PaContact>> getAllPacontact() {
        return allPacontact;
    }

    public void insertPaContact(PaContact... paContact) {
        new InsertAlPaContactTask(paContactDao).execute(paContact);
    }

    public void deletePaContact() {
        new DeletePaContactTask(paContactDao).execute();
    }

    public void allPacontactList() {
        new AllPaContactListTask(paContactDao).execute();
    }

    /**
     * Async Task
     */

    private static class InsertAlPaContactTask extends AsyncTask<PaContact, Void, Void> {
        private PaContactDao paContactDao;

        public InsertAlPaContactTask(PaContactDao paContactDao) {
            this.paContactDao = paContactDao;
        }

        @Override
        protected Void doInBackground(PaContact... paContacts) {
            paContactDao.insertPaContact(paContacts);
            return null;
        }
    }

    private static class DeletePaContactTask extends AsyncTask<Void, Void, Void> {
        private PaContactDao paContactDao;

        public DeletePaContactTask(PaContactDao paContactDao) {
            this.paContactDao = paContactDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            paContactDao.deleteAllPaContact();
            return null;
        }
    }

    public static class AllPaContactListTask extends AsyncTask<Void, Void, List<PaContact>> {
        private PaContactDao paContactDao;

        public AllPaContactListTask(PaContactDao paContactDao) {
            this.paContactDao = paContactDao;
        }

        @Override
        protected List<PaContact> doInBackground(Void... voids) {
            return paContactDao.allPaContactList();

        }

        @Override
        protected void onPostExecute(List<PaContact> paContactList) {
            super.onPostExecute(paContactList);
            if (pacontactListListener != null)
                pacontactListListener.onPaContactClick(paContactList);
        }
    }

    /**
     * Interface
     */
    private static AllPacontactListListener pacontactListListener;

    public interface AllPacontactListListener {
        void onPaContactClick(List<PaContact> paContactList);
    }

    public void setPacontactListListener(AllPacontactListListener pacontactListListener) {
        PaContactRepository.pacontactListListener = pacontactListListener;
    }
}
