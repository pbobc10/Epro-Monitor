package com.example.cbpierre.epromonitor.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.cbpierre.epromonitor.models.PlanAction;
import com.example.cbpierre.epromonitor.repositories.PlanActionRepository;

import java.util.List;

public class PlanActionViewModel extends AndroidViewModel {
    private PlanActionRepository planActionRepository;
    private LiveData<List<PlanAction>> allPlanAction;

    public PlanActionViewModel(Application application) {
        super(application);
        planActionRepository = new PlanActionRepository(application);
        allPlanAction = planActionRepository.getAllPlanAction();
    }

    public LiveData<List<PlanAction>> getAllPlanAction() {
        return allPlanAction;
    }

    public void deletePlanAction() {
        planActionRepository.deletePlanAction();
    }

    public void insertPlanAction(PlanAction... planAction) {
        planActionRepository.insertPlanAction(planAction);
    }

}
