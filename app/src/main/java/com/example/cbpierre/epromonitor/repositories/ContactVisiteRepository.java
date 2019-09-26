package com.example.cbpierre.epromonitor.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.cbpierre.epromonitor.EproMonitorRoomDatabase;
import com.example.cbpierre.epromonitor.dao.ContactVisiteDao;
import com.example.cbpierre.epromonitor.models.ChoiceContactGH;
import com.example.cbpierre.epromonitor.models.ContactVisite;

import java.util.List;

public class ContactVisiteRepository {
    private ContactVisiteDao contactVisiteDao;
    // private LiveData<List<ChoiceContactGH>> allChoiceContactGh;

    public ContactVisiteRepository(Application application) {
        EproMonitorRoomDatabase database = EproMonitorRoomDatabase.getDatabase(application);
        contactVisiteDao = database.contactVisiteDao();
    }

    public LiveData<List<ChoiceContactGH>> getAllChoiceContactGH(String specialite,Integer ghId,String jour) {
        return contactVisiteDao.allChoiceContactGH(specialite,ghId,jour);
    }

    public LiveData<List<ChoiceContactGH>> getAllChoiceContactGH(String specialite, String commnune,Integer ghId,String jour) {
        return contactVisiteDao.allChoiceContactGH(specialite, commnune,ghId,jour);
    }

    public LiveData<List<ChoiceContactGH>> getAllChoiceContactGH(String specialite, String commnune, String localite,Integer ghId,String jour) {
        return contactVisiteDao.allChoiceContactGH(specialite, commnune, localite,ghId,jour);
    }


    public void insetContactVisite(ContactVisite contactVisite) {
        new InsertContactVisite(contactVisiteDao).execute(contactVisite);
    }

    public void deleteCotactVisite() {
        new DeleteCotactVisiteTask(contactVisiteDao).execute();
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

    private static class DeleteCotactVisiteTask extends AsyncTask<Void, Void, Void> {
        private ContactVisiteDao contactVisiteDao;

        public DeleteCotactVisiteTask(ContactVisiteDao contactVisiteDao) {
            this.contactVisiteDao = contactVisiteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            contactVisiteDao.deleteContatVisite();
            return null;
        }
    }
}
