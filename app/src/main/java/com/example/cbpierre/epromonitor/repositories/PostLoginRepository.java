package com.example.cbpierre.epromonitor.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.cbpierre.epromonitor.EproMonitorRoomDatabase;
import com.example.cbpierre.epromonitor.dao.PostLoginDao;
import com.example.cbpierre.epromonitor.models.PostLogin;

import java.util.List;

public class PostLoginRepository {
    private PostLoginDao postLoginDao;
    private LiveData<List<PostLogin>> mAllPostLogin;

    //constructor
    public PostLoginRepository(Application application) {
        EproMonitorRoomDatabase database = EproMonitorRoomDatabase.getDatabase(application);
        postLoginDao = database.postLoginDao();
        mAllPostLogin = postLoginDao.getAllPostLogin();
    }


    public LiveData<List<PostLogin>> getmAllPostLogin() {
        return mAllPostLogin;
    }

    public void insertPost(PostLogin postLogin) {
        new InsertPostAsyncTask(postLoginDao).execute(postLogin);

    }

    public static class InsertPostAsyncTask extends AsyncTask<PostLogin, Void, Void> {
        private PostLoginDao mPostLoginDao;

        public InsertPostAsyncTask(PostLoginDao postLoginDao) {
            this.mPostLoginDao = postLoginDao;
        }

        @Override
        protected Void doInBackground(PostLogin... postLogins) {
            mPostLoginDao.insert(postLogins[0]);
            return null;
        }
    }
}
