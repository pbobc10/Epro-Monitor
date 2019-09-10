package com.example.cbpierre.epromonitor.repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.example.cbpierre.epromonitor.EproMonitorRoomDatabase;
import com.example.cbpierre.epromonitor.dao.ContactVisiteDao;
import com.example.cbpierre.epromonitor.models.ContactVisite;

public class ContactVisiteRepository {
    private ContactVisiteDao contactVisiteDao;

    public ContactVisiteRepository(Application application) {
        EproMonitorRoomDatabase database = EproMonitorRoomDatabase.getDatabase(application);
        contactVisiteDao = database.contactVisiteDao();
    }

    public void insetContactVisite(ContactVisite contactVisite) {
        new InsertContactVisite(contactVisiteDao).execute(contactVisite);
    }

    public void deleteCotactVisite(ContactVisite contactVisite) {
        new InsertContactVisite(contactVisiteDao).execute(contactVisite);
    }

    /******************* Async Task ********************************/
    private static class InsertContactVisite extends AsyncTask<ContactVisite, Void, Void> {
        private ContactVisiteDao contactVisiteDao;

        public InsertContactVisite(ContactVisiteDao contactVisiteDao) {
            this.contactVisiteDao = contactVisiteDao;
        }

        @Override
        protected Void doInBackground(ContactVisite... contactVisites) {
            contactVisiteDao.insertContactVisite(contactVisites[0]);
            return null;
        }
    }

    private static class DeleteCotactVisite extends AsyncTask<ContactVisite, Void, Void> {
        private ContactVisiteDao contactVisiteDao;

        public DeleteCotactVisite(ContactVisiteDao contactVisiteDao) {
            this.contactVisiteDao = contactVisiteDao;
        }

        @Override
        protected Void doInBackground(ContactVisite... contactVisites) {
            contactVisiteDao.deleteContatVisite(contactVisites[0]);
            return null;
        }
    }
}
