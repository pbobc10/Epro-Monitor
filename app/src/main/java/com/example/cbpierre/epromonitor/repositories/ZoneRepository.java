package com.example.cbpierre.epromonitor.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.cbpierre.epromonitor.EproMonitorRoomDatabase;
import com.example.cbpierre.epromonitor.dao.ZoneDao;
import com.example.cbpierre.epromonitor.models.Zone;

import java.util.List;

public class ZoneRepository {
    private LiveData<List<Zone>> zAllZone;
    private ZoneDao zoneDao;

    public ZoneRepository(Application application) {
        EproMonitorRoomDatabase database = EproMonitorRoomDatabase.getDatabase(application);
        zoneDao = database.zoneDao();
        zAllZone = zoneDao.getAllZone();
    }

    public LiveData<List<Zone>> getzAllZone() {
        return zAllZone;
    }

    public void insert(Zone zone) {
        new InsertZoneAsyncTask(zoneDao).execute(zone);
    }

    private static class InsertZoneAsyncTask extends AsyncTask<Zone, Void, Void> {
        private ZoneDao zoneDao;

        public InsertZoneAsyncTask(ZoneDao zoneDao) {
            this.zoneDao = zoneDao;
        }

        @Override
        protected Void doInBackground(Zone... zones) {
            zoneDao.insert(zones[0]);
            return null;
        }
    }
}
