package com.example.cbpierre.epromonitor.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.cbpierre.epromonitor.models.Zone;
import com.example.cbpierre.epromonitor.repositories.ZoneRepository;

import java.util.List;

public class ZoneViewModel extends AndroidViewModel {
    private ZoneRepository zoneRepository;
    private LiveData<List<Zone>> allZone;

    public ZoneViewModel(@NonNull Application application) {
        super(application);
        zoneRepository = new ZoneRepository(application);
        allZone = zoneRepository.getzAllZone();
    }

    public LiveData<List<Zone>> getAllZone() {
        return allZone;
    }

    public void insertZone(Zone zone) {
        zoneRepository.insert(zone);
    }
}
