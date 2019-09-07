package com.example.cbpierre.epromonitor.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.cbpierre.epromonitor.EproMonitorRoomDatabase;
import com.example.cbpierre.epromonitor.dao.PlanActionDao;
import com.example.cbpierre.epromonitor.models.PlanAction;

import java.util.List;

public class PlanActionRepository {
    public PlanActionDao planActionDao;
    private LiveData<List<PlanAction>> allPlanAction;

    public PlanActionRepository(Application application) {
        EproMonitorRoomDatabase database = EproMonitorRoomDatabase.getDatabase(application);
        planActionDao = database.planActionDao();
        allPlanAction = planActionDao.allPlanAction();
    }

    public LiveData<List<PlanAction>> getAllPlanAction() {
        return allPlanAction;
    }

    public void insertPlanAction(PlanAction planAction) {
        new InsertPlanActionTask(planActionDao).execute(planAction);
    }

    /**
     * Async Task
     */
    private static class InsertPlanActionTask extends AsyncTask<PlanAction, Void, Void> {
        private PlanActionDao planActionDao;

        public InsertPlanActionTask(PlanActionDao planActionDao) {
            this.planActionDao = planActionDao;
        }

        @Override
        protected Void doInBackground(PlanAction... planActions) {
            planActionDao.insertPlanAction(planActions[0]);
            return null;
        }
    }
}
