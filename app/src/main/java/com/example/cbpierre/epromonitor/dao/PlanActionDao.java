package com.example.cbpierre.epromonitor.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.cbpierre.epromonitor.models.PlanAction;

import java.util.List;

@Dao
public interface PlanActionDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertPlanAction(PlanAction planAction);

    @Query("select * from plan_action order by plan_action.debut")
    LiveData<List<PlanAction>> allPlanAction();

    @Query("delete from plan_action")
    void deleteAllPlanAction();
}
